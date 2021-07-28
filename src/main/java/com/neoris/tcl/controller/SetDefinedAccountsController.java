package com.neoris.tcl.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.PrimeFaces;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmOracleAcc;
import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.models.SetDefinedAccounts;
import com.neoris.tcl.models.SetHfmCodes;
import com.neoris.tcl.models.SetMatchAccounts;
import com.neoris.tcl.models.ViewCostCenter;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.services.IHfmOracleAccService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.services.ISetDefinedAccountsService;
import com.neoris.tcl.services.ISetHfmCodesService;
import com.neoris.tcl.services.ISetIcpcodesService;
import com.neoris.tcl.services.IViewCostCenterService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "setdefaccountsControllerBean")
@Scope(ViewScope.VIEW)
public class SetDefinedAccountsController {

	private final static Logger LOG = LoggerFactory.getLogger(SetDefinedAccountsController.class);

	@Autowired
	private ISetDefinedAccountsService service;
	@Autowired
	private IHfmOracleAccService serviceOAS;
	@Autowired
	private IViewCostCenterService servcc;
	@Autowired
	private IHfmRollupEntriesService servcomp;
	@Autowired
	ISetHfmCodesService servhfm;
	@Autowired
	ISetIcpcodesService sericp;

	private List<SetDefinedAccounts> lsttpAccs;
	private List<SetDefinedAccounts> lstSelectdAccs;
	private SetDefinedAccounts curtpAccs; // actual iterator

	// Company
	private List<HfmRollupEntries> lstcompany;

	private List<HfmOracleAcc> lstOrcl;
	private List<ViewCostCenter> lstCC;
	private List<SetMatchAccounts> lstMatchAcc;

	private int lcompanyid;

	private User user;
	
	private boolean isNewLine;

