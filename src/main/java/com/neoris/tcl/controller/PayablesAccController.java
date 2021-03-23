package com.neoris.tcl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.SetPayablesIcp;
import com.neoris.tcl.models.ViewPartnersICP;
import com.neoris.tcl.services.ISetPayablesIcpService;
import com.neoris.tcl.services.IViewPartnersICPService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "payablesaccControllerBean")
@Scope(ViewScope.VIEW)
public class PayablesAccController {

    private final static Logger LOG = LoggerFactory.getLogger(PayablesAccController.class);

    @Autowired
    private ISetPayablesIcpService service;
    @Autowired
    private IViewPartnersICPService servicesupplierview;
    private List<ViewPartnersICP> lstpay;
    private List<ViewPartnersICP> lstSelectdPay;

    private List<SetPayablesIcp> lstSelectdPaytab;
    private ViewPartnersICP currentPay;
    private SetPayablesIcp currentPaytab;

    @PostConstruct
    public void init() {
        LOG.info("Initializing lstPayablesicp...");
        this.lstpay = servicesupplierview.findAll();
        LOG.info("reg= {}", lstpay.size());
    }

    public void openNew() {
        this.currentPaytab = new SetPayablesIcp();
    }

    public void save() {
        LOG.info("Entering to save Payables Acc  => {}", this.currentPaytab);
        this.currentPaytab = service.save(currentPaytab);
        this.lstpay = servicesupplierview.findAll();
        Functions.addInfoMessage("Succes", " Payables Acc saved");
        PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }

    public void delete() {
        LOG.info("Entering to delete Trading Partner  => {}", this.currentPaytab);
        service.delete(this.currentPaytab);
        this.currentPaytab = null;
        this.lstpay = servicesupplierview.findAll();
        Functions.addInfoMessage("Succes", "Code Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }

    public void deleteSelected(ActionEvent event) {
        LOG.info("[deleteSelected] = > Entering to delete Trading Partner : {}", this.lstSelectdPaytab);
        service.deleteAll(this.lstSelectdPaytab);
        this.lstSelectdPay = null;
        this.lstpay = servicesupplierview.findAll();
        Functions.addInfoMessage("Succes", "Payables Account Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }

    public void update() {
        LOG.info("Entering to update Payables Acc  => {}", currentPay);
        save();
    }

    public boolean hasSelectedCodes() {
        return this.lstSelectdPay != null && !this.lstSelectdPay.isEmpty();
    }

    public String getDeleteButtonMessage() {
        String message = "Delete %s record%s selected";
        String retval = "Delete";
        if (hasSelectedCodes()) {
            int size = this.lstSelectdPay.size();
            if (size > 1) {
                retval = String.format(message, size, "s");
            } else {
                retval = String.format(message, size, "");
            }
        }
        return retval;
    }

    public String getTitle() {
        return "Accounts Payable Trading Partner";
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

    public List<ViewPartnersICP> getLstpay() {
        return lstpay;
    }

    public void setLstpay(List<ViewPartnersICP> lstpay) {
        this.lstpay = lstpay;
    }

    public List<ViewPartnersICP> getLstSelectdPay() {
        return lstSelectdPay;
    }

    public void setLstSelectdPay(List<ViewPartnersICP> lstSelectdPay) {
        this.lstSelectdPay = lstSelectdPay;

        this.lstSelectdPaytab = new ArrayList<SetPayablesIcp>();

        for (ViewPartnersICP viewPartnersICP : lstSelectdPay) {
            SetPayablesIcp currentPaytabx = new SetPayablesIcp();

            currentPaytabx.getId().setCompanyid(new Long(viewPartnersICP.getorganization_id()));
            currentPaytabx.getId().setSupplierno(viewPartnersICP.getSupplier_num());
            currentPaytabx.setIcpcode(viewPartnersICP.getIcpcode());

            lstSelectdPaytab.add(currentPaytabx);
        }

    }

    public List<SetPayablesIcp> getLstSelectdPaytab() {
        return lstSelectdPaytab;
    }

    public void setLstSelectdPaytab(List<SetPayablesIcp> lstSelectdPaytab) {
        this.lstSelectdPaytab = lstSelectdPaytab;
    }

    public ViewPartnersICP getCurrentPay() {
        return currentPay;
    }

    public void setCurrentPay(ViewPartnersICP currentPay) {
        this.currentPay = currentPay;
        this.currentPaytab = new SetPayablesIcp();
        this.currentPaytab.getId().setCompanyid(new Long(currentPay.getorganization_id()));
        this.currentPaytab.getId().setSupplierno(currentPay.getSupplier_num());
        this.currentPaytab.setIcpcode(currentPay.getIcpcode());
    }

    public SetPayablesIcp getCurrentPaytab() {
        return currentPaytab;
    }

    public void setCurrentPaytab(SetPayablesIcp currentPaytab) {
        this.currentPaytab = currentPaytab;
    }

}
