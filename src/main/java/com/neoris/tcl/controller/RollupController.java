package com.neoris.tcl.controller;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmFfss;
import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.services.IHfmFfssService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ProcessRollUps;
import com.neoris.tcl.utils.ViewScope;

import static com.neoris.tcl.services.IHfmRollupEntriesService.*;

@Controller(value = "rollupControllerBean")
@Scope(ViewScope.VIEW)
public class RollupController {

	private final static Logger LOG = LoggerFactory.getLogger(RollupController.class);

	private List<HfmRollupEntries> lstRollUps;
	private List<HfmRollupEntries> lstSelectedRollups;
	private HfmRollupEntries curRollUp;
	private List<String> lstMonths;
	private List<HfmFfss> lstHfmFfss;

	@Autowired
	private IHfmRollupEntriesService service;

	@Autowired
	private IHfmFfssService hfmFfSsService;

	@PostConstruct
	public void init() {

		// Fill the rollup entity list
		setLstRollUps(service.findAll());

		// Fill List for months
		lstMonths = Arrays.asList("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC");

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

	public String getImageCemex() {
		return "/resources/img/loading.gif";
	}

	/**
	 * 
	 * @param event
	 */
	public void processSelectedRollUps(ActionEvent event) {
		LOG.info("Running process with rollUpBean = {}, event = {}", lstSelectedRollups, event);
		lstSelectedRollups.stream().forEach(roll -> processRollUp(roll));

		// clean the selected rollups list...
		lstSelectedRollups = null;
		Functions.addInfoMessage("Succes", "RollUps Proceced!!");
		PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:dt-rollup");
		PrimeFaces.current().executeScript("PF('dtRollUps').unselectAllRows()");
	}

	/**
	 * 
	 * @param rollUp
	 */
	private void processRollUp(HfmRollupEntries rollUp) {

		Thread payablesThread = null;
		Thread receivablesThread = null;
		Thread payrollThread = null;
		Thread assetsThread = null;
		Thread otherThread = null;

		LOG.info("Now processing rollup = {}", rollUp);
		// 1.- Start RollUp Service.
		service.rollUpStart(rollUp.getCompanyid().intValue(), rollUp.getRperiod(), rollUp.getRyear(), rollUp.getSegment(), "admin");

		LOG.info("Preparing concept rollups...");
		ProcessRollUps rollUpPayables = getProcessRollUpsInstance(rollUp, P_CONCEPT_PAYABLES, 0, false, false);
		ProcessRollUps rollUpReceivables = getProcessRollUpsInstance(rollUp, P_CONCEPT_RECEIVABLES, 0, false, false);
		ProcessRollUps rollUpPayroll = getProcessRollUpsInstance(rollUp, P_CONCEPT_PAYROLL, 0, false, false);
		ProcessRollUps rollUpAssets = getProcessRollUpsInstance(rollUp, P_CONCEPT_ASSET, 0, false, false);
		ProcessRollUps rollUpOther = getProcessRollUpsInstance(rollUp, P_CONCEPT_OTHER, 0, false, false);
		ProcessRollUps rollUpCostManager = getProcessRollUpsInstance(rollUp, P_COSTMANAGER, 0, false, false);


		// 2.- Process  the drills details...
		LOG.info("Preparing threads for rollUp process...");
		try {
			payablesThread = createRollUpTread(rollUpPayables);
			receivablesThread = createRollUpTread(rollUpReceivables);
			payrollThread = createRollUpTread(rollUpPayroll);
			assetsThread = createRollUpTread(rollUpAssets);
			otherThread = createRollUpTread(rollUpOther);
		} catch (Exception e) {
			LOG.error("Error creating threads: {}", e.getMessage(), e);
			return;
		}

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_PAYABLES, rollUp.getCompanyid());
		payablesThread.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_RECEIVABLES, rollUp.getCompanyid());
		receivablesThread.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_PAYROLL, rollUp.getCompanyid());
		payrollThread.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_ASSET, rollUp.getCompanyid());
		assetsThread.start();

		LOG.info("Starting Thread for rollUp process: {}, companyId:{}", P_CONCEPT_OTHER, rollUp.getCompanyid());
		otherThread.start();	
		
		// 3.- wait for finish these process and start Costmanager
		try {
			payablesThread.join();
			receivablesThread.join();
			payrollThread.join();
			assetsThread.join();
			otherThread.join();			
		} catch (InterruptedException e) {
			LOG.error("Error running process: {}", e.getMessage(), e);
		}		
		LOG.info("Thread for rollUp Finish!");
		Thread costmanagerThread = createRollUpTread(rollUpCostManager);
		costmanagerThread.run();
		
		// Wait for process to finish....
		try {
			costmanagerThread.join();
		} catch (InterruptedException e) {
			LOG.error("Error running costmanager: {}", e.getMessage(), e);
		}
		
		// 4.- Run the Drills...
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
			drillRollUp1Tread.join();
			drillRollUp2Tread.join();
			drillRollUp3Tread.join();
			drillRollUp4Tread.join();
			drillRollUp5Tread.join();
			drillRollUp6Tread.join();
			drillRollUp7Tread.join();
			drillRollUp8Tread.join();
			drillRollUp9Tread.join();
		} catch (InterruptedException e) {
			LOG.error("Error running Drills rollup: {}", e.getMessage(), e);
		}
		
		// 5.- Run the validations..
		ProcessRollUps rollUpValidations = getProcessRollUpsInstance(rollUp, "", 0, true, false);
		Thread validationsThread = createRollUpTread(rollUpValidations);
		validationsThread.run();
		
		try {
			validationsThread.join();
		} catch (InterruptedException e) {
			LOG.error("Error running Validations rollup: {}", e.getMessage(), e);
		}
		
		// 6.- Run Match account...
		ProcessRollUps rollUpMatchAccount = getProcessRollUpsInstance(rollUp, "", 0, false, true);
		Thread matchAccountThread = createRollUpTread(rollUpMatchAccount);
		matchAccountThread.run();
		try {
			matchAccountThread.join();
		} catch (InterruptedException e) {
			LOG.error("Error running Match Account rollup: {}", e.getMessage(), e);
		}
		
		LOG.info("Finish processing rollups!!");
	}

	/**
	 * 
	 * @param event
	 */
	public void viewRollUp(ActionEvent event) {
		LOG.info("eneting to view detail of rollUpBean = {}, event = {}", this.curRollUp, event);
		PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:dt-rollup");
		PrimeFaces.current().executeScript("PF('dtRollUps').unselectAllRows()");
	}

	public List<HfmRollupEntries> getLstRollUps() {
		return lstRollUps;
	}

	public void setLstRollUps(List<HfmRollupEntries> lstRollUps) {
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
		LOG.info("Recibo curRollUp = {}", curRollUp);
		this.curRollUp = curRollUp;
		String period = curRollUp.getFullPeriod();

		LOG.info("Query HFM_FFSS with company = {} and period = {}", curRollUp.getCompanyid(), period);
		this.lstHfmFfss = hfmFfSsService.findByCompanyIdAndPeriod(curRollUp.getCompanyid(), period);
		if (this.lstHfmFfss == null || this.lstHfmFfss.isEmpty()) {
			Functions.addWarnMessage("Attention", String.format("No records found for companyId=%s and period=%s", curRollUp.getCompanyid(), period));
		}
		LOG.info("Actualizo vista...");
		PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:dt-hfm-ffss-details");
	}

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

	public List<String> getLstMonths() {
		return lstMonths;
	}

	public void setLstMonths(List<String> lstMonths) {
		this.lstMonths = lstMonths;
	}

	public List<HfmFfss> getLstHfmFfss() {
		return lstHfmFfss;
	}

	public void setLstHfmFfss(List<HfmFfss> lstHfmFfss) {
		this.lstHfmFfss = lstHfmFfss;
	}

	public int getCurrYear() {
		return Year.now().getValue();
	}

	public String getTitle() {
		return "RollUps";
	}

	/**
	 * 
	 * @param process
	 * @return
	 */
	private ProcessRollUps getProcessRollUpsInstance(HfmRollupEntries rollUp, String process, int numDrill, boolean processValidations, boolean matchAccounts) {
		ProcessRollUps rollup = new ProcessRollUps(rollUp, this.service, process, numDrill, processValidations, matchAccounts);
		rollup.setFacesContext(FacesContext.getCurrentInstance());
		rollup.setPrimefaces(PrimeFaces.current());
		return rollup;
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

}
