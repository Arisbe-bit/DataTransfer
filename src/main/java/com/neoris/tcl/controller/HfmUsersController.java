package com.neoris.tcl.controller;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.services.IHfmUSersService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

import com.neoris.tcl.models.HfmUsers;

@Controller(value = "hfmusersControllerBean")
@Scope(ViewScope.VIEW)
public class HfmUsersController {

	 private final static Logger LOG = LoggerFactory.getLogger(HfmUsers.class);
	 
	 @Autowired
	 private IHfmUSersService service;

	 private List<HfmUsers> lstusers;
	 private List<HfmUsers> lstSelectdUsers;
	 private HfmUsers curUsers; // actual iterator
	 private boolean newCode;
	  
	 @PostConstruct
	    public void init() {
	        LOG.info("Initializing lstUsers...");
	        this.lstusers = service.findAll();
	    }

	    public void openNew() {
	        this.newCode = true;
	        this.curUsers = new HfmUsers();
	    }

	    /**
	     * 
	     * @param event
	     */
	    public void save(ActionEvent event) {
	        LOG.info("Entering to save User => {}, event ={}", this.curUsers, event);
	        
	        if(newCode) {
	            Optional<HfmUsers> vuserid = service.findById(curUsers.getUserid());
	            if(vuserid.isPresent()) {
	                String errorMessage = String.format("The record with UserId = %s  already exist. Can't create new record.", curUsers.getUserid()); 
	                Functions.addErrorMessage("Error adding new Code", errorMessage);
	                PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
	                return;
	            }
	        }
	        
	        this.curUsers = service.save(curUsers);
	        Functions.addInfoMessage("Succes", "User saved");

	        this.lstusers = service.findAll();
	        if (this.lstusers != null)
	            LOG.info("La lista viene con {} registros. 1ro={}", lstusers.size(), lstusers.get(0));

	        PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
	        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
	        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	    }

	    public void delete(ActionEvent event) {
	        LOG.info("Entering to delete User => {}", this.curUsers);
	        service.delete(this.curUsers);
	        this.curUsers = null;
	        this.lstusers = service.findAll();
	        Functions.addInfoMessage("Succes", "Code Removed");
	        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
	        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	    }

	    public void deleteSelected(ActionEvent event) {
	        LOG.info("[deleteSelected] = > Entering to delete User: {}", this.lstSelectdUsers);
	        service.deleteAll(this.lstSelectdUsers);
	        this.lstSelectdUsers = null;
	        this.lstusers = service.findAll();
	        Functions.addInfoMessage("Succes", "User Removed");
	        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
	        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	    }

	    public boolean hasSelectedCodes() {
	        return this.lstSelectdUsers != null && !this.lstSelectdUsers.isEmpty();
	    }

	    public String getDeleteButtonMessage() {
	        String message = "Delete %s code%s selected";
	        String retval = "Delete";
	        if (hasSelectedCodes()) {
	            int size = this.lstSelectdUsers.size();
	            if (size > 1) {
	                retval = String.format(message, size, "s");
	            } else {
	                retval = String.format(message, size, "");
	            }
	        }
	        return retval;
	    }

	  

	    
	   

	    public String getTitle() {
	        return "Users Administration";
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

		public List<HfmUsers> getLstusers() {
			return lstusers;
		}

		public void setLstusers(List<HfmUsers> lstusers) {
			this.lstusers = lstusers;
		}

		public List<HfmUsers> getLstSelectdUsers() {
			return lstSelectdUsers;
		}

		public void setLstSelectdUsers(List<HfmUsers> lstSelectdUsers) {
			this.lstSelectdUsers = lstSelectdUsers;
		}

		public HfmUsers getCurUsers() {
			return curUsers;
		}

		public void setCurUsers(HfmUsers curUsers) {
			 this.newCode = false;
			this.curUsers = curUsers;
			LOG.info("Gets curUsers = {}", curUsers.getUserid());
		} 
	  
}
