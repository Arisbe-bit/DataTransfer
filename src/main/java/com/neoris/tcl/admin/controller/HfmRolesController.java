package com.neoris.tcl.admin.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.security.models.Role;
import com.neoris.tcl.security.service.IRoleService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "hfmRolesBean")
@Scope(ViewScope.VIEW)
public class HfmRolesController {

	private final static Logger LOG = LoggerFactory.getLogger(HfmRolesController.class);

	@Autowired
	private IRoleService service;

	private List<Role> lstRoles;
	private List<Role> lstSelectdRoles;
	private Role curRole;

	@PostConstruct
	private void init() {
		LOG.info("Initializing Roles Controller. service = {}", service);
		this.lstRoles = service.findAll();
	}

	public void openNew() {
		this.curRole = new Role();
	}

	public void saveRole() {
		LOG.info("Entering to save role = {}", this.curRole);
		String message;
		if (this.curRole.getId() == 0) {
			message = "Role Added";
		} else {
			message = "Role Updated";
		}
		this.curRole = service.saveRole(curRole);
		Functions.addInfoMessage("Save Role", message);
		PrimeFaces.current().executeScript("PF('manageRolesDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");
	}

	public void deleteRole() {
		LOG.info("Entering to delete role = {}", this.curRole);
		service.deleteRole(this.curRole);
		this.curRole = null;
		lstRoles = service.findAll();
		Functions.addInfoMessage("Delete Role", "Role Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedRoles()) {
			int size = this.lstSelectdRoles.size();
			return size > 1 ? size + " roles selected" : "1 role selected";
		}
		return "Delete";
	}

	public boolean hasSelectedRoles() {
		return this.lstSelectdRoles != null && !this.lstSelectdRoles.isEmpty();
	}

	public void deleteSelectedRoles() {
		LOG.info("Entering to delete selected roles = {}", this.lstSelectdRoles);
		service.deleteAll(this.lstSelectdRoles);
		this.lstSelectdRoles = null;
		Functions.addInfoMessage("Delete All", "Roles Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-roles");
		PrimeFaces.current().executeScript("PF('dtRoles').clearFilters()");
	}

	public List<Role> getLstRoles() {
		return lstRoles;
	}

	public void setLstRoles(List<Role> lstRoles) {
		this.lstRoles = lstRoles;
	}

	public List<Role> getLstSelectdRoles() {
		return lstSelectdRoles;
	}

	public void setLstSelectdRoles(List<Role> lstSelectdRoles) {
		this.lstSelectdRoles = lstSelectdRoles;
	}

	public Role getCurRole() {
		return curRole;
	}

	public void setCurRole(Role curRole) {
		this.curRole = curRole;
	}
	
	public String getTitle() {
		return "Roles Administration";		
	}

}
