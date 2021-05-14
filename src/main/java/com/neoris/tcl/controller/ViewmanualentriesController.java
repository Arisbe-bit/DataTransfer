package com.neoris.tcl.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.neoris.tcl.models.viewmanualentries;
import com.neoris.tcl.services.IViewmanualentriesService;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "viewentriesControllerBean")
@Scope(ViewScope.VIEW)
public class ViewmanualentriesController {
	
	private final static Logger LOG = LoggerFactory.getLogger(ViewmanualentriesController.class);

	private List<viewmanualentries> lstentries;
	private viewmanualentries currentent; // actual iterator

	@Autowired
	private IViewmanualentriesService service;
	
	  

	@PostConstruct
	public void init() {
		
	
		
		 LOG.info("Initializing lstentries...");
		 this.lstentries = service.findAll();
			
		
	}

	

	public List<viewmanualentries> getLstentries() {
		return lstentries;
	}




	public void setLstentries(List<viewmanualentries> lstentries) {
		this.lstentries = lstentries;
	}




	public viewmanualentries getCurrentent() {
		return currentent;
	}




	public void setCurrentent(viewmanualentries currentent) {
		this.currentent = currentent;
	}




	public String getTitle() {
		return "Manual Entries History";
	}

	public String getDialogName() {
		return "manageCodeDialog";
	}

	public String getDataTableName() {
		return "dt-codes";
	}

	
	
	public void periodChange() {
		try {
			LOG.info("View company  => {}", this.currentent.getPeriodnm());
			this.lstentries = service.findByPeriodnm(this.currentent.getPeriodnm());
			
			LOG.info("change lstentries "+this.lstentries.size());			
		} catch (Exception e) {
			LOG.error("change ERRor -> {}", e.getMessage());
		}
		
		PrimeFaces.current().ajax().update( "form:" + getDataTableName());
	}


}
