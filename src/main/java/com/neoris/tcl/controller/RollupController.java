package com.neoris.tcl.controller;

import java.time.Year;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeListener;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmFfss;
import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.services.IHfmFfssService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "rollupControllerBean")
@Scope(ViewScope.VIEW)
public class RollupController {

    private final static Logger LOG = LoggerFactory.getLogger(RollupController.class);

    @Autowired
    @Qualifier("mapMonths")
    private Map<String, Integer> months;
    private Map<String, Integer> mapEntities;
    
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

        setLstRollUps(service.findAll());

        LOG.info("months = {}", months);

        mapEntities = new HashMap<String, Integer>();
        for (int i = 1; i <= 10; i++) {
            mapEntities.put(String.format("Entity %s", i), i);
        }
        
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

    public void processSelectedRollUps(ActionEvent event) {
        LOG.info("Running process with rollUpBean = {}, event = {}", lstSelectedRollups, event);
        lstSelectedRollups = null;
        Functions.addInfoMessage("Succes", "RollUps Proceced!!");
        PrimeFaces.current().ajax().update( "rollupForm:messages", "rollupForm:dt-rollup");
        PrimeFaces.current().executeScript("PF('dtRollUps').unselectAllRows()");
    }
    
    public void viewRollUp(ActionEvent event) {
        LOG.info("eneting to view detail of rollUpBean = {}, event = {}", this.curRollUp, event);
        PrimeFaces.current().ajax().update( "rollupForm:messages", "rollupForm:dt-rollup");
        PrimeFaces.current().executeScript("PF('dtRollUps').unselectAllRows()");  
    }

    public Map<String, Integer> getMonths() {
        return months;
    }

    public void setMonths(Map<String, Integer> months) {
        this.months = months;
    }

    public Map<String, Integer> getMapEntities() {
        return mapEntities;
    }

    public void setMapEntities(Map<String, Integer> mapEntities) {
        this.mapEntities = mapEntities;
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

    public void setCurRollUp(HfmRollupEntries curRollUp) {
        LOG.info("Recibo curRollUp = {}", curRollUp);
        this.curRollUp = curRollUp;
        String period = curRollUp.getRperiod() + "-" + Integer.toString(curRollUp.getRyear()).substring(2) ;

        LOG.info("Query HFM_FFSS with company = {} and period = {}", curRollUp.getCompanyid(), period);
        this.lstHfmFfss = hfmFfSsService.findByCompanyIdAndPeriod(curRollUp.getCompanyid(), period);
        if(this.lstHfmFfss == null || this.lstHfmFfss.isEmpty()) {
            Functions.addWarnMessage("Attention", 
                    String.format("No records found for companyId=%s and period=%s", curRollUp.getCompanyid(), period));
        }
        LOG.info("Lista = {}", this.lstHfmFfss);
        LOG.info("Actualizo vista...");
        PrimeFaces.current().ajax().update( "rollupForm:messages", "rollupForm:dt-hfm-ffss-details");
    }

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

    public int validateYear(ValueChangeListener listener) {
        return 0;        
    }
}
