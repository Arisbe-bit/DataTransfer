package com.neoris.tcl.controller;

import java.security.Timestamp;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmPeriodFfss;
import com.neoris.tcl.models.viewmanualentries;
import com.neoris.tcl.services.IHfmPeriodFfssService;
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
	
	private List<HfmPeriodFfss> lstperiod;
	private HfmPeriodFfss curperiod;

	@Autowired
	private IViewmanualentriesService service;
	
	@Autowired
	private IHfmPeriodFfssService servperiods;
	  

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

	public List<HfmPeriodFfss> getLstperiod() {
		return lstperiod;
	}

	public void setLstperiod(List<HfmPeriodFfss> lstperiod) {
		this.lstperiod = lstperiod;
	}

	public HfmPeriodFfss getCurperiod() {
		return curperiod;
	}

	public void setCurperiod(HfmPeriodFfss curperiod) {
		this.curperiod = curperiod;
	}

	public void periodChange() {
		
		String strDate = null;
		String periodini=null;
		String periodfin=null;
		LOG.info("View periodini  => {}",this.getZperiodini());
		LOG.info("View periodini  => {}",this.getZperiodfin());
  
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");  
		
		try {

		
			Date date1=(Date) new SimpleDateFormat("yyyyMMdd").parse("01-"+this.getZperiodini());
			Date date2=(Date) new SimpleDateFormat("yyyyMMdd").parse("01-"+this.getZperiodfin());
			
			
			periodini =  dateFormat.format(date1); 
			periodfin =  dateFormat.format(date2); 
			
			LOG.info("Execute "+periodini.toString()+"-"+periodfin.toString());
			this.lstentries = service.findByPeriodidAndPeriodid(Integer.parseInt(periodini),Integer.parseInt(periodfin));
			
			LOG.info("change lstentries "+this.lstentries.size());			
		} catch (Exception e) {
			LOG.error("change ERRor -> {}", e.getMessage());
		}
		
		PrimeFaces.current().ajax().update( "form:" + getDataTableName());
	}


}


