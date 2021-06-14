package com.neoris.tcl.services;

import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_ASSET;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_OTHER;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_PAYABLES;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_PAYABLES1;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_PAYABLES2;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_PAYABLES3;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_PAYABLES4;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_PAYABLES5;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_PAYROLL;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_RECEIVABLES;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_RECEIVABLES1;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_RECEIVABLES2;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_RECEIVABLES3;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_RECEIVABLES4;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_COSTMANAGER;

import java.util.List;

import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.utils.ProcessRollUps;
import com.neoris.tcl.websocket.IWebSocketService;

@Service
public class RollUpProcessService implements IRollUpProcessService {

	private final static Logger LOG = LoggerFactory.getLogger(RollUpProcessService.class);
	private IWebSocketService webSocketService;
	private IHfmRollupEntriesService service;
	private User user;

	@Override
	@Async
	public void processRollUps(List<HfmRollupEntries> lstRollUps, List<HfmRollupEntries> lstSelectedRollups) {
		// Initilize status of each rollup pending for processing...
		lstSelectedRollups.stream().forEach(roll -> roll.pending());

		for (HfmRollupEntries rollup : lstSelectedRollups) {
			// find rollup and its processing from original list
			// if not, the messages don't refreshing
			int idx = lstRollUps.indexOf(rollup);
			processRollUp(lstRollUps.get(idx));
			webSocketService.sendPushNotification("Finished company " + rollup.getEntity(), "Sucess", "info");
		}

		// clean the selected rollups list...
		lstSelectedRollups = null;
	}

	/**
	 * 
	 * @param rollUp
	 */
	private void processRollUp(HfmRollupEntries rollUp) {
		LOG.info("store list rollup = {}", rollUp);

		// service.saveAll(this.lstRollUps);

		LOG.info("Processing Rollup Del Data by company ");
		service.rollDelData(rollUp.getCompanyid().intValue(), rollUp.getSegment1(), rollUp.getRperiod(),
				rollUp.getRyear(), user.getUsername());

		rollUp.setAttribute1(HfmRollupEntries.STATUS_PROCESSING);
		rollUp.setValidations(HfmRollupEntries.STATUS_PROCESSING);
		// PrimeFaces.current().ajax().update(DT_ROLLUP);
		webSocketService.sendPushNotification(rollUp);

		LOG.info("Processing Rollup Start ");
		service.rollUpStart(rollUp.getCompanyid().intValue(), rollUp.getRperiod(), rollUp.getRyear(),
				rollUp.getSegment1(), user.getUsername());
		webSocketService.sendPushNotification(rollUp);

		// 2.- Process Drill Details
		processDrillDetailsHd(rollUp);

		LOG.info("*******************Processing Drill Details*********************** ");
		// 2.1- Process Drill Details
		processDrillDetails(rollUp);

		LOG.info("*********************Processing CostMngr Details*********************");
		// 3.- Process Cost Manager
		processCostManager(rollUp);// getheader

		// 4.- Run the Drills...
		processDrils(rollUp);

		LOG.info("*********************Processing Validations*********************");
		// 5.- Run the validations..
		processValidations(rollUp);

		// 6.- Run Match account...
		LOG.info("*********************Processing Match ACccounts*********************");
		processMatchAccount(rollUp);

		LOG.info("**********************Finish processing rollups!!********************************");

		webSocketService.sendPushNotification(rollUp.getCompanyid());

	}

