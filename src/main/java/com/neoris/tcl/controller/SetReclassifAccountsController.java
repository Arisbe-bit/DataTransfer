package com.neoris.tcl.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmOracleAcc;
import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.models.SetHfmCodes;
import com.neoris.tcl.models.SetReclassifAccounts;
import com.neoris.tcl.models.ViewCostCenter;
import com.neoris.tcl.models.ViewPayablesSupp;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.services.IHfmOracleAccService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.services.ISetHfmCodesService;
import com.neoris.tcl.services.ISetReclassifAccountsService;
import com.neoris.tcl.services.IViewCostCenterService;
import com.neoris.tcl.services.IViewPayablesSuppService;
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

	private List<HfmOracleAcc> lstOrcl;
	private List<ViewCostCenter> lstCC;
	private List<HfmRollupEntries> lstcompany;
	private List<SetHfmCodes> lstHfmcodes;
	private User user;

	@Autowired
	private IHfmOracleAccService serviceOAS;
	@Autowired
	private IViewCostCenterService servcc;
	@Autowired
	private IHfmRollupEntriesService servcomp;
	@Autowired
	ISetHfmCodesService servhfm;
	@Autowired
	private ISetHfmCodesService serviceHfmcodes;

	// private int lcompanyid;
	private String vsource;

	// customer view by company
	@Autowired
	private IViewPayablesSuppService servicessupp;
	private List<ViewPayablesSupp> lstSuppno;

	@PostConstruct
	public void init() {
		LOG.info("Initializing lstReclasssifAccounts...");
		this.lstref = service.findAll();

		try {
			LOG.info("Initializing Cost Centers...");
			this.lstCC = servcc.findAll();
			LOG.info(" lstCC " + this.lstCC.size());

			LOG.info("Initializing Companies...");
			this.lstcompany = servcomp.findAll();
			LOG.info(" lstcompany " + this.lstcompany.size());

			LOG.info("Initializing HFM Codes...");
			this.lstHfmcodes = serviceHfmcodes.findAll();
			LOG.info(" lstHfmcodes " + this.lstHfmcodes.size());

		} catch (Exception e) {
			LOG.error("init lstCC ERRor -> {}", e.getMessage(), e);
		}

		this.user = Functions.getUser();

	}

	public void openNew(AjaxBehaviorEvent ev) {
		LOG.info("[openNew] ev => {}", ev);
		openNew();
	}

	public void openNew() {
		LOG.info("new");
		this.curracc = new SetReclassifAccounts();
		if (lstcompany.size() > 0) {
			curracc.getId().setCompanyid(lstcompany.get(0).getCompanyid().intValue());
		}
		if (lstCC.size() > 0) {
			curracc.getId().setCostcenter(lstCC.get(0).getCostcenter());
		}

		// force lo load "lstOrcl" list
		this.costcenterChange();

		if (lstOrcl.size() > 0) {
			curracc.getId().setAccountidini(lstOrcl.get(0).getOracleacct());
			curracc.getId().setAccountidfin(lstOrcl.get(0).getOracleacct());
		}

		this.curracc.setUserid(this.user.getUsername());
		LOG.info("[openNew] => curracc = {}", curracc);

		lstSuppno = servicessupp.findByOrganizationid(curracc.getId().getCompanyid());
	}

	/**
	 * 
	 */
	public void save() {
		LOG.info("Entering to save Account => {}", this.curracc);
		Long companyId = new Long(this.curracc.getId().getCompanyid());
		this.curracc.setUserid(user.getUsername());
		this.curracc.setSegment1(getSegment1FromList(companyId));
		this.curracc = service.save(curracc);
		this.lstref = service.findAll();
		Functions.addInfoMessage("Succes", "Accounts saved");
		PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	}

	/**
	 * 
	 * @param companyId
	 * @return
	 */
	private String getSegment1FromList(Long companyId) {
		String segmet1 = "** not setted **";
		Optional<HfmRollupEntries> optional = lstcompany.stream().filter(ru -> ru.getCompanyid().equals(companyId))
				.findFirst();
		if (optional.isPresent()) {
			segmet1 = optional.get().getSegment1();
			if (segmet1 == null) {
				segmet1 = "** not value **";
			}
		}
		return segmet1;
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
		LOG.info("[setCurracc] => curracc = {}", curracc);
	}

	public List<HfmOracleAcc> getLstOrcl() {
		return lstOrcl;
	}

	public void setLstOrcl(List<HfmOracleAcc> lstOrcl) {
		this.lstOrcl = lstOrcl;
	}

	public List<ViewCostCenter> getLstCC() {
		return lstCC;
	}

	public void setLstCC(List<ViewCostCenter> lstCC) {
		this.lstCC = lstCC;
	}

	public List<HfmRollupEntries> getLstcompany() {
		return lstcompany;
	}

	public void setLstcompany(List<HfmRollupEntries> lstcompany) {
		this.lstcompany = lstcompany;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public int getLcompanyid() {
//		return lcompanyid;
//	}
//
//	public void setLcompanyid(int lcompanyid) {
//		this.lcompanyid = lcompanyid;
//		LOG.info("[setLcompanyid] Receive companyid = {}", lcompanyid);
//	}

	public String getVsource() {
		return vsource;
	}

	public void setVsource(String vsource) {
		this.vsource = vsource;
	}

	public List<ViewPayablesSupp> getLstSuppno() {
		return lstSuppno;
	}

	public void setLstSuppno(List<ViewPayablesSupp> lstSuppno) {
		this.lstSuppno = lstSuppno;
	}

	/**
	 * Rised when company selectbox is changed in form
	 * 
	 * @param ev
	 */
	public void companyidChange(AjaxBehaviorEvent ev) {
		LOG.info("[companyidChange] ev = {}", ev);
		companyidChange();
	}

	/**
	 * 
	 */
	public void companyidChange() {
		try {
			LOG.info("[companyidChange] => curracc = {}", curracc);

			int lcompanyid = this.curracc.getId().getCompanyid();
			String costCenter = this.curracc.getId().getCostcenter();
			LOG.info("[companyidChange] companyid  => {},costcenter  => {}", lcompanyid, costCenter);

			lstOrcl = serviceOAS.findByOrgidAndCostcenter(lcompanyid, costCenter);
			LOG.info("[companyidChange]  return lstOrcl con items => {}", lstOrcl != null ? lstOrcl.size() : "is null");
			lstSuppno = servicessupp.findByOrganizationid(lcompanyid);
			LOG.info("[companyidChange]  return lstSuppno con items => {}",
					lstSuppno != null ? lstSuppno.size() : "is null");
		} catch (Exception e) {
			LOG.error("[companyidChange]  Error -> {}", e.getMessage(), e);
		}
	}

	/**
	 * Event triguered when selectbox cost center is changed
	 * 
	 * @param ev
	 */
	public void costcenterChange(AjaxBehaviorEvent ev) {
		LOG.info("[costcenterChange] ev  => {}", ev);
		costcenterChange();
	}

	public void costcenterChange() {
		try {
			int companyId = this.curracc.getId().getCompanyid();
			String costCenter = this.curracc.getId().getCostcenter();
			LOG.info("[costcenterChange] companyid  => {},costcenter  => {}", companyId, costCenter);
			lstOrcl = serviceOAS.findByOrgidAndCostcenter(companyId, costCenter);

			LOG.info("[costcenterChange] return lstOrcl con items => {}", lstOrcl != null ? lstOrcl.size() : "is null");
		} catch (Exception e) {
			LOG.error("costcenterChange ERRor -> {}", e.getMessage());
		}
	}

	public List<SetHfmCodes> getLstHfmcodes() {
		return lstHfmcodes;
	}

	public void setLstHfmcodes(List<SetHfmCodes> lstHfmcodes) {
		this.lstHfmcodes = lstHfmcodes;
	}

}
