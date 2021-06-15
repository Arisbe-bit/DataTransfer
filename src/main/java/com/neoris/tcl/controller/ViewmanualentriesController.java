package com.neoris.tcl.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmAllPeriods;
import com.neoris.tcl.models.viewmanualentries;
import com.neoris.tcl.services.IHfmAllPeriodsService;
import com.neoris.tcl.services.IViewmanualentriesService;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "viewentriesControllerBean")
@Scope(ViewScope.VIEW)
public class ViewmanualentriesController {
	
	private final static Logger LOG = LoggerFactory.getLogger(ViewmanualentriesController.class);

	private List<viewmanualentries> lstentries;
	private viewmanualentries currentent; // actual iterator
	

	private String zperiodini;
	private String zperiodfin;
	
	private List<HfmAllPeriods> lstperiod;
	private HfmAllPeriods curperiod;

	@Autowired
	private IViewmanualentriesService service;
	
	@Autowired
	private IHfmAllPeriodsService servperiods;
	  

	@PostConstruct
	public void init() {
			 LOG.info("Initializing lstentries...");
		try {
			this.lstperiod = servperiods.findAll();
			 this.lstentries = service.findAll();
		} catch (Exception e) {
			LOG.error("lstentries ERRor -> {}", e.getMessage());
		}	
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

	
	

	public String getZperiodini() {
		return zperiodini;
	}

	public void setZperiodini(String zperiodini) {
		this.zperiodini = zperiodini;
	}

	public String getZperiodfin() {
		return zperiodfin;
	}

	public void setZperiodfin(String zperiodfin) {
		this.zperiodfin = zperiodfin;
	}

	public List<HfmAllPeriods> getLstperiod() {
		return lstperiod;
	}

	public void setLstperiod(List<HfmAllPeriods> lstperiod) {
		this.lstperiod = lstperiod;
	}

	public HfmAllPeriods getCurperiod() {
		return curperiod;
	}

	public void setCurperiod(HfmAllPeriods curperiod) {
		this.curperiod = curperiod;
	}

	public void periodChange() {
		
		
		String periodini=this.getZperiodini();
		String periodfin=this.getZperiodfin();
		LOG.info("View periodini  => {}",periodini);
		LOG.info("View periodini  => {}",periodfin);
		
		try {
			
			
			LOG.info("Execute ");
			this.lstentries = service.findByPeriodidBetween(Integer.parseInt(periodini),Integer.parseInt(periodfin));
			
			LOG.info("change lstentries "+this.lstentries.size());			
		} catch (Exception e) {
			LOG.error("change ERRor -> {}", e.getMessage());
		}
		
		PrimeFaces.current().ajax().update( "form:" + getDataTableName());
	}


}


