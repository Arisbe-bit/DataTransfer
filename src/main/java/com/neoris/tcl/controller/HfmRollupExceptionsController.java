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
import com.neoris.tcl.models.HfmRollupExceptions;
import com.neoris.tcl.models.ViewCostCenter;
import com.neoris.tcl.models.ViewCustReceivables;
import com.neoris.tcl.models.ViewPayablesSupp;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.services.IHfmOracleAccService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.services.IHfmRollupExceptionsService;
import com.neoris.tcl.services.IViewCostCenterService;
import com.neoris.tcl.services.IViewCustReceivablesService;
import com.neoris.tcl.services.IViewPayablesSuppService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "hfmexceptionsControllerBean")
@Scope(ViewScope.VIEW)
public class HfmRollupExceptionsController {
	private final static Logger LOG = LoggerFactory.getLogger(HfmRollupExceptionsController.class);
	
	@Autowired
	private IHfmRollupExceptionsService service;
	private List<HfmRollupExceptions> lstexcep;
	private List<HfmRollupExceptions> lstdelexcep;
	private HfmRollupExceptions curExcep;
	
	private List<HfmOracleAcc> lstOrcl;
	private List<ViewCostCenter> lstCC;
	private List<HfmRollupEntries> lstcompany;
	
	private User user;
	
	@Autowired
	private IHfmOracleAccService serviceOAS;
	@Autowired
	private IViewCostCenterService servcc;
	@Autowired
	private IHfmRollupEntriesService servcomp;
	
	private String vsource;

	// customer view by company
	@Autowired
	private IViewPayablesSuppService servicessupp;
	private List<ViewPayablesSupp> lstSuppno;
	
	@Autowired
	private IViewCustReceivablesService servicecust;
	private List<ViewCustReceivables> lstCustno;
	
	String vsegment1;
	private Optional<HfmRollupEntries> lstentries;
	
	private int vpartnerid;
	private int vpartnerids;
	