	/**
	 * 
	 * @param rollUp
	 */
	private void processDrillDetailsHd(HfmRollupEntries rollUp) {
		LOG.info("Preparing Headers rollups...");

		Thread payablesThread = null;
		Thread receivablesThread = null;

		ProcessRollUps rollUpPayables = getProcessRollUpsInstance(rollUp, P_CONCEPT_PAYABLES, 0, false, false);
		ProcessRollUps rollUpReceivables = getProcessRollUpsInstance(rollUp, P_CONCEPT_RECEIVABLES, 0, false, false);

		// 2.- Process the drills details...
		LOG.info("Preparing threads for header-rollUp process...");
		try {
			payablesThread = createRollUpTread(rollUpPayables);
			receivablesThread = createRollUpTread(rollUpReceivables);

		} catch (Exception e) {
			LOG.error("Error creating threads: {}", e.getMessage(), e);
			webSocketService.sendPushNotification(e.getMessage(), "Error creating threads", "error", rollUp);
			return;
		}

		LOG.info("Starting Thread for header-rollUp process: {}, companyId:{}", P_CONCEPT_PAYABLES,
				rollUp.getCompanyid());
		payablesThread.start();

		LOG.info("Starting Thread for header-rollUp process: {}, companyId:{}", P_CONCEPT_RECEIVABLES,
				rollUp.getCompanyid());
		receivablesThread.start();

		// 3.- wait for finish these process and start Costmanager
		try {
			// otherThread.sleep(5000);
			payablesThread.join();
			receivablesThread.join();
			webSocketService.sendPushNotification(rollUp.getCompanyid());

			// rollUp.setAttribute2(HfmRollupEntries.STATUS_OK);
		} catch (InterruptedException e) {
			LOG.error("Error running header process: {}", e.getMessage(), e);
			rollUp.setAttribute2(HfmRollupEntries.STATUS_ERROR);
			webSocketService.sendPushNotification(e.getMessage(), "Error Running header process", "error", rollUp);
		}
		// PrimeFaces.current().ajax().update(DT_ROLLUP);
		LOG.info("Thread for HEADER-rollUp Finish!");
	}

