package com.neoris.tcl.controller;

import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_ASSET;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_OTHER;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_PAYABLES;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_PAYROLL;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_CONCEPT_RECEIVABLES;
import static com.neoris.tcl.services.IHfmRollupEntriesService.P_COSTMANAGER;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.PrimeFaces;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmFfss;
import com.neoris.tcl.models.HfmFfssDetails;
import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.models.ViewRollupMacthFFSS;
import com.neoris.tcl.services.IHfmFfssDetailsService;
import com.neoris.tcl.services.IHfmFfssService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.services.IViewRollupMatchFFSSService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ProcessRollUps;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "rollupControllerBean")
@Scope(ViewScope.VIEW)
public class RollupController {

    private final static Logger LOG = LoggerFactory.getLogger(RollupController.class);
    private final static String DT_ROLLUP = "rollupForm:tabViewRollUps:dt-rollup"; 

    private List<HfmRollupEntries> lstRollUps;
    private List<HfmRollupEntries> lstSelectedRollups;
    private HfmRollupEntries curRollUp;

    private List<HfmFfss> lstHfmFfss;
    private HfmFfss curHfmFfss;

    private List<HfmFfssDetails> lstHfmFfssDetails;
    
    private List<ViewRollupMacthFFSS> lstMatchAcc;
 	private ViewRollupMacthFFSS curMacthAcc;

    @Autowired
    private IHfmRollupEntriesService service;
    @Autowired
    private IHfmFfssService hfmFfSsService;
    @Autowired
    private IHfmFfssDetailsService hfmFfssDetailsService;
    @Autowired
    private IViewRollupMatchFFSSService matchaccService;
    
    @PostConstruct
    public void init() {
        // Fill the rollup entity list
        setLstRollUps(service.findAll());
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

        // Initilize status of each rollup pending for processing...
        lstSelectedRollups.stream().forEach(roll -> roll.pending());
        PrimeFaces.current().ajax().update(DT_ROLLUP);

        for (HfmRollupEntries rollup : lstSelectedRollups) {
            // buscamos el rollup y lo procesamos desde la lista original
            // de otro modo, no se refrescan los mensajes.
            int idx = this.lstRollUps.indexOf(rollup);
            processRollUp(this.lstRollUps.get(idx));
            PrimeFaces.current().ajax().update(getFormNameId() + ":messages", DT_ROLLUP);
        }

        // clean the selected rollups list...
        lstSelectedRollups = null;
        Functions.addInfoMessage("Succes", "RollUps Proceced!!");
        PrimeFaces.current().executeScript("PF('dtRollUps').unselectAllRows()");
        PrimeFaces.current().ajax().update(getFormNameId() + ":messages", DT_ROLLUP);
    }

    /**
     * 
     * @param rollUp
     */
    private void processRollUp(HfmRollupEntries rollUp) {

        LOG.info("Now processing rollup = {}", rollUp);
        // 1.- Start RollUp Service.
        rollUp.setAttribute1(HfmRollupEntries.STATUS_PROCESSING);
        PrimeFaces.current().ajax().update(DT_ROLLUP);
        LOG.info("Processing Roolup Start ");
        service.rollUpStart(rollUp.getCompanyid().intValue(), rollUp.getRperiod(), rollUp.getRyear(), rollUp.getSegment1(), "admin");
        rollUp.setAttribute1(HfmRollupEntries.STATUS_OK);
        PrimeFaces.current().ajax().update(DT_ROLLUP);

        // 2.- Process Drill Details
        processDrillDetails(rollUp);

        // 3.- Process Cost Manager
        //processCostManager(rollUp);//getheader

        // 4.- Run the Drills...
       // processDrils(rollUp);

        // 5.- Run the validations..
        processValidations(rollUp);

        // 6.- Run Match account...
        processMatchAccount(rollUp);

        LOG.info("Finish processing rollups!!");
    }

    private void processMatchAccount(HfmRollupEntries rollUp) {
        rollUp.setAttribute6(HfmRollupEntries.STATUS_PROCESSING);
        PrimeFaces.current().ajax().update(DT_ROLLUP);

        ProcessRollUps rollUpMatchAccount = getProcessRollUpsInstance(rollUp, "", 0, false, true);
        Thread matchAccountThread = createRollUpTread(rollUpMatchAccount);
        matchAccountThread.run();
        try {
            matchAccountThread.join();
            rollUp.setAttribute6(HfmRollupEntries.STATUS_OK);
        } catch (InterruptedException e) {
            LOG.error("Error running Match Account rollup: {}", e.getMessage(), e);
            rollUp.setAttribute6(HfmRollupEntries.STATUS_ERROR);
        }

        PrimeFaces.current().ajax().update(DT_ROLLUP);
    }

