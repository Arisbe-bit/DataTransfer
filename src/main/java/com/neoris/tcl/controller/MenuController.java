package com.neoris.tcl.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import com.neoris.tcl.security.models.User;
import com.neoris.tcl.utils.Functions;

@Controller(value = "menuController")
@RequestScope
public class MenuController {

	private final static Logger LOG = LoggerFactory.getLogger(MenuController.class);
	private final static String REDIRECT = "%s?faces-redirect=true";
	private MenuModel model;
	private User user;

	@PostConstruct
	public void init() {
		LOG.debug("Initializing MenuController...");
		model = new DefaultMenuModel();

		this.user = Functions.getUser();


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
		return String.format(REDIRECT, "/rollup/rollups");
	}

	public String rolluphist() {
		return String.format(REDIRECT, "/rolluphist/rollupshist");
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

	public String manualentries() {
		return String.format(REDIRECT, "/manualentries");
	}
	
	public String manualentrieshist() {
		return String.format(REDIRECT, "/manualentrieshist");
	}

	
	public String rollupexceptions() {
		return String.format(REDIRECT, "/rollupexceptions");
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
	
	public String getRollupExceptionsText() {
		return "Trading Partners Exceptions";
	}

	public String getHfmCodesOAText() {
		return "Accounts & Hfm Codes";
	}

	public String getDefinedAccountsText() {
		return "Source Accounts";
	}

	public String getesText() {
		return "Accounting is";
	}

	public String getName() {
		return user.getName();
	}
	
	public String getUsername() {
		return user.getUsername();
	}

	public boolean isAdminRole() {
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
		return user.isRollup() || user.isAdmin();
	}

	public boolean isRolluphistRole() {
		return user.isRolluphist() || user.isAdmin();
	}

	public boolean isPoliciesRole() {
		return user.isPolicies() || user.isAdmin();
	}

	public boolean isDefinedaccountsRole() {
		return user.isDefinedaccounts() || user.isAdmin();
	}
	
	public boolean isManualentrieshistRole() {
		return user.isManualentrieshist() || user.isAdmin();
	}
	
	public boolean isReclassificationRole() {
		return user.isReclassification() || user.isAdmin();
	}
	
	public boolean isRollupexceptionsRole() {
		return user.isRollexceptions() || user.isAdmin();
	}

}
