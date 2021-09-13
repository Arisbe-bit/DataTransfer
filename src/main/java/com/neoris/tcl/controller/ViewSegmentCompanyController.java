package com.neoris.tcl.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.ViewOrclCompany;
import com.neoris.tcl.models.ViewSegmentCompany;
import com.neoris.tcl.services.IViewOrclCompanyService;
import com.neoris.tcl.services.IViewSegmentCompanyService;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "viewsegmentcompControllerBean")
@Scope(ViewScope.VIEW)
public class ViewSegmentCompanyController {
	
	 private final static Logger LOG = LoggerFactory.getLogger(ViewSegmentCompanyController.class);

	 private List<ViewSegmentCompany> lstsegment;
	    @Autowired
	    private IViewSegmentCompanyService servseg;
	    

	    @PostConstruct
	    public void init() {
     
			try{
				LOG.info("Initializing lstcomp...");
			
				  this.lstsegment = servseg.findAll();

				LOG.info(" lstsegment "+this.lstsegment.size());
			}catch (Exception e) {
				LOG.error("init view lstsegment ERRor -> {}", e.getMessage());
			}
	      
	    }
	  
		    
		  public String getTitle() {
		        return "Segment1-Company";
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


		public List<ViewSegmentCompany> getLstsegment() {
			return lstsegment;
		}


		public void setLstsegment(List<ViewSegmentCompany> lstsegment) {
			this.lstsegment = lstsegment;
		}

	  


}
