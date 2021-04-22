package com.neoris.tcl.admin.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;
import com.neoris.tcl.security.models.Role;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.security.service.IRoleService;
import com.neoris.tcl.security.service.IUserService;

@Controller(value = "hfmusersControllerBean")
@Scope(ViewScope.VIEW)
public class HfmUsersController {

	private final static Logger LOG = LoggerFactory.getLogger(HfmUsersController.class);

	@Autowired
	private IUserService service;

	@Autowired
	private IRoleService roleService;

	private List<User> lstusers;
	private List<User> lstSelectdUsers;
	
	private List<Role> lstRoles;
	private List<Role> lstSelecteRoles;
	private User curUsers; // actual iterator
	private boolean newCode;

	@PostConstruct
	public void init() {
		LOG.info("Initializing lstUsers...");
		this.lstusers = service.findAll();
		LOG.info("Initializing lstRoles...");
		this.lstRoles = roleService.findAll();
	}

	public void openNew() {
		this.newCode = true;
		this.curUsers = new User();
		this.setLstSelecteRoles(new ArrayList<Role>(this.curUsers.getRoles()));
	}

	/**
	 * 
	 * @param event
	 */
	public void save(ActionEvent event) {
		LOG.info("Entering to save User => {}, event ={}", this.curUsers, event);

		if (newCode) {
			Optional<User> vuserid = service.findById(curUsers.getId());
			if (vuserid.isPresent()) {
				String errorMessage = String.format("The record with UserId = %s - %s already exist. Can't create new record.", curUsers.getId(), curUsers.getUsername());
				LOG.warn(errorMessage);
				Functions.addErrorMessage("Error adding new User", errorMessage);				
				PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
				return;
			}
		}

		this.curUsers = service.saveUser(curUsers);
		Functions.addInfoMessage("Succes", "User saved");

		this.lstusers = service.findAll();

		PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		PrimeFaces.current().executeScript("PF('dtUsers').clearFilters()");
	}

	public void delete(ActionEvent event) {
		LOG.info("Entering to delete User => {}", this.curUsers);
		service.delete(this.curUsers);
		this.curUsers = null;
		this.lstusers = service.findAll();
		Functions.addInfoMessage("Succes", "Code Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		PrimeFaces.current().executeScript("PF('dtUsers').clearFilters()");
	}

	public void deleteSelected(ActionEvent event) {
		LOG.info("[deleteSelected] = > Entering to delete User: {}", this.lstSelectdUsers);
		service.deleteAll(this.lstSelectdUsers);
		this.lstSelectdUsers = null;
		this.lstusers = service.findAll();
		Functions.addInfoMessage("Succes", "User Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		PrimeFaces.current().executeScript("PF('dtUsers').clearFilters()");
	}

	public boolean hasSelectedCodes() {
		return this.lstSelectdUsers != null && !this.lstSelectdUsers.isEmpty();
	}

	public String getDeleteButtonMessage() {
		String message = "Delete %s user%s selected";
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
		return "wvEditUsersDialog";
	}

	public String getDataTableName() {
		return "dtUsersId";
	}

	public String getDeleteCodesButton() {
		return "delete-codes-button-id";
	}

	public List<User> getLstusers() {
		return lstusers;
	}

	public void setLstusers(List<User> lstusers) {
		this.lstusers = lstusers;
	}

	public List<User> getLstSelectdUsers() {
		return lstSelectdUsers;
	}

	public void setLstSelectdUsers(List<User> lstSelectdUsers) {
		this.lstSelectdUsers = lstSelectdUsers;
	}

	public User getCurUsers() {
		if(this.curUsers != null) {
			this.curUsers.setPassword("");
		}
		return this.curUsers;
	}

	public void setCurUsers(User curUsers) {
		LOG.info("Recibo curUsers = {}", curUsers);
		this.newCode = false;
		this.curUsers = curUsers;
		if(this.curUsers != null) {
			this.lstSelecteRoles = new ArrayList<Role>(this.curUsers.getRoles());		
		} else {
			this.lstSelecteRoles = new ArrayList<Role>();
		}
		Functions.addInfoMessage("Info", "Vamos a Editar!!!");
		PrimeFaces.current().ajax().update("form:messages", "form:manage-code-content", "form:userDialogId_content");
	}

	public List<Role> getLstRoles() {
		LOG.info("Return Roles = {}", lstRoles);
		return lstRoles;
	}

	public void setLstRoles(List<Role> lstRoles) {
		this.lstRoles = lstRoles;
	}

	public List<Role> getLstSelecteRoles() {
		LOG.info("Regreso con lstSelecteRoles = {}", lstSelecteRoles);
		return lstSelecteRoles;
	}

	public void setLstSelecteRoles(List<Role> lstSelecteRoles) {
		this.lstSelecteRoles = lstSelecteRoles;
	}
	
	public boolean isCurUsersAvailable() {
		LOG.info("isCurUsersAvailable? = {}", this.curUsers);
		return (this.curUsers != null);
	}

}
