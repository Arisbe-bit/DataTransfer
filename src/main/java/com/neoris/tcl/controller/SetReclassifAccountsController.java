package com.neoris.tcl.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.SetReclassifAccounts;
import com.neoris.tcl.services.ISetReclassifAccountsService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "setreclassifaccControllerBean")
@Scope(ViewScope.VIEW)
public class SetReclassifAccountsController {
	
private final static Logger LOG = LoggerFactory.getLogger(TradingTypeController.class);
	
	@Autowired
	private ISetReclassifAccountsService service;
	
	private List<SetReclassifAccounts> lstref;
	private List<SetReclassifAccounts> lstSlctdref;
	private SetReclassifAccounts curracc;
	
	 @PostConstruct
	    public void init() {
	        LOG.info("Initializing lstReclasssifAccounts...");
	        this.lstref = service.findAll();
	    }
	    
	    public void openNew() {
	        this.curracc = new SetReclassifAccounts();
	    }
	      
	    public void save() {
	        LOG.info("Entering to save Account => {}", this.curracc);
	        this.curracc = service.save(curracc);
	        this.lstref = service.findAll();
	        Functions.addInfoMessage("Succes", "Accounts saved");
	        PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
	        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
	        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	    }
	    
	    public void delete() {
	        LOG.info("Entering to delete Account => {}", this.curracc);
	        service.delete(this.curracc);
	        this.curracc = null;
	        this.lstref = service.findAll();
	        Functions.addInfoMessage("Succes", "Code Removed");
	        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
	        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	    }
	    
	    public void deleteSelected(ActionEvent event) {
	        LOG.info("[deleteSelected] = > Entering to delete Accounts: {}", this.lstSlctdref);
	        service.deleteAll(this.lstSlctdref);
	        this.lstSlctdref = null;
	        this.lstref = service.findAll();
	        Functions.addInfoMessage("Succes", "Accounts Removed");
	        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
	        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	    }
	    
	    public void update() {
	        LOG.info("Entering to update Account => {}", curracc);
	        save();
	    }

	    public boolean hasSelectedCodes() {
	        return this.lstSlctdref != null && !this.lstSlctdref.isEmpty();
	    }

	    public String getDeleteButtonMessage() {
	        String message = "Delete %s code%s selected";
	        String retval = "Delete";
	        if (hasSelectedCodes()) {
	            int size = this.lstSlctdref.size();
	            if (size > 1) {
	                retval = String.format(message, size, "s");
	            } else {
	                retval = String.format(message, size, "");
	            }
	        }
	        return retval;
	    }
	    
	    public String getTitle() {
	        return "Reclassification Accounts Setting";
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

		public List<SetReclassifAccounts> getLstref() {
			return lstref;
		}

		public void setLstref(List<SetReclassifAccounts> lstref) {
			this.lstref = lstref;
		}

		public List<SetReclassifAccounts> getLstSlctdref() {
			return lstSlctdref;
		}

		public void setLstSlctdref(List<SetReclassifAccounts> lstSlctdref) {
			this.lstSlctdref = lstSlctdref;
		}

		public SetReclassifAccounts getCurracc() {
			return curracc;
		}

		public void setCurracc(SetReclassifAccounts curracc) {
			this.curracc = curracc;
		}
	

}
