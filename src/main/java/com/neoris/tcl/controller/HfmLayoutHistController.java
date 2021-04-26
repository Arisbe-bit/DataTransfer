package com.neoris.tcl.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmLayoutHist;
import com.neoris.tcl.services.IHfmLayoutHistService;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "hfmlayouthistControllerBean")
@Scope(ViewScope.VIEW)
public class HfmLayoutHistController {

	private final static Logger LOG = LoggerFactory.getLogger(HfmLayoutHistController.class);
	
	@Autowired 
	private IHfmLayoutHistService service;
	private List<HfmLayoutHist> lstLay;
	private HfmLayoutHist currLayout;
	
	@PostConstruct
	public void init() {
        LOG.info("Initializing lstLay...");
        this.lstLay = service.findAll();
    }

	 public String getDataTableName() {
	        return "dt-codes";
	    }

	  public String getTitle() {
	        return "HFM Layout";
	    }

	public List<HfmLayoutHist> getLstLay() {
		return lstLay;
	}

	public void setLstLay(List<HfmLayoutHist> lstLay) {
		this.lstLay = lstLay;
	}

	public HfmLayoutHist getCurrLayout() {
		return currLayout;
	}

	public void setCurrLayout(HfmLayoutHist currLayout) {
		this.currLayout = currLayout;
	}

	  
	  
	
}
