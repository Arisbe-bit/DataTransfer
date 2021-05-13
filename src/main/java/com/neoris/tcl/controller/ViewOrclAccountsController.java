package com.neoris.tcl.controller;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmOracleAcc;
import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.services.IHfmOracleAccService;
import com.neoris.tcl.utils.ViewScope;
import java.util.List;

import javax.annotation.PostConstruct;

@Controller(value = "vieworclaccountsControllerBean")
@Scope(ViewScope.VIEW)
public class ViewOrclAccountsController {

	private final static Logger LOG = LoggerFactory.getLogger(ViewOrclAccountsController.class);

	private List<HfmOracleAcc> lstOrcl;
	private HfmOracleAcc currentOrcl; // actual iterator

	@Autowired
	private IHfmOracleAccService service;
	
	  //Company
  	private List<HfmRollupEntries> lstcompany;
  	
  	private HfmRollupEntries curcompid;
  	

	@PostConstruct
	public void init() {
		
	
		LOG.info("Initializing currentOrcl");
		this.currentOrcl = new HfmOracleAcc(); 
		
		 LOG.info("Initializing lstOrclAccountsHFM...");
	       // this.lstAcc = service.findAll();
	  //      this.lstOrcl = service.findAll();
		
	}

	public List<HfmOracleAcc> getLstOrcl() {
		return lstOrcl;
	}

	public void setLstOrcl(List<HfmOracleAcc> lstOrcl) {
		this.lstOrcl = lstOrcl;
	}

	public HfmOracleAcc getCurrentOrcl() {
		return currentOrcl;
	}

	public void setCurrentOrcl(HfmOracleAcc currentOrcl) {
		this.currentOrcl = currentOrcl;
		
		try{ 
			LOG.info("curcompid "+this.currentOrcl.getOrgid());
		
		LOG.info("currentOrcl company  => {}", this.currentOrcl.getOrgid());
		this.lstOrcl = service.findByOrgid(this.currentOrcl.getOrgid());
		
		LOG.info("setcur lstOrcl "+this.lstOrcl.size());			
	} catch (Exception e) {
		LOG.error("setcur ERRor -> {}", e.getMessage());
	}
		
		PrimeFaces.current().ajax().update( "form:" + getDataTableName());
	}

	public IHfmOracleAccService getService() {
		return service;
	}

	public void setService(IHfmOracleAccService service) {
		this.service = service;
	}

	public String getTitle() {
		return "Oracle Accounts Setting";
	}

	public String getDialogName() {
		return "manageCodeDialog";
	}

	public String getDataTableName() {
		return "dt-codes";
	}

	public List<HfmRollupEntries> getLstcompany() {
		return lstcompany;
	}

	public void setLstcompany(List<HfmRollupEntries> lstcompany) {
		this.lstcompany = lstcompany;
	}

	public HfmRollupEntries getCurcompid() {
		return curcompid;
	}

	public void setCurcompid(HfmRollupEntries curcompid) {
		this.curcompid = curcompid;
	
	
	}
	
	public void companyidChange() {
		try {
			LOG.info("Vieworcl company  => {}", this.currentOrcl.getOrgid());
			this.lstOrcl = service.findByOrgid(this.currentOrcl.getOrgid());
			
			LOG.info("change lstOrcl "+this.lstOrcl.size());			
		} catch (Exception e) {
			LOG.error("change ERRor -> {}", e.getMessage());
		}
		
		PrimeFaces.current().ajax().update( "form:" + getDataTableName());
	}
}
