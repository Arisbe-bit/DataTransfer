package com.neoris.tcl.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.model.HFMcodes;
import com.neoris.tcl.services.IHFMcodesService;

@Controller(value = "hfmControllerBean")
@Scope("view")
public class HfmController {
    private final static Logger LOG = LoggerFactory.getLogger(HfmController.class);
    
    @Autowired
    private IHFMcodesService service;
    
    private List<HFMcodes> lstHfmcodes;
    private List<HFMcodes> lstSelectdHfmcodes; 
    private HFMcodes hfmcode;
    
    @PostConstruct
    public void init() {
        LOG.info("Initializing lstHfmcodes...");
        this.lstHfmcodes = service.listar();
    }
    
    public void openNew() {
        this.hfmcode = new HFMcodes();
    }
      
    public void save() {
        LOG.info("Entering to save hfmcode => {}", hfmcode);
        hfmcode = service.save(hfmcode);
        this.lstHfmcodes = service.listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("HFMcode Saved"));
        PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
    }
    
    public void delete() {
        LOG.info("Entering to delete hfmcode => {}", this.hfmcode);
        service.delete(this.hfmcode);
        this.hfmcode = null;
        this.lstHfmcodes = service.listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Code Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
    }
    
    public void deleteSelectedCodes() {
        LOG.info("Entering to delete codes: {}", this.lstSelectdHfmcodes);
        service.deleteAll(this.lstSelectdHfmcodes);
        this.lstSelectdHfmcodes = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Codes Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void update() {
        LOG.info("Entering to update hfmcode => {}", hfmcode);
        save();
    }

    public boolean hasSelectedCodes() {
        return this.lstSelectdHfmcodes != null && !this.lstSelectdHfmcodes.isEmpty();
    }

    public String getDeleteButtonMessage() {
        String message = "delete %s code%s selected";
        String retval = "Delete";
        if (hasSelectedCodes()) {
            int size = this.lstSelectdHfmcodes.size();
            if (size > 1) {
                retval = String.format(message, size, "s");
            } else {
                retval = String.format(message, size, "");
            }
        }
        return retval;
    }

    public List<HFMcodes> getLstHfmcodes() {
        return lstHfmcodes;
    }

    public void setLstHfmcodes(List<HFMcodes> lstHfmcodes) {
        this.lstHfmcodes = lstHfmcodes;
    }

    public Optional<HFMcodes> getOptionaHfmcode(String code) {
        return service.listarID(code);
    }
    
    public List<HFMcodes> getLstSelectdHfmcodes() {
        return lstSelectdHfmcodes;
    }

    public void setLstSelectdHfmcodes(List<HFMcodes> lstSelectdHfmcodes) {
        LOG.info("Recibo Lista de seleccionados => {}", lstSelectdHfmcodes);
        this.lstSelectdHfmcodes = lstSelectdHfmcodes;
    }
    
    public HFMcodes getHfmcode() {
        return hfmcode;
    }

    public void setHfmcode(HFMcodes hfmcode) {
        LOG.info("Recibo HFMcodes => {}", hfmcode);
        this.hfmcode = hfmcode;
    }

    public String getTitle() {
        return "HFM Codes Mantaince";
    }
    
    public String getDialogName() {
        return "manageCodeDialog";
    }
    
    public String getDataTableName() {
        return "dt-codes";
    }
}