	@PostConstruct
	public void init() {

		this.curtpAccs = new SetDefinedAccounts();
		LOG.info("Initializing lstcompany ...");
		this.lstcompany = servcomp.findAll();
		if (lstcompany != null && lstcompany.size() > 0) {
			this.lcompanyid = lstcompany.get(0).getCompanyid().intValue();
		}
		curtpAccs.getId().setCompanyid(lcompanyid);
		this.lsttpAccs = service.findByIdCompanyid(lcompanyid);

		try {
			LOG.info("Initializing Cost Centers...");
			this.lstCC = servcc.findAll();

			LOG.info(" lstCC " + this.lstCC.size());
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
		this.curtpAccs = new SetDefinedAccounts();
		this.curtpAccs.getId().setCompanyid(this.lcompanyid);
		this.curtpAccs.setUserid(this.user.getUsername());
		this.isNewLine = true;
		LOG.info("[openNew] => curtpAccs = {}", curtpAccs);

	}

	public void save(SetDefinedAccounts curtpAccs) {
		LOG.info("save form curtpAccs...");
		this.curtpAccs = curtpAccs;
		
		
		save();
	}

	public void save() {
		LOG.info("Entering to save Accounting Accounts => {}", this.curtpAccs);
		
		/*LOG.info("save getid ={}",this.curtpAccs.getId());
		if(this.isNewLine) {
			LOG.info("isNewLine"); 
            Optional<SetDefinedAccounts> code = service.findById(this.curtpAccs.getId());
            if(code.isPresent()) {
            	
                 
                Functions.addInfoMessage("Warning", "The record with code = %s already exist with value= %s. Can't create new record.");
                PrimeFaces.current().ajax().update("form:messages");
                
                return;
            }
               
        }*/
		
		
		this.curtpAccs.setUserid(user.getUsername());
		this.curtpAccs = service.save(curtpAccs);

		this.lsttpAccs = service.findByIdCompanyid(this.lcompanyid);

		Functions.addInfoMessage("Succes", "Accounting Accounts saved");
		PrimeFaces.current().executeScript("PF('manageCodeDialog').hide()");
		PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
		PrimeFaces.current().ajax().update("form:messages");
	}

	public void delete(ActionEvent ev) {
		LOG.info("[delete] ev => {}", ev);
		delete();
	}

	public void delete(SetDefinedAccounts curtpAccs) {
		LOG.info("[delete] curtpAccs");
		this.curtpAccs = curtpAccs;
		delete();
	}

	public void delete() {
		LOG.info("[delete] Entering to delete Accounting Accounts => {}", this.curtpAccs);
		service.delete(this.curtpAccs);
		this.curtpAccs = null;
		this.lsttpAccs = service.findByIdCompanyid(this.lcompanyid);
		Functions.addInfoMessage("Succes", "Code Removed");
		PrimeFaces.current().ajax().update("form:messages");
		PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	}

	public void deleteSelected(ActionEvent event) {
		LOG.info("[deleteSelected] = > Entering to delete Accounting Account: {}", this.lstSelectdAccs);
		service.deleteAll(this.lstSelectdAccs);
		this.lstSelectdAccs = null;
		this.lsttpAccs = service.findByIdCompanyid(this.lcompanyid);
		Functions.addInfoMessage("Succes", "Accounting Account Removed");
		PrimeFaces.current().ajax().update("form:messages");
		PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	}

	public boolean hasSelectedCodes() {
		return this.lstSelectdAccs != null && !this.lstSelectdAccs.isEmpty();
	}

	public String getDeleteButtonMessage() {
		String message = "Delete %s code%s selected";
		String retval = "Delete";
		if (hasSelectedCodes()) {
			int size = this.lstSelectdAccs.size();
			if (size > 1) {
				retval = String.format(message, size, "s");
			} else {
				retval = String.format(message, size, "");
			}
		}
		return retval;
	}

	public String getTitle() {
		return "Source Accounts Setting";
	}

//	public String getDialogName() {
//		return "manageCodeDialog";
//	}

//	public String getDataTableName() {
//		return "dt-codes";
//	}

	public String getDeleteCodesButton() {
		return "delete-codes-button-id";
	}

	public List<SetDefinedAccounts> getLsttpAccs() {
		return lsttpAccs;
	}

	public void setLsttpAccs(List<SetDefinedAccounts> lsttpAccs) {
		this.lsttpAccs = lsttpAccs;
	}

	public List<SetDefinedAccounts> getLstSelectdAccs() {
		return lstSelectdAccs;
	}

	public void setLstSelectdAccs(List<SetDefinedAccounts> lstSelectdAccs) {
		this.lstSelectdAccs = lstSelectdAccs;
	}

	public SetDefinedAccounts getCurtpAccs() {
		return curtpAccs;
	}

	public void setCurtpAccs(SetDefinedAccounts curtpAccs) {
		this.curtpAccs = curtpAccs;

		try {
			// this.lstCC = servcc.findAll();

			LOG.info("**setCurtpAccs company  => {},costcenter  => {}, source {}",
					this.curtpAccs.getId().getCompanyid(), this.curtpAccs.getId().getCostcenter(),
					this.curtpAccs.getId().getSource());

			// LOG.info("current lstCC "+this.lstCC.size());

			lstOrcl = serviceOAS.findByOrgidAndCostcenter(this.curtpAccs.getId().getCompanyid(),
					this.curtpAccs.getId().getCostcenter());
			LOG.info("setCurtpAccs return lstOrcl con items => {}", lstOrcl != null ? lstOrcl.size() : "is null");

		} catch (Exception e) {
			LOG.error("setCurtpAccs ERRor -> {}", e.getMessage());
		}

	}

	public List<HfmOracleAcc> getLstOrcl() {
		return lstOrcl;
	}

	public void setLstOrcl(List<HfmOracleAcc> lstOrcl) {
		this.lstOrcl = lstOrcl;
	}

	public List<HfmRollupEntries> getLstcompany() {
		return lstcompany;
	}

	public void setLstcompany(List<HfmRollupEntries> lstcompany) {
		this.lstcompany = lstcompany;
	}

	public List<ViewCostCenter> getLstCC() {
		return lstCC;
	}

	public void setLstCC(List<ViewCostCenter> lstCC) {
		this.lstCC = lstCC;
	}
	

	public List<SetMatchAccounts> getLstMatchAcc() {
		return lstMatchAcc;
	}

	public void setLstMatchAcc(List<SetMatchAccounts> lstMatchAcc) {
		this.lstMatchAcc = lstMatchAcc;
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
	 * Rised when company selectbox is changed in form
	 */
	public void companyidChange() {
		try {
			int companyId = curtpAccs.getId().getCompanyid();
			String costCenter = this.curtpAccs.getId().getCostcenter();

			LOG.info("companyidChange company  => {},costcenter  => {}", companyId, costCenter);
			this.lstOrcl = serviceOAS.findByOrgidAndCostcenter(companyId, costCenter);
			// this.lstCC = servcc.findAll();
			// LOG.info("change lstCC "+this.lstCC.size());
		} catch (Exception e) {
			LOG.error("[companyidChange] Exception -> {}", e.getMessage());
		}
	}

	/**
	 * 
	 * @param ev
	 */
	public void companyidChangeorcl(AjaxBehaviorEvent ev) {
		LOG.info("[companyidChangeorcl] ev  => {}", ev);
		SelectOneMenu selectOne = (SelectOneMenu) ev.getSource();
		LOG.info("[companyidChangeorcl] value  => {}", selectOne.getValue());
		companyidChangeorcl();
	}

	/**
	 * 
	 */
	public void companyidChangeorcl() {
		// this.lcompanyid = this.curtpAccs.getId().getCompanyid();
		try {
			LOG.info("[companyidChangeorcl] company  => {}", this.lcompanyid);
			this.lsttpAccs = service.findByIdCompanyid(this.lcompanyid);
			LOG.info("[companyidChangeorcl] lsttpAccs " + this.lsttpAccs.size());
		} catch (Exception e) {
			LOG.error("[companyidChangeorcl] Exception: -> {}", e.getMessage());
		}
		this.lstSelectdAccs = null;
		PrimeFaces.current().ajax().update(":form:dt-defined-account", ":form:messages");
		PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
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

	/**
	 * Event triguered when selectbox cost center is changed
	 */
	public void costcenterChange() {
		try {
			int companyId = curtpAccs.getId().getCompanyid();
			String costCenter = this.curtpAccs.getId().getCostcenter();
			LOG.info("[costcenterChange] companyid  => {},costcenter  => {}", companyId, costCenter);
			lstOrcl = serviceOAS.findByOrgidAndCostcenter(companyId, costCenter);

			LOG.info("[costcenterChange] return lstOrcl con items => {}", lstOrcl != null ? lstOrcl.size() : "is null");
		} catch (Exception e) {
			LOG.error("costcenterChange ERRor -> {}", e.getMessage());
		}
	}

	public int getLcompanyid() {
		return lcompanyid;
	}

	public void setLcompanyid(int lcompanyid) {
		LOG.info("[setLcompanyid] Receive companyid = {}", lcompanyid);
		this.lcompanyid = lcompanyid;
	}
	
	public void rcRefresh() {
		LOG.info("Refreshing after upload file...");
		this.lsttpAccs = service.findByIdCompanyid(lcompanyid);
	}

}
