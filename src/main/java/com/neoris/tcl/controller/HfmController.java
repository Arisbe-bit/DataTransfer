package com.neoris.tcl.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.SetHfmCodes;
import com.neoris.tcl.services.ISetHfmCodesService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "hfmControllerBean")
@Scope(ViewScope.VIEW)
public class HfmController {
    private final static Logger LOG = LoggerFactory.getLogger(HfmController.class);
    
    @Autowired
    private ISetHfmCodesService service;
    
    private List<SetHfmCodes> lstHfmcodes;
    private List<SetHfmCodes> lstSelectdHfmcodes; 
    private SetHfmCodes hfmcode;
    private boolean isNewHfmcode;
    
    @PostConstruct
    public void init() {
        LOG.info("Initializing lstHfmcodes...");
        this.lstHfmcodes = service.findAll();
    }
    
    public void openNew() {
        this.isNewHfmcode = true;
        this.hfmcode = new SetHfmCodes();
    }
      
    public void save() {
        LOG.info("Entering to save hfmcode => {}", hfmcode);
        
        if(this.isNewHfmcode) {
            Optional<SetHfmCodes> code = service.findById(hfmcode.getHfmcode());
            if(code.isPresent()) {
                String errorMessage = String.format("The record with code = %s already exist with value= %s. Can't create new record.", hfmcode.getHfmcode(), hfmcode.getTptype()); 
                Functions.addErrorMessage("Error adding new HFMCode", errorMessage);
                PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
                return;
            }
        }
        
        hfmcode = service.save(hfmcode);
        this.lstHfmcodes = service.findAll();
        Functions.addInfoMessage("Succes", "HFMCode saved");
        PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void delete() {
        LOG.info("Entering to delete hfmcode => {}", this.hfmcode);
        service.delete(this.hfmcode);
        this.hfmcode = null;
        this.lstHfmcodes = service.findAll();
        Functions.addInfoMessage("Succes", "Code Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void deleteSelected(ActionEvent event) {
        LOG.info("[deleteSelected] = > Entering to delete codes: {}", this.lstSelectdHfmcodes);
        //service.deleteAll(this.lstSelectdHfmcodes);
        service.deleteAll(this.lstSelectdHfmcodes);
        this.lstSelectdHfmcodes = null;
        this.lstHfmcodes = service.findAll();
        Functions.addInfoMessage("Succes", "Codes Removed");
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
        String message = "Delete %s code%s selected";
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

    public List<SetHfmCodes> getLstHfmcodes() {
        return lstHfmcodes;
    }

    public void setLstHfmcodes(List<SetHfmCodes> lstHfmcodes) {
        this.lstHfmcodes = lstHfmcodes;
    }

    public Optional<SetHfmCodes> getOptionaHfmcode(String code) {
        return service.findById(code);
    }
    
    public List<SetHfmCodes> getLstSelectdHfmcodes() {
        return lstSelectdHfmcodes;
    }

    public void setLstSelectdHfmcodes(List<SetHfmCodes> lstSelectdHfmcodes) {
        this.lstSelectdHfmcodes = lstSelectdHfmcodes;
    }
    
    public SetHfmCodes getHfmcode() {
        return hfmcode;
    }

    public void setHfmcode(SetHfmCodes hfmcode) {
        LOG.info("Recibo SetHfmCodes => {}", hfmcode);
        this.isNewHfmcode = false;
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
    
    public String getDeleteCodesButton() {
        return "delete-codes-button-id";
    }
}
