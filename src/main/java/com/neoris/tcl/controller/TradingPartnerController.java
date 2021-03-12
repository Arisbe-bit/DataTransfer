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

import com.neoris.tcl.models.SetIcpcodes;
import com.neoris.tcl.services.ISetIcpcodesService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "tradingpartnerControllerBean")
@Scope(ViewScope.VIEW)
public class TradingPartnerController {

    private final static Logger LOG = LoggerFactory.getLogger(TradingPartnerController.class);

    @Autowired
    private ISetIcpcodesService service;

    private List<SetIcpcodes> lstTP;
    private List<SetIcpcodes> lstSelectdTP;
    private SetIcpcodes curtp; // actual iterator
    private boolean newCode;

    @PostConstruct
    public void init() {
        LOG.info("Initializing lstTradingPartner...");
        this.lstTP = service.findAll();
    }

    public void openNew() {
        this.newCode = true;
        this.curtp = new SetIcpcodes();
    }

    /**
     * 
     * @param event
     */
    public void save(ActionEvent event) {
        LOG.info("Entering to save Trading Partner Type => {}, event ={}", this.curtp, event);
        
        if(newCode) {
            Optional<SetIcpcodes> icp = service.findById(curtp.getId());
            if(icp.isPresent()) {
                String errorMessage = String.format("The record with ICPid = %s and ICPCode = %s already exist. Can't create new record.", curtp.getId().getIcpid(), curtp.getId().getIcpcode()); 
                Functions.addErrorMessage("Error adding new Code", errorMessage);
                PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
                return;
            }
        }
        
        this.curtp = service.save(curtp);
        Functions.addInfoMessage("Succes", "Trading Partner Type saved");

        this.lstTP = service.findAll();
        if (this.lstTP != null)
            LOG.info("La lista viene con {} registros. 1ro={}", lstTP.size(), lstTP.get(0));

        PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }

    public void delete(ActionEvent event) {
        LOG.info("Entering to delete Trading Partner Type => {}", this.curtp);
        service.delete(this.curtp);
        this.curtp = null;
        this.lstTP = service.findAll();
        Functions.addInfoMessage("Succes", "Code Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }

    public void deleteSelected(ActionEvent event) {
        LOG.info("[deleteSelected] = > Entering to delete Trading Partner Type: {}", this.lstSelectdTP);
        service.deleteAll(this.lstSelectdTP);
        this.lstSelectdTP = null;
        this.lstTP = service.findAll();
        Functions.addInfoMessage("Succes", "Trading Partner Type Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }

    public boolean hasSelectedCodes() {
        return this.lstSelectdTP != null && !this.lstSelectdTP.isEmpty();
    }

    public String getDeleteButtonMessage() {
        String message = "Delete %s code%s selected";
        String retval = "Delete";
        if (hasSelectedCodes()) {
            int size = this.lstSelectdTP.size();
            if (size > 1) {
                retval = String.format(message, size, "s");
            } else {
                retval = String.format(message, size, "");
            }
        }
        return retval;
    }

    public SetIcpcodes getCurtp() {
        return curtp;
    }

    public void setCurtp(SetIcpcodes curtp) {
        this.newCode = false;
        this.curtp = curtp;
        LOG.info("Recibo curtp = {}", curtp);
    }

    public List<SetIcpcodes> getLstTP() {
        return lstTP;
    }

    public void setLstTP(List<SetIcpcodes> lstTP) {
        this.lstTP = lstTP;
    }

    public List<SetIcpcodes> getLstSelectdTP() {
        return lstSelectdTP;
    }

    public void setLstSelectdTP(List<SetIcpcodes> lstSelectdTP) {
        this.lstSelectdTP = lstSelectdTP;
    }

    public String getTitle() {
        return "Trading Partner Type Setting";
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
