package com.neoris.tcl.controller;

import java.util.List;


import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.ViewOrclCompany;
import com.neoris.tcl.services.IViewOrclCompanyService;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "vieworclcompControllerBean")
@Scope(ViewScope.VIEW)
public class ViewOrclCompController {
	
	 private final static Logger LOG = LoggerFactory.getLogger(ViewOrclCompController.class);

	    @Autowired
	    private IViewOrclCompanyService service;
	    private List<ViewOrclCompany> lstcomp;
	    private ViewOrclCompany curcomps;

	    @PostConstruct
	    public void init() {
        
			try{
				LOG.info("Initializing lstcomp...");
			
				  this.lstcomp = service.findAll();

				LOG.info(" lstcomp "+this.lstcomp.size());
			}catch (Exception e) {
				LOG.error("init view lstcomp ERRor -> {}", e.getMessage());
			}
	      
	    }
	  
		    
		  public String getTitle() {
		        return "HFM Input Data by Company";
		    }

	    public String getDialogName() {
	        return "manageCodeDialog";
	    }

	    public String getDataTableName() {
	        return "dt-codes";
	    }

	    public String getDeleteCodesButton() {
	        return "delete-codes-button-id";
	    }

	  

		public List<ViewOrclCompany> getLstcomp() {
			return lstcomp;
		}

		public void setLstcomp(List<ViewOrclCompany> lstcomp) {
			this.lstcomp = lstcomp;
		}


}
