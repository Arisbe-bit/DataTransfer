package com.neoris.tcl.admin.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.security.models.Role;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.security.service.IRoleService;
import com.neoris.tcl.security.service.IUserService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "hfmUsersBean")
@Scope(ViewScope.VIEW)
public class HfmUsersController {

	private final static Logger LOG = LoggerFactory.getLogger(HfmUsersController.class);

	@Autowired
	private IUserService service;

	//@Autowired
	//private IRoleService roleService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private boolean updatePassword;

	private List<User> lstUsers;
	private List<User> lstSelectdUsers;
	//private List<Role> lstRoles;
	private User curUser;

	@PostConstruct
	public void init() {
		LOG.info("Initializing lstUsers...");
		this.lstUsers = service.findAll();

		//LOG.info("Initializing lstRoles...");
		//this.lstRoles = roleService.findAll();
	}

	public void openNew() {
		this.curUser = new User();
	}

	public void saveUser() {
		LOG.info("Entering to save User = {}", this.curUser);
		String message;
		if (this.curUser.getUsername().length() > 0  || !this.curUser.getPassword().equals(this.curUser.getPasswordBackUp()) ) {
			this.curUser.setPassword(encoder.encode(this.curUser.getPassword()));
			message = "User Added";
		} else {
			message = "User Updated";
		}
		this.curUser = service.saveUser(curUser);
		LOG.info("User saved!! = {}", this.curUser);
		this.lstUsers = service.findAll();
		Functions.addInfoMessage("Save User", message);
		PrimeFaces.current().executeScript("PF('manageUsersDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
	}

	public void deleteUser() {
		LOG.info("Entering to delete User = {}", this.curUser);
		service.delete(this.curUser);
		this.curUser = null;
		this.lstUsers = service.findAll();
		Functions.addInfoMessage("Delete User", "User Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedUsers()) {
			int size = this.lstSelectdUsers.size();
			return size > 1 ? size + " user selected" : "1 users selected";
		}
		return "Delete";
	}

	public boolean hasSelectedUsers() {
		return this.lstSelectdUsers != null && !this.lstSelectdUsers.isEmpty();
	}

	public void deleteSelectedUsers() {
		LOG.info("Entering to delete selected users = {}", this.lstSelectdUsers);
		service.deleteAll(this.lstSelectdUsers);
		this.lstUsers = service.findAll();
		this.lstSelectdUsers = null;
		Functions.addInfoMessage("Delete All", "Users Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
		PrimeFaces.current().executeScript("PF('dtUsers').clearFilters()");
	}

	public String getTitle() {
		return "Users Administration";
	}

	public List<User> getLstUsers() {
		return lstUsers;
	}

	public void setLstUsers(List<User> lstUsers) {
		this.lstUsers = lstUsers;
	}

	public List<User> getLstSelectdUsers() {
		return lstSelectdUsers;
	}

	public void setLstSelectdUsers(List<User> lstSelectdUsers) {
		LOG.info("lstSelectdUsers = {}", lstSelectdUsers);
		this.lstSelectdUsers = lstSelectdUsers;
	}

	/*public List<Role> getLstRoles() {
		return lstRoles;
	}

	public void setLstRoles(List<Role> lstRoles) {
		this.lstRoles = lstRoles;
	}*/

	public User getCurUser() {
		return curUser;
	}

	public void setCurUser(User curUser) {
		LOG.info(" curUser = {}", curUser);
		this.curUser = curUser;
		this.curUser.setPasswordBackUp(this.curUser.getPassword());
	}

	public boolean isUpdatePassword() {
		return updatePassword;
	}

	public void setUpdatePassword(boolean updatePassword) {
		this.updatePassword = updatePassword;
	}

}