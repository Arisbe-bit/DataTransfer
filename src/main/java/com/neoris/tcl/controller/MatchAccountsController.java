package com.neoris.tcl.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.SetMatchAccounts;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.services.ISetMatchAccountsService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "matchaccountsControllerBean")
@Scope(ViewScope.VIEW)
public class MatchAccountsController {
	
private final static Logger LOG = LoggerFactory.getLogger(MatchAccountsController.class);
	
	@Autowired 
	private ISetMatchAccountsService service;
	
	private List<SetMatchAccounts> lstMatchAcc;
	private List<SetMatchAccounts> lstSlctedMAccounts;
	private SetMatchAccounts currmatch;
	private Authentication authentication;
	private User user;
	
	@PostConstruct
	public void init() {
        LOG.info("Initializing Match Accounts...");
        this.lstMatchAcc = service.findAll();
        LOG.info("reg= {}", lstMatchAcc.size());
        authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() instanceof User) {
			user = (User) authentication.getPrincipal();
		}
    }
	
	
	public void openNew() {
        this.currmatch = new SetMatchAccounts();
	}
	
	
	public void save() {
		this.currmatch.setUserid(user.getUsername());
        LOG.info("Entering to save Macth Accounts  => {}", this.currmatch);
        this.currmatch = service.save(currmatch);
        this.lstMatchAcc = service.findAll();
        Functions.addInfoMessage("Succes", "Account saved");
        PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void delete() {
        LOG.info("Entering to delete Account => {}", this.currmatch);
        service.delete(this.currmatch);
        this.currmatch = null;
        this.lstMatchAcc = service.findAll();
        Functions.addInfoMessage("Succes", "Account Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void deleteSelected(ActionEvent event) {
        LOG.info("[deleteSelected] = > Entering to delete Account: {}", this.lstSlctedMAccounts);
        service.deleteAll(this.lstSlctedMAccounts);
        this.lstSlctedMAccounts = null;
        this.lstMatchAcc = service.findAll();
        Functions.addInfoMessage("Succes", "Account Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void update() {
        LOG.info("Entering to update Account => {}", currmatch);
        save();
    }

    public boolean hasSelectedCodes() {
        return this.lstSlctedMAccounts != null && !this.lstSlctedMAccounts.isEmpty();
    }

    public String getDeleteButtonMessage() {
        String message = "Delete %s account%s selected";
        String retval = "Delete";
        if (hasSelectedCodes()) {
            int size = this.lstSlctedMAccounts.size();
            if (size > 1) {
                retval = String.format(message, size, "s");
            } else {
                retval = String.format(message, size, "");
            }
        }
        return retval;
    }
    
	public String getTitle() {
        return "Match Accounts Setting";
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


	public List<SetMatchAccounts> getLstMatchAcc() {
		return lstMatchAcc;
	}


	public void setLstMatchAcc(List<SetMatchAccounts> lstMatchAcc) {
		this.lstMatchAcc = lstMatchAcc;
	}


	public List<SetMatchAccounts> getLstSlctedMAccounts() {
		return lstSlctedMAccounts;
	}


	public void setLstSlctedMAccounts(List<SetMatchAccounts> lstSlctedMAccounts) {
		this.lstSlctedMAccounts = lstSlctedMAccounts;
	}


	public SetMatchAccounts getCurrmatch() {
		return currmatch;
	}


	public void setCurrmatch(SetMatchAccounts currmatch) {
		this.currmatch = currmatch;
	}
	
    
}



