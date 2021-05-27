package com.neoris.tcl.controller;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.models.SetReceivablesIcp;
import com.neoris.tcl.models.ViewCustReceivables;
import com.neoris.tcl.models.ViewPartnersRecICP;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.services.ISetReceivablesIcpService;
import com.neoris.tcl.services.IViewCustReceivablesService;
import com.neoris.tcl.services.IViewPartnersRecICPService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.KeyValue;
import com.neoris.tcl.utils.ViewScope;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

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

	// customer view
	@Autowired
	private IViewCustReceivablesService servicecust;
	private List<ViewCustReceivables> lstCustno;

	// Company
	@Autowired
	private IHfmRollupEntriesService serviceEntries;
	private List<HfmRollupEntries> lstcompany;
	private List<KeyValue<String, String>> mapCompany;

	private Authentication authentication;
	private User user;

	@PostConstruct
	public void init() {
		LOG.info("Initializing lstReceivablesicp...");
		this.lstVRec = serviceVRec.findAll();

		LOG.info("Initializing lstEntries...");
		this.lstcompany = serviceEntries.findAll();

		// We use this list instead the company list. f:itemlist don't works!
		this.mapCompany = this.lstcompany.stream().map(r -> new KeyValue<String, String>(r.getEntity(), String.valueOf(r.getCompanyid())))
				.collect(Collectors.toList());
		LOG.info("mapCompany = {}", mapCompany);
		LOG.info("Getting User form security context...");
		authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() instanceof User) {
			user = (User) authentication.getPrincipal();
		}
	}

	public void openNew() {
		LOG.info("Inicializo currentRecTab...");
		this.currentRecTab = new SetReceivablesIcp();
	}

	public void save() {
		this.currentRecTab.setUserid(user.getUsername());
		//this.currentRecTab.setModified(LocalDate.now().toString());
		LOG.info("Entering to save Trading Partner  => {}", this.currentRecTab);

		this.currentRecTab = service.save(this.currentRecTab);
		this.lstVRec = serviceVRec.findAll();

		Functions.addInfoMessage("Succes", " Trading Partner saved");
		PrimeFaces.current().executeScript("PF('manageACCDialog').hide()");
		PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
		PrimeFaces.current().ajax().update("form:messages", "form:dtRecAccId");

		// PrimeFaces.current().dialog().showMessageDynamic(message);
	}

	public void delete() {
		LOG.info("Entering to delete Trading Partner => {}", this.currentRecTab);
		service.delete(this.currentRecTab);
		this.currentRecTab = null;
		this.lstVRec = serviceVRec.findAll();
		Functions.addInfoMessage("Succes", "Code Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:dtRecAccId");
		PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	}

	public void deleteSelected(ActionEvent event) {
		LOG.info("[deleteSelected] = > Entering to delete Trading Partner : {}", this.lstSelectdRectab);
		service.deleteAll(this.lstSelectdRectab);
		this.lstSelectdRectab = null;
		this.lstVRec = serviceVRec.findAll();
		Functions.addInfoMessage("Succes", "Trading Partner  Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:dtRecAccId");
		PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	}

	public void changeItemValue(AjaxBehaviorEvent ev) {
		LOG.info("changeItemValue. Recibo this.currentRecTab = {}", this.currentRecTab);
		if (ev.getSource() instanceof HtmlSelectOneMenu) {
			HtmlSelectOneMenu selectOne = (HtmlSelectOneMenu) ev.getSource();
			LOG.info("Receive HtmlSelectOneMenu id = {}, value = {}, label = {}", selectOne.getId(),
					selectOne.getValue(), selectOne.getLabel());
			this.lstCustno = servicecust.findByOrganizationid(this.currentRecTab.getId().getCompanyid().intValue());
			LOG.info("this.lstCustno = {}", this.lstCustno);
			if (this.lstCustno == null || this.lstCustno.isEmpty()) {
				Functions.addWarnMessage("Information", "No customers found for company " + selectOne.getValue());
			}
			PrimeFaces.current().ajax().update("form:messages", "form:custno");
		}
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
			currentRectabx.setUserid(user.getUsername());
			//currentRectabx.setModified(LocalDate.now().toString());

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
		LOG.info("return lstcustno with items => {}", lstCustno != null ? lstCustno.size() : "is null");

		this.currentRecTab = new SetReceivablesIcp();
		this.currentRecTab.getId().setCompanyid(new Long(currentVRec.getOrganization_id()));
		this.currentRecTab.getId().setCustno(currentVRec.getCustno());
		this.currentRecTab.setIcpcode(currentVRec.getIcpcode());
		this.currentRecTab.setUserid(user.getUsername());
		//this.currentRecTab.setModified(LocalDate.now().toString());
	}

	public List<ViewCustReceivables> getLstCustno() {
		return lstCustno;
	}

	public void setLstCustno(List<ViewCustReceivables> lstCustno) {
		this.lstCustno = lstCustno;
	}

	public List<KeyValue<String, String>> getMapCompany() {
		return mapCompany;
	}

}
