package com.neoris.tcl.controller;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
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
        lstSelectedRollups.stream().forEach(roll -> processRollUp(roll) );

        //clean the selected rollups list...
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
        ProcessRollUps process = new ProcessRollUps();
        process.setRollUp(rollUp);
 
        LOG.info("Openning new Thread for rollUp companyId:{}", rollUp.getCompanyid());
        Thread hilo = new Thread(process);
        hilo.setName("Th-RollUp-" + rollUp.getCompanyid());
        hilo.start();
        LOG.info("Thread for rollUp Finish!");
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
            Functions.addWarnMessage("Attention",
                    String.format("No records found for companyId=%s and period=%s", curRollUp.getCompanyid(), period));
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

}