	@PostConstruct
	public void init() {
		LOG.info("Initializing lstPArtners Exceptions...");
		this.lstexcep = service.findAll();
		this.vsource = "Receivables";
		try {
			LOG.info("Initializing Cost Centers...");
			this.lstCC = servcc.findAll();
			LOG.info(" lstCC " + this.lstCC.size());

			LOG.info("Initializing Companies...");
			this.lstcompany = servcomp.findAll();
			LOG.info(" lstcompany " + this.lstcompany.size());

			
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
		this.curExcep = new HfmRollupExceptions();
		
		try {
			if (lstcompany.size() > 0) {
				curExcep.getId().setCompanyid(lstcompany.get(0).getCompanyid().intValue());
			}
			if (lstCC.size() > 0) {
				curExcep.getId().setCostcenter(lstCC.get(0).getCostcenter());
			}
	
			// force lo load "lstOrcl" list
			this.costcenterChange();
	
			if (lstOrcl.size() > 0) {
				curExcep.getId().setAccountid(lstOrcl.get(0).getOracleacct());
				
			}
	
			this.curExcep.setUserid(this.user.getUsername());
			LOG.info("[openNew] => curExcep = {}", curExcep);
	
			//lstSuppno = servicessupp.findByOrganizationid(curExcep.getId().getCompanyid());
		
	} catch (Exception e) {
		LOG.error("openNew ERRor -> {}", e.getMessage(), e);
	}
	}

	/**
	 * 
	 */
	public void save() {
		if (this.vsource.equals("Receivables")) {
			this.curExcep.getId().setPartnerid(this.vpartnerid);
			}else {
				this.curExcep.getId().setPartnerid(this.vpartnerids);
			}
		LOG.info("Entering to save Account => {}", this.curExcep);
		
		Long companyId = new Long(this.curExcep.getId().getCompanyid());
		this.curExcep.setUserid(user.getUsername());
		this.curExcep.setSegment1(getSegment1FromList(companyId));
		this.curExcep.setUserid(user.getUsername());
		
		
		
		this.curExcep = service.save(curExcep);
		this.lstexcep = service.findAll();
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
		String segmet1 = "none";
		Optional<HfmRollupEntries> optional = lstcompany.stream().filter(ru -> ru.getCompanyid().equals(companyId))
				.findFirst();
		if (optional.isPresent()) {
			segmet1 = optional.get().getSegment1();
			if (segmet1 == null) {
				segmet1 = "none";
			}
		}
		return segmet1;
	}

	public void delete() {
		LOG.info("Entering to delete Account => {}", this.curExcep);
		service.delete(this.curExcep);
		this.curExcep = null;
		this.lstexcep = service.findAll();
		Functions.addInfoMessage("Succes", "Code Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	}

	public void deleteSelected(ActionEvent event) {
		LOG.info("[deleteSelected] = > Entering to delete Accounts: {}", this.lstdelexcep);
		service.deleteAll(this.lstdelexcep);
		this.lstdelexcep = null;
		this.lstexcep = service.findAll();
		Functions.addInfoMessage("Succes", "Accounts Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
	}

	public void update() {
		LOG.info("Entering to update Account => {}", curExcep);
		save();
	}

	public boolean hasSelectedCodes() {
		return this.lstdelexcep != null && !this.lstdelexcep.isEmpty();
	}

	public String getDeleteButtonMessage() {
		String message = "Delete %s code%s selected";
		String retval = "Delete";
		if (hasSelectedCodes()) {
			int size = this.lstdelexcep.size();
			if (size > 1) {
				retval = String.format(message, size, "s");
			} else {
				retval = String.format(message, size, "");
			}
		}
		return retval;
	}

	public String getTitle() {
		return "Trading partners Exceptions";
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

	public List<HfmRollupExceptions> getLstexcep() {
		return lstexcep;
	}

	public void setLstexcep(List<HfmRollupExceptions> lstexcep) {
		this.lstexcep = lstexcep;
	}

	public HfmRollupExceptions getCurExcep() {
		return curExcep;
	}

	public void setCurExcep(HfmRollupExceptions curExcep) {
		this.curExcep = curExcep;
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

	public List<ViewCustReceivables> getLstCustno() {
		return lstCustno;
	}

	public void setLstCustno(List<ViewCustReceivables> lstCustno) {
		this.lstCustno = lstCustno;
	}

	public String getVsegment1() {
		return vsegment1;
	}

	public void setVsegment1(String vsegment1) {
		this.vsegment1 = vsegment1;
	}

	public Optional<HfmRollupEntries> getLstentries() {
		return lstentries;
	}

	public void setLstentries(Optional<HfmRollupEntries> lstentries) {
		this.lstentries = lstentries;
	}

	public int getVpartnerid() {
		return vpartnerid;
	}

	public void setVpartnerid(int vpartnerid) {
		this.vpartnerid = vpartnerid;
	}

	public int getVpartnerids() {
		return vpartnerids;
	}

	public void setVpartnerids(int vpartnerids) {
		this.vpartnerids = vpartnerids;
	}
	
	
	
	public List<HfmRollupExceptions> getLstdelexcep() {
		return lstdelexcep;
	}

	public void setLstdelexcep(List<HfmRollupExceptions> lstdelexcep) {
		this.lstdelexcep = lstdelexcep;
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
			LOG.info("[companyidChange] => curExcep = {}", curExcep);

			int lcompanyid = this.curExcep.getId().getCompanyid();
			Long compid = Long.valueOf(Integer.toString(lcompanyid));
			
			String costCenter = this.curExcep.getId().getCostcenter();
			LOG.info("[companyidChange] companyid  => {},costcenter  => {}", lcompanyid, costCenter);

			lstOrcl = serviceOAS.findByOrgidAndCostcenter(lcompanyid, costCenter);
			LOG.info("[companyidChange]  return lstOrcl con items => {}", lstOrcl != null ? lstOrcl.size() : "is null");
			
			
			if (this.vsource.equals("Receivables")) {
			lstCustno = servicecust.findByOrganizationid(lcompanyid);
			lstSuppno=null;
			LOG.info("[companyidChange]  return lstCustno con items => {}",
					lstCustno != null ? lstCustno.size() : "is null");
			
			}else {
				lstSuppno = servicessupp.findByOrganizationid(lcompanyid);
				lstCustno =null;
				LOG.info("[companyidChange]  return lstSuppno con items => {}",
						lstSuppno != null ? lstSuppno.size() : "is null");
			}
			
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
			int companyId = this.curExcep.getId().getCompanyid();
			String costCenter = this.curExcep.getId().getCostcenter();
			LOG.info("[costcenterChange] companyid  => {},costcenter  => {}", companyId, costCenter);
			lstOrcl = serviceOAS.findByOrgidAndCostcenter(companyId, costCenter);

			LOG.info("[costcenterChange] return lstOrcl con items => {}", lstOrcl != null ? lstOrcl.size() : "is null");
		} catch (Exception e) {
			LOG.error("costcenterChange ERRor -> {}", e.getMessage());
		}
	}

}
