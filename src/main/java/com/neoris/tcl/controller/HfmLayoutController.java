package com.neoris.tcl.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmLayout;
import com.neoris.tcl.services.IHfmLayoutService;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "hfmlayoutControllerBean")
@Scope(ViewScope.VIEW)
public class HfmLayoutController {

	
private final static Logger LOG = LoggerFactory.getLogger(HfmLayoutController.class);
	
	@Autowired 
	private IHfmLayoutService service;
	private List<HfmLayout> lstLay;
	private HfmLayout currLayout;
	
	
	@PostConstruct
	public void init() {
        LOG.info("Initializing lstLay...");
        this.lstLay = service.findAll();
    }


	public List<HfmLayout> getLstLay() {
		return lstLay;
	}


	public void setLstLay(List<HfmLayout> lstLay) {
		this.lstLay = lstLay;
	}


	public HfmLayout getCurrLayout() {
		return currLayout;
	}


	public void setCurrLayout(HfmLayout currLayout) {
		this.currLayout = currLayout;
	}
	
	 public String getDataTableName() {
	        return "dt-codes";
	    }

	  public String getTitle() {
	        return "HFM Layout by Company";
	    }

}
