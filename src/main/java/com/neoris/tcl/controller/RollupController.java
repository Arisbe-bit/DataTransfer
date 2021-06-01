package com.neoris.tcl.controller;

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

import java.time.Year;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.validation.constraints.Min;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmFfss;
import com.neoris.tcl.models.HfmFfssDetails;
import com.neoris.tcl.models.HfmLayout;
import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.models.ViewFFSSGrouped;
import com.neoris.tcl.models.ViewRollupMatchFFSS;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.services.HfmLayoutService;
import com.neoris.tcl.services.IHfmFfssDetailsService;
import com.neoris.tcl.services.IHfmFfssService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.services.IViewRollupFFSSGconsService;
import com.neoris.tcl.services.IViewRollupMatchFFSSService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ProcessRollUps;
import com.neoris.tcl.websocket.WebSocketConfig;
import com.neoris.tcl.websocket.WebSocketService;

@Controller(value = "rollupControllerBean")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RollupController {

	private final static Logger LOG = LoggerFactory.getLogger(RollupController.class);
	private final static String DT_ROLLUP = "rollupForm:dt-rollup";
//	private static final String DOT_XML = ".xhtml";

//	private final static String ROLLUPS = "/rollup/rollups";
	private final static String FFSS = "/rollup/ffss";
	private static final String SUMARY = "/rollup/sumary";
	private static final String LAYOUT = "/rollup/layout";
	private static final String MOVEMENTS = "/rollup/movements";

	private List<HfmRollupEntries> lstRollUps;
	private List<HfmRollupEntries> lstSelectedRollups;
	private HfmRollupEntries curRollUp;

	private List<HfmFfss> lstHfmFfss;
	private HfmFfss curHfmFfss;

	private List<HfmFfssDetails> lstHfmFfssDetails;

	private List<ViewRollupMatchFFSS> lstMatchAcc;
	private ViewRollupMatchFFSS curMacthAcc;

	// sumarized FFSS
	private List<ViewFFSSGrouped> lstSumFS;
	private ViewFFSSGrouped curFSgroup;

	// HFM Layout
	private List<HfmLayout> lstlayout;
	private HfmLayout curlayout;

	private User user;

	@Autowired
	private IHfmRollupEntriesService service;
	@Autowired
	private IHfmFfssService hfmFfSsService;
	@Autowired
	private IHfmFfssDetailsService hfmFfssDetailsService;
	@Autowired
	private IViewRollupMatchFFSSService matchaccService;
	@Autowired
	private IViewRollupFFSSGconsService serviceFSG;
	@Autowired
	private HfmLayoutService serviceLay;
	@Autowired
	private WebSocketService webSocketService;

	private Calendar calendar;
	@Min(1998)
	private int zyear;
	private String zmonth;
	
	@PostConstruct
	public void init() {
		this.user = Functions.getUser();
		this.calendar = Calendar.getInstance();
		this.calendar.add(Calendar.MONTH, -1);

		this.zyear = this.getCurrYear();
		this.zmonth = this.getMonth(calendar.get(Calendar.MONTH) + 1);

		LOG.info("Year:{}, Period:{}", this.zyear, this.zmonth);

		LOG.info("Init rollupControllerBean...");
		setLstRollUps(service.findAll());
		
		LOG.info("Init setting rollup service to webSocketService...");
		webSocketService.setRollUpService(service);
		
//		if(webSocketService != null) {
//			LOG.info("Sending test message....");
//			RollUpMessage message = new RollUpMessage();
//			message.setMessage("Initializing Push service...");
//			message.setSeverity("info");
//			message.setTitle("Init");
//			webSocketService.notyfyRollUpProcess(message);
//		} else {
//			LOG.info("webSocketService is null!!!");
//		}
	}

	public void openNew() {
		this.setCurRollUp(new HfmRollupEntries());
	}

	public String getprocessButtonMessage() {
		String retval = "Process";
		String message = "Start processing %s Rollups";
		if (hasSelectedRollUps()) {
			retval = String.format(message, lstSelectedRollups.size());
		}
		return retval;
	}

	public String getProcessButtonStyleClass() {
		if (hasSelectedRollUps()) {
			return "ui-button-primary";
		} else {
			return "ui-button-secondary";
		}
	}

	public String getFormNameId() {
		return "rollupForm";
	}

	public boolean hasSelectedRollUps() {
		return this.lstSelectedRollups != null && !this.lstSelectedRollups.isEmpty();
	}

	public int getZyear() {
		return zyear;
	}

	public void setZyear(int zyear) {
		if (zyear < 1998) {
			Functions.addWarnMessage("Not valid Year", "The year must be bigger than 1998");
			PrimeFaces.current().ajax().update("rollupForm:messages");
			return;
		}
		String zID = "rollupForm:dt-rollup:%s:year";
		for (int i = 0; i < this.lstRollUps.size(); i++) {
			this.lstRollUps.get(i).setRyear(String.valueOf(zyear));
			PrimeFaces.current().ajax().update(String.format(zID, i));
		}
		this.zyear = zyear;
	}

	public String getZmonth() {
		return zmonth;
	}

	public void setZmonth(String zmonth) {
		String zID = "rollupForm:dt-rollup:%s:month";
		for (int i = 0; i < this.lstRollUps.size(); i++) {
			this.lstRollUps.get(i).setRperiod(zmonth);
			PrimeFaces.current().ajax().update(String.format(zID, i));
		}
		this.zmonth = zmonth;
	}

	/**
	 * 
	 * @param event
	 */
	public void processSelectedRollUps(ActionEvent event) {
		LOG.info("Running process with rollUpBean = {}, event = {}", lstSelectedRollups, event);

		// Initilize status of each rollup pending for processing...
		lstSelectedRollups.stream().forEach(roll -> roll.pending());
		//PrimeFaces.current().ajax().update(DT_ROLLUP);

		for (HfmRollupEntries rollup : lstSelectedRollups) {
			// find rollup and its processing from original list
			// if not, the messages dont refreshing
			int idx = this.lstRollUps.indexOf(rollup);
			processRollUp(this.lstRollUps.get(idx));
			webSocketService.sendPushNotification( "Finished company "+rollup.getEntity(),"Sucess", "info");
		}
		
		// clean the selected rollups list...
		lstSelectedRollups = null;
		
		//Functions.addInfoMessage("Succes", "RollUps Proceced!!");
		PrimeFaces.current().executeScript("PF('dtRollUps').unselectAllRows()");
		setLstRollUps(service.findAll());
		this.curRollUp = null;
		PrimeFaces.current().ajax().update(DT_ROLLUP);
		
	}

	/**
	 * 
	 * @param rollUp
	 */
	private void processRollUp(HfmRollupEntries rollUp) {

		LOG.info("Now processing rollup = {}", rollUp);
		// 1.- Start RollUp Service.

		LOG.info("Processing Rollup Del Data by company ");
		service.rollDelData(rollUp.getCompanyid().intValue(), rollUp.getSegment1(), rollUp.getRperiod(),
				rollUp.getRyear(), user.getUsername());

		rollUp.setAttribute1(HfmRollupEntries.STATUS_PROCESSING);
		rollUp.setValidations(HfmRollupEntries.STATUS_PROCESSING);
		//PrimeFaces.current().ajax().update(DT_ROLLUP);
		webSocketService.sendPushNotification(rollUp);

		LOG.info("Processing Rollup Start ");
		service.rollUpStart(rollUp.getCompanyid().intValue(), rollUp.getRperiod(), rollUp.getRyear(),
				rollUp.getSegment1(), user.getUsername());
		//PrimeFaces.current().ajax().update(DT_ROLLUP);
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
		//rollUp.setValidations(HfmRollupEntries.STATUS_OK);
		// 6.- Run Match account...
		LOG.info("*********************Processing Match ACccounts*********************");
		processMatchAccount(rollUp);

		LOG.info("**********************Finish processing rollups!!********************************");

		webSocketService.sendPushNotification(rollUp.getCompanyid());
		//rollUp.setAttribute1(HfmRollupEntries.STATUS_OK);
		//rollUp.setAttribute6(HfmRollupEntries.STATUS_OK);
		//Functions.addInfoMessage("Succes", "RollUps Proceced!!");
		//PrimeFaces.current().ajax().update(getFormNameId() + ":messages", DT_ROLLUP);

	}

	public void layoutprocess() {
		LOG.info("Running process with curRollUp = {}", curRollUp);
		try {
			int comapnyid = this.curRollUp.getCompanyid().intValue();
			String period = this.curRollUp.getRperiod();
			String vyear = this.curRollUp.getRyear();

			serviceLay.rollUpLayout(comapnyid, period, vyear, user.getUsername());
			
			webSocketService.sendPushNotification( "Finished Layout ","Sucess", "info");
			
			
		} catch (Exception e) {
			LOG.error("Exception in layoutprocess: => {}", e.getMessage());
		}
	}

	private void processMatchAccount(HfmRollupEntries rollUp) {
		//rollUp.setAttribute6(HfmRollupEntries.STATUS_PROCESSING);
		//PrimeFaces.current().ajax().update(DT_ROLLUP);
		//webSocketService.sendPushNotification(rollUp);

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

	private void processValidations(HfmRollupEntries rollUp) {
		rollUp.setAttribute5(HfmRollupEntries.STATUS_PROCESSING);
		webSocketService.sendPushNotification(rollUp);

		ProcessRollUps rollUpValidations = getProcessRollUpsInstance(rollUp, "", 0, true, false);
		Thread validationsThread = createRollUpTread(rollUpValidations);
		validationsThread.run();

		try {
			validationsThread.join();
			//rollUp.setAttribute5(HfmRollupEntries.STATUS_OK);
			webSocketService.sendPushNotification(rollUp.getCompanyid());
		} catch (InterruptedException e) {
			LOG.error("Error running Validations rollup: {}", e.getMessage(), e);
			rollUp.setAttribute5(HfmRollupEntries.STATUS_ERROR);
			webSocketService.sendPushNotification(e.getMessage(), "Error running Validation", "error", rollUp);
		}
		//PrimeFaces.current().ajax().update(DT_ROLLUP);
	}

	/**
	 * Step process 3
	 * 
	 * @param rollUp
	 */
	private void processCostManager(HfmRollupEntries rollUp) {

		ProcessRollUps rollUpCostManager = getProcessRollUpsInstance(rollUp, P_COSTMANAGER, 0, false, false);
		rollUp.setAttribute3(HfmRollupEntries.STATUS_PROCESSING);

		//PrimeFaces.current().ajax().update(DT_ROLLUP);
		Thread costmanagerThread = createRollUpTread(rollUpCostManager);
		costmanagerThread.run();

		// Wait for process to finish....
		try {
			costmanagerThread.join();
			//rollUp.setAttribute3(HfmRollupEntries.STATUS_OK);
			webSocketService.sendPushNotification(rollUp.getCompanyid());
		} catch (InterruptedException e) {
			LOG.error("Error running costmanager: {}", e.getMessage(), e);
			rollUp.setAttribute3(HfmRollupEntries.STATUS_ERROR);
			webSocketService.sendPushNotification(e.getMessage(), "Error running Cost manager", "error", rollUp);
		}
		//PrimeFaces.current().ajax().update(DT_ROLLUP);
	}

	/**
	 * 
	 * @param rollUp
	 */
	private void processDrils(HfmRollupEntries rollUp) {
		rollUp.setAttribute4(HfmRollupEntries.STATUS_PROCESSING);
		webSocketService.sendPushNotification(rollUp);
		//PrimeFaces.current().ajax().update(DT_ROLLUP);

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
			//rollUp.setAttribute4(HfmRollupEntries.STATUS_OK);
			webSocketService.sendPushNotification(rollUp.getCompanyid());
		} catch (InterruptedException e) {
			LOG.error("Error running Drills rollup: {}", e.getMessage(), e);
			rollUp.setAttribute4(HfmRollupEntries.STATUS_ERROR);
			webSocketService.sendPushNotification(e.getMessage(), "Error running Drills rollup", "error", rollUp);
		}
		//PrimeFaces.current().ajax().update(DT_ROLLUP);
	}

	/**
	 * 
	 * @param rollUp
	 */
	private void processDrillDetails(HfmRollupEntries rollUp) {
		LOG.info("Preparing concept rollups...");
		rollUp.setAttribute2(HfmRollupEntries.STATUS_PROCESSING);
		webSocketService.sendPushNotification(rollUp);
		//this.pushStatus("Preparing concept rollups...");
		//PrimeFaces.current().ajax().update(DT_ROLLUP);

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
			//rollUp.setAttribute2(HfmRollupEntries.STATUS_OK);
			webSocketService.sendPushNotification(rollUp.getCompanyid());
		} catch (InterruptedException e) {
			LOG.error("Error running process: {}", e.getMessage(), e);
			rollUp.setAttribute2(HfmRollupEntries.STATUS_ERROR);
			webSocketService.sendPushNotification(e.getMessage(), "Error running process", "error", rollUp);
		}
		//PrimeFaces.current().ajax().update(DT_ROLLUP);
		LOG.info("Thread for rollUp Finish!");
	}

	/**
	 * 
	 * @param rollUp
	 */
	private void processDrillDetailsHd(HfmRollupEntries rollUp) {
		LOG.info("Preparing Headers rollups...");
		// rollUp.setAttribute2(HfmRollupEntries.STATUS_PROCESSING);
		// PrimeFaces.current().ajax().update(DT_ROLLUP);

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
			webSocketService.sendPushNotification(e.getMessage(), "Error creating threads", "error",  rollUp);
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
			webSocketService.sendPushNotification(e.getMessage(), "Error Running header process", "error",  rollUp);
		}
		//PrimeFaces.current().ajax().update(DT_ROLLUP);
		LOG.info("Thread for HEADER-rollUp Finish!");
	}

	/**
	 * 
	 * @param event
	 */
	public void viewRollUp(ActionEvent event) {
		LOG.info("Entering to view detail of rollUpBean = {}, event = {}", this.curRollUp, event);
		PrimeFaces.current().ajax().update(getFormNameId() + ":messages", getFormNameId() + ":dt-rollup");
		PrimeFaces.current().executeScript("PF('dtRollUps').unselectAllRows()");
	}

	public List<HfmRollupEntries> getLstRollUps() {
		return lstRollUps;
	}

	public void setLstRollUps(List<HfmRollupEntries> lstRollUps) {
		lstRollUps.forEach(r -> {
			r.setRyear(String.valueOf(this.calendar.get(Calendar.YEAR)));
			r.setRperiod(this.getMonth(this.calendar.get(Calendar.MONTH) + 1));
		});
		this.lstRollUps = lstRollUps;
	}

	public HfmRollupEntries getCurRollUp() {
		return curRollUp;
	}

	/**
	 * 
	 * @param curRollUp
	 */
	public void setCurRollUp(HfmRollupEntries curRollUp) {
		LOG.info("Obtain curRollUp = {}", curRollUp);
		this.curRollUp = curRollUp;
	}

	/**
	 * 
	 * @param rollUp
	 */
	public void setRollUpOnRowClick(HfmRollupEntries rollUp) {
		LOG.info("Obtains curRollUp = {}. Finding companies", rollUp);
	}

	public HfmFfss getCurHfmFfss() {
		return curHfmFfss;
	}

	/**
	 * 
	 * @param curHfmFfss
	 */
	public void setCurHfmFfss(HfmFfss curHfmFfss) {
		LOG.info("Obtains curHfmFfss = {}", curHfmFfss);
		this.curHfmFfss = curHfmFfss;

//		LOG.info("Update view grouped FFSS ...");
//		PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:dtfsgrouped");

	}

	/**
	 * 
	 * @param event
	 */
	public void curHfmffssClic(AjaxBehaviorEvent event) {
		LOG.info("curHfmffssClic with event = {}", event.getComponent());
	}

	/**
	 * This event is fired when user clicks on a row of main data table. It search
	 * the company childs for "Global Validation" Data Table.
	 * 
	 * @param event
	 */
