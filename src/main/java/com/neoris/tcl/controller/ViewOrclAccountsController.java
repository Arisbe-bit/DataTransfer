package com.neoris.tcl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmOracleAcc;
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
	
	
	@PostConstruct
	public void init() {
        LOG.info("Initializing Oracle Accounts...");
        this.lstOrcl = service.findAll();
        LOG.info("reg= {}", lstOrcl.size());
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
	    
	
}