    private void processValidations(HfmRollupEntries rollUp) {
        rollUp.setAttribute5(HfmRollupEntries.STATUS_PROCESSING);
        PrimeFaces.current().ajax().update(DT_ROLLUP);

        ProcessRollUps rollUpValidations = getProcessRollUpsInstance(rollUp, "", 0, true, false);
        Thread validationsThread = createRollUpTread(rollUpValidations);
        validationsThread.run();

        try {
            validationsThread.join();
            rollUp.setAttribute5(HfmRollupEntries.STATUS_OK);
        } catch (InterruptedException e) {
            LOG.error("Error running Validations rollup: {}", e.getMessage(), e);
            rollUp.setAttribute5(HfmRollupEntries.STATUS_ERROR);
        }
        PrimeFaces.current().ajax().update(DT_ROLLUP);
    }

    /**
     * Step process 3
     * 
     * @param rollUp
     */
    private void processCostManager(HfmRollupEntries rollUp) {

        ProcessRollUps rollUpCostManager = getProcessRollUpsInstance(rollUp, P_COSTMANAGER, 0, false, false);
        rollUp.setAttribute3(HfmRollupEntries.STATUS_PROCESSING);

        PrimeFaces.current().ajax().update(DT_ROLLUP);
        Thread costmanagerThread = createRollUpTread(rollUpCostManager);
        costmanagerThread.run();

        // Wait for process to finish....
        try {
            costmanagerThread.join();
            rollUp.setAttribute3(HfmRollupEntries.STATUS_OK);
        } catch (InterruptedException e) {
            LOG.error("Error running costmanager: {}", e.getMessage(), e);
            rollUp.setAttribute3(HfmRollupEntries.STATUS_ERROR);
        }
        PrimeFaces.current().ajax().update(DT_ROLLUP);
    }

