package com.neoris.tcl.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.ViewCustReceivables;
import com.neoris.tcl.models.ViewPartnersRecICP;
import com.neoris.tcl.services.IViewCustReceivablesService;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "viewcustreceivablesControllerBean")
@Scope(ViewScope.VIEW)
public class ViewCustReceivablesController {

	
private final static Logger LOG = LoggerFactory.getLogger(ViewPartnersRecICPController.class);
	
	@Autowired 
	private IViewCustReceivablesService service;
	
	private List<ViewCustReceivables> lstCustno;
	
	
	
	
	@PostConstruct
	public void init() {
        LOG.info("Initializing Customers List...");
        this.lstCustno = service.findAll();
        
    }


	public List<ViewCustReceivables> getLstCustno() {
		return lstCustno;
	}


	public void setLstCustno(List<ViewCustReceivables> lstCustno) {
		this.lstCustno = lstCustno;
	}

	
	
}
