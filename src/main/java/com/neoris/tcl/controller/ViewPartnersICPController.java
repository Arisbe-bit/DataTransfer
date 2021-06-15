package com.neoris.tcl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.ViewPartnersICP;
import com.neoris.tcl.services.IViewPartnersICPService;
import com.neoris.tcl.utils.ViewScope;
import java.util.List;

import javax.annotation.PostConstruct;

@Controller(value = "viewpartnersicpControllerBean")
@Scope(ViewScope.VIEW)
public class ViewPartnersICPController {

	
	private final static Logger LOG = LoggerFactory.getLogger(ViewPartnersICPController.class);
	
	@Autowired 
	private IViewPartnersICPService service;
	private List<ViewPartnersICP> lstSuppliers;
	private List<ViewPartnersICP> lstSelectdPay;
	private ViewPartnersICP currentSupp;
	
	
	@PostConstruct
	public void init() {
        LOG.info("Initializing Trading Partners Vs ICPcodes...");
        this.lstSuppliers = service.findAll();
    }


	public IViewPartnersICPService getService() {
		return service;
	}


	public void setService(IViewPartnersICPService service) {
		this.service = service;
	}


	public List<ViewPartnersICP> getLstSuppliers() {
		return lstSuppliers;
	}


	public void setLstSuppliers(List<ViewPartnersICP> lstSuppliers) {
		this.lstSuppliers = lstSuppliers;
	}


	public ViewPartnersICP getCurrentSupp() {
		return currentSupp;
	}


	public void setCurrentSupp(ViewPartnersICP currentSupp) {
		this.currentSupp = currentSupp;
	}
	
	
}