    /**
     * 
     * @param rollUp
     */
    private void processDrils(HfmRollupEntries rollUp) {
        rollUp.setAttribute4(HfmRollupEntries.STATUS_PROCESSING);
        PrimeFaces.current().ajax().update(DT_ROLLUP);

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
          //  drillRollUp9Tread.sleep(5000);
            drillRollUp1Tread.join();
            drillRollUp2Tread.join();
            drillRollUp3Tread.join();
            drillRollUp4Tread.join();
            drillRollUp5Tread.join();
            drillRollUp6Tread.join();
            drillRollUp7Tread.join();
            drillRollUp8Tread.join();
            drillRollUp9Tread.join();
            rollUp.setAttribute4(HfmRollupEntries.STATUS_OK);
        } catch (InterruptedException e) {
            LOG.error("Error running Drills rollup: {}", e.getMessage(), e);
            rollUp.setAttribute4(HfmRollupEntries.STATUS_ERROR);
        }
        PrimeFaces.current().ajax().update(DT_ROLLUP);
    }

    /**
     * 
     * @param rollUp
     */
    private void processDrillDetails(HfmRollupEntries rollUp) {
        LOG.info("Preparing concept rollups...");
        rollUp.setAttribute2(HfmRollupEntries.STATUS_PROCESSING);
        PrimeFaces.current().ajax().update(DT_ROLLUP);

        Thread payablesThread = null;
        Thread receivablesThread = null;
        Thread payrollThread = null;
        Thread assetsThread = null;
        Thread otherThread = null;

        ProcessRollUps rollUpPayables = getProcessRollUpsInstance(rollUp, P_CONCEPT_PAYABLES, 0, false, false);
        LOG.info("******rollUpPayables*****" + rollUpPayables);
        ProcessRollUps rollUpReceivables = getProcessRollUpsInstance(rollUp, P_CONCEPT_RECEIVABLES, 0, false, false);
        ProcessRollUps rollUpPayroll = getProcessRollUpsInstance(rollUp, P_CONCEPT_PAYROLL, 0, false, false);
        ProcessRollUps rollUpAssets = getProcessRollUpsInstance(rollUp, P_CONCEPT_ASSET, 0, false, false);
        ProcessRollUps rollUpOther = getProcessRollUpsInstance(rollUp, P_CONCEPT_OTHER, 0, false, false);

        // 2.- Process the drills details...
        LOG.info("Preparing threads for rollUp process...");
        try {
            payablesThread = createRollUpTread(rollUpPayables);
            receivablesThread = createRollUpTread(rollUpReceivables);
            payrollThread = createRollUpTread(rollUpPayroll);
            assetsThread = createRollUpTread(rollUpAssets);
            otherThread = createRollUpTread(rollUpOther);
        } catch (Exception e) {
            LOG.error("Error creating threads: {}", e.getMessage(), e);
            rollUp.setAttribute2(HfmRollupEntries.STATUS_ERROR);
            PrimeFaces.current().ajax().update(DT_ROLLUP);
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
            //otherThread.sleep(5000);
            payablesThread.join();
            receivablesThread.join();
            payrollThread.join();
            assetsThread.join();
            otherThread.join();
            rollUp.setAttribute2(HfmRollupEntries.STATUS_OK);
        } catch (InterruptedException e) {
            LOG.error("Error running process: {}", e.getMessage(), e);
            rollUp.setAttribute2(HfmRollupEntries.STATUS_ERROR);
        }
        PrimeFaces.current().ajax().update(DT_ROLLUP);
        LOG.info("Thread for rollUp Finish!");
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
        lstRollUps.forEach(r -> r.clean());
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
        String period = curRollUp.getRperiod();
        String ryear =  curRollUp.getRyear();
        Long companyId = curRollUp.getCompanyid();

        LOG.info("Query HFM_FFSS with company = {} and period = {}", companyId, period);        
        this.lstHfmFfss = hfmFfSsService.findByCompanyIdAndPeriod(companyId, period + "-" + String.valueOf(ryear));
                
        if (this.lstHfmFfss == null || this.lstHfmFfss.isEmpty()) {
            Functions.addWarnMessage("Attention",
                    String.format("No records found for companyId=%s and period=%s", companyId, period));
        }
        LOG.info("Query MATCH ACCOUNT LIST with company = {}", companyId);
        this.lstMatchAcc = matchaccService.findByCompanyid(companyId);

        if (this.lstMatchAcc == null || this.lstMatchAcc.isEmpty()) {
            String mensaje = String.format("No Match account records found for companyId=%s", companyId);
            LOG.info(mensaje);
            Functions.addWarnMessage("Attention", mensaje);
        } else {
            LOG.info("Records for lstMatchAcc = {}", lstMatchAcc);
        }
//        LOG.info("Update view...");
//        PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:tabViewRollUps:dt-hfm-tab-ffss", "rollupForm:tabViewRollUps:dt-macthacc");
        
    }
    public void setRollUpOnRowClick(HfmRollupEntries rollUp) {
        LOG.info("Recibo curRollUp = {}. Buscando compañías", rollUp);
    }
    public HfmFfss getCurHfmFfss() {
        return curHfmFfss;
    }

    /**
     * 
     * @param curHfmFfss
     */
    public void setCurHfmFfss(HfmFfss curHfmFfss) {
        LOG.info("Recibo curHfmFfss = {}", curHfmFfss);
        this.curHfmFfss = curHfmFfss;

        LOG.info("Query HFM_FFSS_DETAIL with company = {}, hfmcode = {}, period = {}",
                curHfmFfss.getId().getCompanyId(), curHfmFfss.getId().getHfmcode(), curHfmFfss.getId().getPeriod());
        this.lstHfmFfssDetails = hfmFfssDetailsService.findByIdCompanyidAndHfmparentAndPeriodname(
                curHfmFfss.getId().getCompanyId(), curHfmFfss.getId().getHfmcode(), curHfmFfss.getId().getPeriod());

        if (this.lstHfmFfssDetails == null || this.lstHfmFfssDetails.isEmpty()) {
            Functions.addWarnMessage("Attention",
                    String.format("No records found for companyId=%s, hfmcode=%s and period=%s",
                            curHfmFfss.getId().getCompanyId(), curHfmFfss.getId().getHfmcode(),
                            curHfmFfss.getId().getPeriod()));
        }

        LOG.info("Actualizo vista...");
        PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:tabViewRollUps:dt-hfm-tab-ffss-details");
    }

    /**
     * 
     * @param event
     */
    public void curHfmffssClic(AjaxBehaviorEvent event) {
        LOG.info("curHfmffssClic with event = {}", event.getComponent());
    }

    /**
     * This event is fired when user clicks on a row of main data table. 
     * It search the company childs for "Global Validation" Data Table.
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
//            PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:tabViewRollUps:dt-macthacc");
//        } else {
//            LOG.info("Item type is = {}", obj.getClass().getSimpleName());
//        }
//    }

    /**
     * 
     * @param event
     */
    public void onTabChange(TabChangeEvent<?> event) {
        String message = String.format("Active Tab ID:[%s], Title:[%s], Client ID:[%s]", event.getTab().getId(),
                event.getTab().getTitle(), event.getTab().getClientId());
        LOG.info(message);
    }

    /**
     * 
     * @param event
     */
    public void onTabClose(TabCloseEvent<?> event) {
        String message = String.format("Closed tab: %s, client ID = %s", event.getTab().getTitle(),
                event.getTab().getClientId());
        LOG.info(message);
        // Functions.addInfoMessage("Tab Closed", message);
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

	public List<ViewRollupMacthFFSS> getLstMatchAcc() {
		return lstMatchAcc;
	}

	public void setLstMatchAcc(List<ViewRollupMacthFFSS> lstMatchAcc) {
		this.lstMatchAcc = lstMatchAcc;
	}

	public ViewRollupMacthFFSS getCurMacthAcc() {
		return curMacthAcc;
	}

	public void setCurMacthAcc(ViewRollupMacthFFSS curMacthAcc) {
		this.curMacthAcc = curMacthAcc;
	}

	/**
     * 
     * @param process
     * @return
     */
    private ProcessRollUps getProcessRollUpsInstance(HfmRollupEntries rollUp, String process, int numDrill,
            boolean processValidations, boolean matchAccounts) {
        ProcessRollUps rollup = new ProcessRollUps(rollUp, this.service, process, numDrill, processValidations,
                matchAccounts);
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
