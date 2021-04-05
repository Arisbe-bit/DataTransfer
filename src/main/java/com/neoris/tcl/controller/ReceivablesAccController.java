package com.neoris.tcl.controller;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.models.SetReceivablesIcp;
import com.neoris.tcl.models.ViewCustReceivables;
import com.neoris.tcl.models.ViewPartnersRecICP;
import com.neoris.tcl.services.ISetReceivablesIcpService;
import com.neoris.tcl.services.IViewCustReceivablesService;
import com.neoris.tcl.services.IViewPartnersRecICPService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

@Controller(value = "receivablesaccControllerBean")
@Scope(ViewScope.VIEW)
public class ReceivablesAccController {

	
	private final static Logger LOG = LoggerFactory.getLogger(PayablesAccController.class);
	
	@Autowired 
	private ISetReceivablesIcpService service;	
	@Autowired 
	private IViewPartnersRecICPService serviceVRec;
	private List<SetReceivablesIcp> lstSelectdRectab;
	private SetReceivablesIcp currentRecTab;	
	
	// partners view for data table
	private List<ViewPartnersRecICP> lstVRec;	
	private List<ViewPartnersRecICP> lstSelectdVRec;
	private ViewPartnersRecICP currentVRec;
	
	//customer view
	@Autowired
	private IViewCustReceivablesService servicecust;	
	private List<ViewCustReceivables> lstCustno;
	
	//Company
	private List<HfmRollupEntries> lstcompany;
	

	

	@PostConstruct
	public void init() {
        LOG.info("Initializing lstReceivablesicp...");
        this.lstVRec = serviceVRec.findAll();
        LOG.info("reg= {}", lstVRec.size());
        
     
    }

    public void openNew() {
        this.currentRecTab = new SetReceivablesIcp();
        
    }

    public void save() {
        LOG.info("Entering to save Trading Partner  => {}", this.currentRecTab);
        this.currentRecTab = service.save(currentRecTab);
        this.lstVRec = serviceVRec.findAll();
        Functions.addInfoMessage("Succes", " Trading Partner saved");
        PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }

    public void delete() {
        LOG.info("Entering to delete Trading Partner => {}", this.currentRecTab);
        service.delete(this.currentRecTab);
        this.currentRecTab = null;
        this.lstVRec = serviceVRec.findAll();
        Functions.addInfoMessage("Succes", "Code Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }

    public void deleteSelected(ActionEvent event) {
        LOG.info("[deleteSelected] = > Entering to delete Trading Partner : {}", this.lstSelectdRectab);
        service.deleteAll(this.lstSelectdRectab);
        this.lstSelectdRectab = null;
        this.lstVRec = serviceVRec.findAll();
        Functions.addInfoMessage("Succes", "Trading Partner  Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }

    public void companyidChange() {
        LOG.info("payables-company  => {}", this.currentRecTab.getId().getCompanyid());
        lstCustno = servicecust.findByOrganizationid(this.currentRecTab.getId().getCompanyid().intValue());
        LOG.info("Return lstCustno  => {}",lstCustno);
    }

    public boolean hasSelectedCodes() {
        return this.lstSelectdRectab != null && !this.lstSelectdRectab.isEmpty();
        // return this.lstSelectdVRec != null && !this.lstSelectdVRec.isEmpty();
    }

    public String getDeleteButtonMessage() {
        String message = "Delete %s code%s selected";
        String retval = "Delete";
        if (hasSelectedCodes()) {
            int size = this.lstSelectdRectab.size();
            if (size > 1) {
                retval = String.format(message, size, "s");
            } else {
                retval = String.format(message, size, "");
            }
        }
        return retval;
    }

    public String getTitle() {
        return "Accounts Receivable Trading Partner";
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

    public List<SetReceivablesIcp> getLstSelectdRectab() {
        return lstSelectdRectab;
    }

    public void setLstSelectdRectab(List<SetReceivablesIcp> lstSelectdRectab) {
        this.lstSelectdRectab = lstSelectdRectab;
    }

    public SetReceivablesIcp getCurrentRecTab() {
        return currentRecTab;
    }

    public void setCurrentRecTab(SetReceivablesIcp currentRecTab) {
        this.currentRecTab = currentRecTab;
    }

    public List<ViewPartnersRecICP> getLstVRec() {
        return lstVRec;
    }

    public void setLstVRec(List<ViewPartnersRecICP> lstVRec) {
        this.lstVRec = lstVRec;
    }

    public List<ViewPartnersRecICP> getLstSelectdVRec() {
        return lstSelectdVRec;
    }

    public void setLstSelectdVRec(List<ViewPartnersRecICP> lstSelectdVRec) {
        this.lstSelectdVRec = lstSelectdVRec;

        this.lstSelectdRectab = new ArrayList<SetReceivablesIcp>();

        for (ViewPartnersRecICP viewPartnersRecICP : lstSelectdVRec) {
            SetReceivablesIcp currentRectabx = new SetReceivablesIcp();

            currentRectabx.getId().setCompanyid(new Long(viewPartnersRecICP.getOrganization_id()));
            currentRectabx.getId().setCustno(viewPartnersRecICP.getCustno());
            currentRectabx.setIcpcode(viewPartnersRecICP.getIcpcode());

            lstSelectdRectab.add(currentRectabx);
        }
    }

    public ViewPartnersRecICP getCurrentVRec() {
        return currentVRec;
    }

    public void setCurrentVRec(ViewPartnersRecICP currentVRec) {
        this.currentVRec = currentVRec;

        LOG.info("Receivables- company edit  => {}", this.currentVRec.getOrganization_id());
		lstCustno = servicecust.findByOrganizationid(this.currentVRec.getOrganization_id());
		LOG.info("return lstSuppno with items => {}", lstCustno != null ? lstCustno.size() : "is null");

        
        this.currentRecTab = new SetReceivablesIcp();
        this.currentRecTab.getId().setCompanyid(new Long(currentVRec.getOrganization_id()));
        this.currentRecTab.getId().setCustno(currentVRec.getCustno());
        this.currentRecTab.setIcpcode(currentVRec.getIcpcode());
    }

    public List<ViewCustReceivables> getLstCustno() {
        return lstCustno;
    }

    public void setLstCustno(List<ViewCustReceivables> lstCustno) {
        this.lstCustno = lstCustno;
    }
    
    public List<HfmRollupEntries> getLstcompany() {
		return lstcompany;
	}

	public void setLstcompany(List<HfmRollupEntries> lstcompany) {
		this.lstcompany = lstcompany;
	}

}
