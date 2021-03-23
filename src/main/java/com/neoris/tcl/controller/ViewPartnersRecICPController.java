package com.neoris.tcl.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.ViewPartnersRecICP;
import com.neoris.tcl.services.IViewPartnersRecICPService;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "viewpartnersrecicpControllerBean")
@Scope(ViewScope.VIEW)
public class ViewPartnersRecICPController {
	

	private final static Logger LOG = LoggerFactory.getLogger(ViewPartnersRecICPController.class);
	
	@Autowired 
	private IViewPartnersRecICPService service;
	private List<ViewPartnersRecICP> lstCustomers;
	private List<ViewPartnersRecICP> lstSelectdcust;
	private ViewPartnersRecICP cviewCustno;
	
	
	@PostConstruct
	public void init() {
        LOG.info("Initializing Trading Partners Vs ICPcodes...");
        this.lstCustomers = service.findAll();
    }


	public List<ViewPartnersRecICP> getLstCustomers() {
		return lstCustomers;
	}


	public void setLstCustomers(List<ViewPartnersRecICP> lstCustomers) {
		this.lstCustomers = lstCustomers;
	}


	public List<ViewPartnersRecICP> getLstSelectdcust() {
		return lstSelectdcust;
	}


	public void setLstSelectdcust(List<ViewPartnersRecICP> lstSelectdcust) {
		this.lstSelectdcust = lstSelectdcust;
	}


	public ViewPartnersRecICP getCviewCustno() {
		return cviewCustno;
	}


	public void setCviewCustno(ViewPartnersRecICP cviewCustno) {
		this.cviewCustno = cviewCustno;
	}

	 public String getDataTableName() {
	     return "dt-codes";
	 }
	 public String getDialogName() {
	     return "manageCodeDialog";
	 }
	 

}