//    public void rowClick(AjaxBehaviorEvent event) {        
//        HfmRollupEntries entry;
//        // get the datatable object when click even was fired
//        DataTable dt = (DataTable) event.getComponent();
//        // Get the list of selected items. In this case will be only one item inside an ArrayList
//        Object obj = dt.getSelection();
//        if(obj instanceof ArrayList<?>) {
//            List<HfmRollupEntries> items = (ArrayList<HfmRollupEntries>) obj;
//            entry = items.get(0);
//            LOG.info("Entry = {}", entry);
//            LOG.info("Updating lstMatchAcc with company Id = {} ", entry.getCompanyid());
//            this.lstMatchAcc = matchaccService.findByCompanyid(entry.getCompanyid());
//            if (this.lstMatchAcc == null || this.lstMatchAcc.isEmpty()) {
//                Functions.addWarnMessage("Attention",
//                        String.format("No records found for companyId=%s", entry.getCompanyid()));
//            }
//            LOG.info("Update view...");
//            PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:dt-macthacc");
//        } else {
//            LOG.info("Item type is = {}", obj.getClass().getSimpleName());
//        }
//    }

	/**
	 * 
	 * @return
	 */
	public List<HfmRollupEntries> getLstSelectedRollups() {
		return lstSelectedRollups;
	}

	public void setLstSelectedRollups(List<HfmRollupEntries> lstSelectedRollups) {
		this.lstSelectedRollups = lstSelectedRollups;
	}

	public List<HfmFfss> getLstHfmFfss() {
		return lstHfmFfss;
	}

	public void setLstHfmFfss(List<HfmFfss> lstHfmFfss) {
		this.lstHfmFfss = lstHfmFfss;
	}

	public List<HfmFfssDetails> getLstHfmFfssDetails() {
		return lstHfmFfssDetails;
	}

	public void setLstHfmFfssDetails(List<HfmFfssDetails> lstHfmFfssDetails) {
		this.lstHfmFfssDetails = lstHfmFfssDetails;
	}

	public int getCurrYear() {
		return Year.now().getValue();
	}

	public String getTitle() {
		return "RollUps";
	}

	public List<ViewRollupMatchFFSS> getLstMatchAcc() {
		return lstMatchAcc;
	}

	public void setLstMatchAcc(List<ViewRollupMatchFFSS> lstMatchAcc) {
		this.lstMatchAcc = lstMatchAcc;
	}

	public ViewRollupMatchFFSS getCurMacthAcc() {
		return curMacthAcc;
	}

	public void setCurMacthAcc(ViewRollupMatchFFSS curMacthAcc) {
		this.curMacthAcc = curMacthAcc;
	}

	public List<ViewFFSSGrouped> getLstSumFS() {
		return lstSumFS;
	}

	public void setLstSumFS(List<ViewFFSSGrouped> lstSumFS) {
		this.lstSumFS = lstSumFS;
	}

	public ViewFFSSGrouped getCurFSgroup() {
		return curFSgroup;
	}

	public void setCurFSgroup(ViewFFSSGrouped curFSgroup) {
		this.curFSgroup = curFSgroup;

		LOG.info("Obtains curFSgroup = {}", curFSgroup);

		int companyid = Integer.valueOf(curFSgroup.getCompanyid());
		String vhfmcode = curFSgroup.getHfmcode();
		String partnerid = curFSgroup.getPartnerid();
		String costcenter = curFSgroup.getCostcenter();
		String accountid = curFSgroup.getAccountid();

		try {

			LOG.info("Query FFSS Details LIST with company = {}", companyid);

			this.lstHfmFfssDetails = hfmFfssDetailsService
					.findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartnerid(companyid, vhfmcode,
							costcenter, accountid, partnerid);

			LOG.info("return lstHfmFfssDetails with items => {}",
					lstHfmFfssDetails != null ? lstHfmFfssDetails.size() : "is null");

			if (this.lstHfmFfssDetails == null || this.lstHfmFfssDetails.isEmpty()) {
				String mensaje = String.format("No records found ( FFSS Details )for companyId=%s", companyid);
				LOG.info(mensaje);
				// Functions.addWarnMessage("Attention", mensaje);
			} else {
				LOG.info("Records for lstHfmFfssDetails = {}", lstHfmFfssDetails);

				this.lstHfmFfssDetails.forEach(i -> LOG.info(i != null ? i.toString() : "item is null!!!"));
			}
		} catch (Exception e) {
			LOG.error("ERRor -> {}", e.getMessage());
		}

//		LOG.info("Update view FFSS Details ...");
//		PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:tabHHFFDetail");

	}

	public List<HfmLayout> getLstlayout() {
		return lstlayout;
	}

	public void setLstlayout(List<HfmLayout> lstlayout) {
		this.lstlayout = lstlayout;
	}

	public HfmLayout getCurlayout() {
		return curlayout;
	}

	public void setCurlayout(HfmLayout curlayout) {
		this.curlayout = curlayout;
	}

	public void periodChange() {
		try {

			LOG.info("periodchange company");

		} catch (Exception e) {
			LOG.error("period change ERRor -> {}", e.getMessage());
		}

	}

	/**
	 * 
	 * @param process
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
	 * @return
	 */
	public String submitToFFSS() {

		Long companyId = curRollUp.getCompanyid();

		try {
			LOG.info("Query HFM_FFSS with company = {}", companyId);
			this.lstHfmFfss = hfmFfSsService.findByCompanyId(companyId);

			LOG.info("return lstHfmFfss with items => {}", lstHfmFfss != null ? lstHfmFfss.size() : "is null");

			LOG.info("Query MATCH ACCOUNT LIST with company = {}", companyId);

			this.lstMatchAcc = matchaccService.findByCompanyid(companyId);
			LOG.info("return lstMatchAcc with items => {}", lstMatchAcc != null ? lstMatchAcc.size() : "is null");

			if (this.lstMatchAcc == null || this.lstMatchAcc.isEmpty()) {
				String mensaje = String.format("No Match account records found for companyId=%s", companyId);
				LOG.info(mensaje);
				// Functions.addWarnMessage("Attention", mensaje);
			} else {
				LOG.info("Records for lstMatchAcc = {}", lstMatchAcc);
			}

			/*
			 * LOG.info("Query lstlayout LIST with company = {}", companyId);
			 * 
			 * this.lstlayout = serviceLay.findByIdCompanyid(companyId.intValue());
			 * LOG.info("return lstlayout with items => {}", lstlayout != null ?
			 * lstlayout.size() : "is null");
			 * 
			 * if (this.lstlayout == null || this.lstlayout.isEmpty()) { String mensaje =
			 * String.format("No records found for companyId: %s", companyId);
			 * LOG.info(mensaje); // Functions.addWarnMessage("Attention", mensaje); } else
			 * { LOG.info("Records for lstlayout = {}", lstlayout); }
			 */
		} catch (Exception e) {
			LOG.error("ERROR in setCurRollUp -> {}", e.getMessage());
		}

		LOG.info("Redirecting to {}....", FFSS);
		return FFSS;
	}

	/**
	 * 
	 * @return
	 */
	public String submitToSumary() {

		Long companyid = curHfmFfss.getcompanyId();
		String hfmcode = curHfmFfss.getHfmcode().toString();

		try {
			LOG.info("Query SUM FFSS  LIST with company = {}, hfmcode = {}", companyid, hfmcode);

			this.lstSumFS = serviceFSG.findByCompanyidAndHfmcode(companyid.toString(), hfmcode);

			// LOG.info("return lstFSgrouped with items => {}", lstFSgrouped != null ?
			// lstFSgrouped.size() : "is null");
			LOG.info("return lstFSgrouped with items => {}", lstSumFS != null ? lstSumFS.size() : "is null");

			if (this.lstSumFS == null || this.lstSumFS.isEmpty()) {
				String mensaje = String.format("No records found (Sumarized FFSS )for companyId=%s", companyid);
				LOG.info(mensaje);
				// Functions.addWarnMessage("Attention", mensaje);
			} else {
				LOG.info("Records for lstSumFS = {}", lstSumFS);

				// this.lstSumFS.forEach(i -> LOG.info( i != null ? i.toString() : "item is
				// null!!!"));
			}
		} catch (Exception e) {
			LOG.error("setCurHfmFfss ERRor -> {}", e.getMessage());
		}

		LOG.info("Redirecting to {}....", SUMARY);
		return SUMARY;
	}

	/**
	 * 
	 * @return
	 */
	public String submitToMovements() {
		LOG.info("Redirecting to {}....", MOVEMENTS);
		return MOVEMENTS;
	}

	/**
	 * 
	 * @return
	 */
	public String submitToLayouts() {
		// layoutprocess();
		int companyid = curRollUp.getCompanyid().intValue();

		LOG.info("Redirecting to {}....", LAYOUT);
		LOG.info("Initializing lstLayout...");
		// this.lstlayout = serviceLay.findAll();

		LOG.info("Query lstlayout LIST with company = {}", companyid);

		this.lstlayout = serviceLay.findByIdCompanyid(companyid);
		LOG.info("return lstlayout with items => {}", lstlayout != null ? lstlayout.size() : "is null");

		if (this.lstlayout == null || this.lstlayout.isEmpty()) {
			String mensaje = String.format("No records found for companyId: %s", companyid);
			LOG.info(mensaje);
		} else {
			LOG.info("Records for lstlayout = {}", lstlayout);
		}

		return LAYOUT;
	}

	/**
	 * 
	 * @param rollup
	 * @param companyId
	 * @param numDrill
	 * @return
	 */
	private Thread createRollUpTread(ProcessRollUps rollup) {
		LOG.info("create thread for ProcessRollUps => {}", rollup);
		Thread thead = new Thread(rollup);
		thead.setName(rollup.getProcessId());
		LOG.info("Return with thead => {}", thead);
		return thead;
	}

	private String getMonth(int month) {
		return String.format("%02d", month);
	}

	public String getWebSocketEndPoint() {
		return WebSocketConfig.WS_ROLLUPS_ENDPOINT;
	}

	public String getWebSocketTopic() {
		return WebSocketConfig.WS_ROLLUPS_TOPIC;
	}

	public String getWebSocketApp() {
		return WebSocketConfig.WS_ROLLUPS_APP;
	}

	public String getWebSocketMapping() {
		return WebSocketConfig.WS_ROLLUPS_MAPPING;
	}

	public String getContextPath() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}
	
//	private void sendPushNotification(HfmRollupEntries rollup) {
//		sendPushNotification("","","",rollup);
//	}
//
//	/**
//	 * Sends a Push notification to all clients for update the status of the current rollup
//	 * @param message .- Optional message to show in notification browser
//	 * @param title .- Title of the optional  message
//	 * @param severity . Severity of message (info, warn, error)
//	 * @param rollup .- Current rollup been processed.
//	 */
//	private void sendPushNotification(String message, String title, String severity, HfmRollupEntries rollup) {
//		Optional<HfmRollupEntries> ru = service.findById(rollup.getCompanyid());
//		RollUpMessage rum = new RollUpMessage(message, title, severity, ru.orElse(rollup));
//		webSocketService.notyfyRollUpProcess(rum);
//	}
}
