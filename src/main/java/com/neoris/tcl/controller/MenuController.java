package com.neoris.tcl.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import com.neoris.tcl.security.models.User;

@Controller(value = "menuController")
@RequestScope
public class MenuController {

	private final static Logger LOG = LoggerFactory.getLogger(MenuController.class);
	private final static String REDIRECT = "%s?faces-redirect=true";
	private MenuModel model;
	private Authentication authentication;
	private User user;

	@PostConstruct
	public void init() {
		LOG.debug("Initializing MenuController...");
		model = new DefaultMenuModel();

		authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() instanceof User) {
			user = (User) authentication.getPrincipal();
		}

//        LOG.info("Building model for menu");
//
//        //First submenu
//        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
//                .label("Dynamic Submenu")
//                .build();
//
//        DefaultMenuItem item = DefaultMenuItem.builder()
//                .value("External")
//                .url("http://www.primefaces.org")
//                .icon("pi pi-home")
//                .build();
//        firstSubmenu.getElements().add(item);
//
//        model.getElements().add(firstSubmenu);
//
//        //Second submenu
//        DefaultSubMenu secondSubmenu = DefaultSubMenu.builder()
//                .label("Dynamic Actions")
//                .build();
//
//        item = DefaultMenuItem.builder()
//                .value("Save")
//                .icon("pi pi-save")
//                .command("#{menuView.save}")
//                .update("messages")
//                .build();
//        secondSubmenu.getElements().add(item);
//
//        item = DefaultMenuItem.builder()
//                .value("Delete")
//                .icon("pi pi-times")
//                .command("#{menuView.delete}")
//                .ajax(false)
//                .build();
//        secondSubmenu.getElements().add(item);
//
//        item = DefaultMenuItem.builder()
//                .value("Redirect")
//                .icon("pi pi-search")
//                .command("#{menuView.redirect}")
//                .build();
//        secondSubmenu.getElements().add(item);
//
//        model.getElements().add(secondSubmenu);
	}

	public String hfmcodes() {
		return String.format(REDIRECT, "/hfmcodes");
	}

	public String hfmcodestypes() {
		return String.format(REDIRECT, "/tradingpartnertypes");
	}

	public String hfmcodesOA() {
		return String.format(REDIRECT, "/oracleaccounts");
	}

	public String partners() {
		return String.format(REDIRECT, "/partners");
	}

	public String payablesAccounts() {
		return String.format(REDIRECT, "/payablesAccounts");
	}

	public String receivablesAccounts() {
		return String.format(REDIRECT, "/receivablesAccounts");
	}

	public String reclassification() {
		return String.format(REDIRECT, "/reclassification");
	}

	public String matchAccounts() {
		return String.format(REDIRECT, "/matchAccounts");
	}

	public String dsvscompany() {
		return String.format(REDIRECT, "/companyentries");
	}

	public String rollup() {
		return String.format(REDIRECT, "/rollup");
	}

	public String rolluphist() {
		return String.format(REDIRECT, "/rolluphist");
	}

	public String layout() {
		return String.format(REDIRECT, "/layout");
	}

	public String layouthist() {
		return String.format(REDIRECT, "/layouthist");
	}

	public String admin() {
		return String.format(REDIRECT, "/admin/administration");
	}

	public String definedaccounts() {
		return String.format(REDIRECT, "/definedaccounts");
	}

	public String policies() {
		return String.format(REDIRECT, "/policies");
	}

	public String logout() {
		LOG.info("Entering to logout...");
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		String logout = req.getContextPath() + "/logout";
		LOG.info("logout = {}", logout);

		try {
			context.getExternalContext().redirect(logout);
		} catch (IOException e) {
			LOG.error("IOException: => {}", e.getMessage());
		}
		return null; // req.getContextPath() + "/j_spring_security_logout";
	}

	public MenuModel getModel() {
		return model;
	}

	public String getDataSourceVsCompany() {
		return "Entries By Company";
	}

	public String getRollUpText() {
		return "RollUp";
	}

	public String getReclasificationText() {
		return "Reclasification";
	}

	public String getHfmCodesOAText() {
		return "Accounts & Hfm Codes";
	}

	public String getDefinedAccountsText() {
		return "Source Accounts";
	}

	public String getesText() {
		return "Accounting es";
	}

	public String getName() {
		return authentication.getName();
	}
	
	public boolean isAdminRole() {
		LOG.info("user.isAdmin() = {}", user.isAdmin());
		return user.isAdmin();
	}

	public boolean isHfmcodesRole() {
		return user.isHfmcodes() || user.isAdmin();
	}

	public boolean isHfmcodesOARole() {
		return user.isHfmcodesoa() || user.isAdmin();
	}

	public boolean isHfmcodestypesRole() {
		return user.isHfmcodestypes() || user.isAdmin();
	}

	public boolean isPartnersRole() {
		return user.isPartners() || user.isAdmin();
	}

	public boolean isPayablesAccountsRole() {
		return user.isPayablesaccounts() || user.isAdmin();
	}

	public boolean isReceivablesAccountsRole() {
		return user.isReceivablesaccounts() || user.isAdmin();
	}

	public boolean isMatchAccountsRole() {
		return user.isMatchaccounts() || user.isAdmin();
	}

	public boolean isDsvscompanyRole() {
		return user.isDsvscompany() || user.isAdmin();
	}

	public boolean isRollUpRole() {
		LOG.info("user.isRollup = {}", user.isRollup());
		return user.isRollup() || user.isAdmin();
	}

	public boolean isRolluphistRole() {
		LOG.info("user.isRolluphist() = {}", user.isRolluphist());
		return user.isRolluphist() || user.isAdmin();
	}

	public boolean isPoliciesRole() {
		LOG.info("user.isPolicies() = {}", user.isPolicies());
		return user.isPolicies() || user.isAdmin();
	}
	
	public boolean isDefinedaccountsRole() {
		LOG.info("user.isDefinedaccounts() = {}", user.isDefinedaccounts());
		return user.isDefinedaccounts() || user.isAdmin();
	}
	

}