	/**
	 * 
	 * @param rollUp
	 */
	private void processDrillDetails(HfmRollupEntries rollUp) {
		LOG.info("Preparing concept rollups...");
		rollUp.setAttribute2(HfmRollupEntries.STATUS_PROCESSING);
		webSocketService.sendPushNotification(rollUp);
		// this.pushStatus("Preparing concept rollups...");
		// PrimeFaces.current().ajax().update(DT_ROLLUP);

		Thread payablesThread1 = null;
		Thread payablesThread2 = null;
		Thread payablesThread3 = null;
		Thread payablesThread4 = null;
		Thread payablesThread5 = null;
		Thread receivablesThread1 = null;
		Thread receivablesThread2 = null;
		Thread receivablesThread3 = null;
		Thread receivablesThread4 = null;
		Thread payrollThread = null;
		Thread assetsThread = null;
		Thread otherThread = null;

		ProcessRollUps rollUpPayables1 = getProcessRollUpsInstance(rollUp, P_CONCEPT_PAYABLES1, 0, false, false);
		ProcessRollUps rollUpPayables2 = getProcessRollUpsInstance(rollUp, P_CONCEPT_PAYABLES2, 0, false, false);
		ProcessRollUps rollUpPayables3 = getProcessRollUpsInstance(rollUp, P_CONCEPT_PAYABLES3, 0, false, false);
		ProcessRollUps rollUpPayables4 = getProcessRollUpsInstance(rollUp, P_CONCEPT_PAYABLES4, 0, false, false);
		ProcessRollUps rollUpPayables5 = getProcessRollUpsInstance(rollUp, P_CONCEPT_PAYABLES5, 0, false, false);
		ProcessRollUps rollUpReceivables1 = getProcessRollUpsInstance(rollUp, P_CONCEPT_RECEIVABLES1, 0, false, false);
		ProcessRollUps rollUpReceivables2 = getProcessRollUpsInstance(rollUp, P_CONCEPT_RECEIVABLES2, 0, false, false);
		ProcessRollUps rollUpReceivables3 = getProcessRollUpsInstance(rollUp, P_CONCEPT_RECEIVABLES3, 0, false, false);
		ProcessRollUps rollUpReceivables4 = getProcessRollUpsInstance(rollUp, P_CONCEPT_RECEIVABLES4, 0, false, false);
		ProcessRollUps rollUpPayroll = getProcessRollUpsInstance(rollUp, P_CONCEPT_PAYROLL, 0, false, false);
		ProcessRollUps rollUpAssets = getProcessRollUpsInstance(rollUp, P_CONCEPT_ASSET, 0, false, false);
		ProcessRollUps rollUpOther = getProcessRollUpsInstance(rollUp, P_CONCEPT_OTHER, 0, false, false);

		// 2.- Process the drills details...
		LOG.info("Preparing threads for rollUp process...");
		try {
			payablesThread1 = createRollUpTread(rollUpPayables1);
			payablesThread2 = createRollUpTread(rollUpPayables2);
			payablesThread3 = createRollUpTread(rollUpPayables3);
			payablesThread4 = createRollUpTread(rollUpPayables4);
			payablesThread5 = createRollUpTread(rollUpPayables5);
			receivablesThread1 = createRollUpTread(rollUpReceivables1);
			receivablesThread2 = createRollUpTread(rollUpReceivables2);
			receivablesThread3 = createRollUpTread(rollUpReceivables3);
			receivablesThread4 = createRollUpTread(rollUpReceivables4);
			payrollThread = createRollUpTread(rollUpPayroll);
			assetsThread = createRollUpTread(rollUpAssets);
			otherThread = createRollUpTread(rollUpOther);
		} catch (Exception e) {
			LOG.error("Error creating threads: {}", e.getMessage(), e);
			rollUp.setAttribute2(HfmRollupEntries.STATUS_ERROR);
			webSocketService.sendPushNotification(e.getMessage(), "Error Creating threads", "error", rollUp);
			return;
		}

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_PAYABLES1, rollUp.getCompanyid());
		payablesThread1.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_PAYABLES2, rollUp.getCompanyid());
		payablesThread2.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_PAYABLES3, rollUp.getCompanyid());
		payablesThread3.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_PAYABLES4, rollUp.getCompanyid());
		payablesThread4.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_PAYABLES5, rollUp.getCompanyid());
		payablesThread5.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_RECEIVABLES1, rollUp.getCompanyid());
		receivablesThread1.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_RECEIVABLES2, rollUp.getCompanyid());
		receivablesThread2.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_RECEIVABLES3, rollUp.getCompanyid());
		receivablesThread3.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_RECEIVABLES4, rollUp.getCompanyid());
		receivablesThread4.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_PAYROLL, rollUp.getCompanyid());
		payrollThread.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_ASSET, rollUp.getCompanyid());
		assetsThread.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_OTHER, rollUp.getCompanyid());
		otherThread.start();

		// 3.- wait for finish these process and start Costmanager
		try {
			// otherThread.sleep(5000);
			payablesThread1.join();
			payablesThread2.join();
			payablesThread3.join();
			payablesThread4.join();
			payablesThread5.join();
			receivablesThread1.join();
			receivablesThread2.join();
			receivablesThread3.join();
			receivablesThread4.join();
			payrollThread.join();
			assetsThread.join();
			otherThread.join();
			// rollUp.setAttribute2(HfmRollupEntries.STATUS_OK);
			webSocketService.sendPushNotification(rollUp.getCompanyid());
		} catch (InterruptedException e) {
			LOG.error("Error running process: {}", e.getMessage(), e);
			rollUp.setAttribute2(HfmRollupEntries.STATUS_ERROR);
			webSocketService.sendPushNotification(e.getMessage(), "Error running process", "error", rollUp);
		}
		// PrimeFaces.current().ajax().update(DT_ROLLUP);
		LOG.info("Thread for rollUp Finish!");
	}

	/**
	 * Step process 3
	 * 
	 * @param rollUp
	 */
	private void processCostManager(HfmRollupEntries rollUp) {

		ProcessRollUps rollUpCostManager = getProcessRollUpsInstance(rollUp, P_COSTMANAGER, 0, false, false);
		rollUp.setAttribute3(HfmRollupEntries.STATUS_PROCESSING);

		// PrimeFaces.current().ajax().update(DT_ROLLUP);
		Thread costmanagerThread = createRollUpTread(rollUpCostManager);
		costmanagerThread.run();

		// Wait for process to finish....
		try {
			costmanagerThread.join();
			// rollUp.setAttribute3(HfmRollupEntries.STATUS_OK);
			webSocketService.sendPushNotification(rollUp.getCompanyid());
		} catch (InterruptedException e) {
			LOG.error("Error running costmanager: {}", e.getMessage(), e);
			rollUp.setAttribute3(HfmRollupEntries.STATUS_ERROR);
			webSocketService.sendPushNotification(e.getMessage(), "Error running Cost manager", "error", rollUp);
		}
		// PrimeFaces.current().ajax().update(DT_ROLLUP);
	}

	private void processDrils(HfmRollupEntries rollUp) {
		rollUp.setAttribute4(HfmRollupEntries.STATUS_PROCESSING);
		webSocketService.sendPushNotification(rollUp);
		// PrimeFaces.current().ajax().update(DT_ROLLUP);

		ProcessRollUps drillRollUp1 = getProcessRollUpsInstance(rollUp, "", 1, false, false);
		ProcessRollUps drillRollUp2 = getProcessRollUpsInstance(rollUp, "", 2, false, false);
		ProcessRollUps drillRollUp3 = getProcessRollUpsInstance(rollUp, "", 3, false, false);
		ProcessRollUps drillRollUp4 = getProcessRollUpsInstance(rollUp, "", 4, false, false);
		ProcessRollUps drillRollUp5 = getProcessRollUpsInstance(rollUp, "", 5, false, false);
		ProcessRollUps drillRollUp6 = getProcessRollUpsInstance(rollUp, "", 6, false, false);
		ProcessRollUps drillRollUp7 = getProcessRollUpsInstance(rollUp, "", 7, false, false);
		ProcessRollUps drillRollUp8 = getProcessRollUpsInstance(rollUp, "", 8, false, false);
		ProcessRollUps drillRollUp9 = getProcessRollUpsInstance(rollUp, "", 9, false, false);

		Thread drillRollUp1Tread = createRollUpTread(drillRollUp1);
		Thread drillRollUp2Tread = createRollUpTread(drillRollUp2);
		Thread drillRollUp3Tread = createRollUpTread(drillRollUp3);
		Thread drillRollUp4Tread = createRollUpTread(drillRollUp4);
		Thread drillRollUp5Tread = createRollUpTread(drillRollUp5);
		Thread drillRollUp6Tread = createRollUpTread(drillRollUp6);
		Thread drillRollUp7Tread = createRollUpTread(drillRollUp7);
		Thread drillRollUp8Tread = createRollUpTread(drillRollUp8);
		Thread drillRollUp9Tread = createRollUpTread(drillRollUp9);

		LOG.info("Starting Thread for rollUp drill process 1");
		drillRollUp1Tread.start();

		LOG.info("Starting Thread for rollUp drill process 2");
		drillRollUp2Tread.start();

		LOG.info("Starting Thread for rollUp drill process 3");
		drillRollUp3Tread.start();

		LOG.info("Starting Thread for rollUp drill process 4");
		drillRollUp4Tread.start();

		LOG.info("Starting Thread for rollUp drill process 5");
		drillRollUp5Tread.start();

		LOG.info("Starting Thread for rollUp drill process 6");
		drillRollUp6Tread.start();

		LOG.info("Starting Thread for rollUp drill process 7");
		drillRollUp7Tread.start();

		LOG.info("Starting Thread for rollUp drill process 8");
		drillRollUp8Tread.start();

		LOG.info("Starting Thread for rollUp drill process 9");
		drillRollUp9Tread.start();

		// wait for finish
		try {
			// drillRollUp9Tread.sleep(5000);
			drillRollUp1Tread.join();
			drillRollUp2Tread.join();
			drillRollUp3Tread.join();
			drillRollUp4Tread.join();
			drillRollUp5Tread.join();
			drillRollUp6Tread.join();
			drillRollUp7Tread.join();
			drillRollUp8Tread.join();
			drillRollUp9Tread.join();
			// rollUp.setAttribute4(HfmRollupEntries.STATUS_OK);
			webSocketService.sendPushNotification(rollUp.getCompanyid());
		} catch (InterruptedException e) {
			LOG.error("Error running Drills rollup: {}", e.getMessage(), e);
			rollUp.setAttribute4(HfmRollupEntries.STATUS_ERROR);
			webSocketService.sendPushNotification(e.getMessage(), "Error running Drills rollup", "error", rollUp);
		}
	}

	/**
	 * 
	 * @param rollUp
	 */
	private void processValidations(HfmRollupEntries rollUp) {
		rollUp.setAttribute5(HfmRollupEntries.STATUS_PROCESSING);
		webSocketService.sendPushNotification(rollUp);

		ProcessRollUps rollUpValidations = getProcessRollUpsInstance(rollUp, "", 0, true, false);
		Thread validationsThread = createRollUpTread(rollUpValidations);
		validationsThread.run();

		try {
			validationsThread.join();
			webSocketService.sendPushNotification(rollUp.getCompanyid());
		} catch (InterruptedException e) {
			LOG.error("Error running Validations rollup: {}", e.getMessage(), e);
			rollUp.setAttribute5(HfmRollupEntries.STATUS_ERROR);
			webSocketService.sendPushNotification(e.getMessage(), "Error running Validation", "error", rollUp);
		}
	}

	/**
	 * 
	 * @param rollUp
	 */
	private void processMatchAccount(HfmRollupEntries rollUp) {
		ProcessRollUps rollUpMatchAccount = getProcessRollUpsInstance(rollUp, "", 0, false, true);
		Thread matchAccountThread = createRollUpTread(rollUpMatchAccount);
		matchAccountThread.run();
		try {
			matchAccountThread.join();
			webSocketService.sendPushNotification(rollUp.getCompanyid());
		} catch (InterruptedException e) {
			LOG.error("Error running Match Account rollup: {}", e.getMessage(), e);
			rollUp.setAttribute6(HfmRollupEntries.STATUS_ERROR);
			webSocketService.sendPushNotification(e.getMessage(), "Error running MatchAccount", "error", rollUp);
		}

	}

	/**
	 * 
	 * @param rollUp
	 * @param process
	 * @param numDrill
	 * @param processValidations
	 * @param matchAccounts
	 * @return
	 */
	private ProcessRollUps getProcessRollUpsInstance(HfmRollupEntries rollUp, String process, int numDrill,
			boolean processValidations, boolean matchAccounts) {
		ProcessRollUps rollup = new ProcessRollUps(rollUp, this.service, process, numDrill, processValidations,
				matchAccounts, this.user);
		rollup.setFacesContext(FacesContext.getCurrentInstance());
		rollup.setPrimefaces(PrimeFaces.current());
		rollup.setWebSocketService(webSocketService);
		return rollup;
	}

	/**
	 * 
	 * @param rollup
	 * @return
	 */
	private Thread createRollUpTread(ProcessRollUps rollup) {
		LOG.info("create thread for ProcessRollUps => {}", rollup);
		Thread thead = new Thread(rollup);
		thead.setName(rollup.getProcessId());
		LOG.info("Return with thead => {}", thead);
		return thead;
	}

	@Override
	public void setWebSocketService(IWebSocketService webSocketService) {
		this.webSocketService = webSocketService;
	}

	@Override
	public void setService(IHfmRollupEntriesService service) {
		this.service = service;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

}
