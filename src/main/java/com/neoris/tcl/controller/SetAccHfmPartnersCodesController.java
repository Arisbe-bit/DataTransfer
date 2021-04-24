package com.neoris.tcl.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmOracleAcc;
import com.neoris.tcl.models.SetAccHfmPartnersCodes;
import com.neoris.tcl.services.IHfmOracleAccService;
import com.neoris.tcl.services.ISetAccHfmPartnersCodesService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "setacchfmcodesControllerBean")
@Scope(ViewScope.VIEW)
public class SetAccHfmPartnersCodesController {
	
	
private final static Logger LOG = LoggerFactory.getLogger(TradingTypeController.class);
	
	@Autowired
	private ISetAccHfmPartnersCodesService service;
	private List<SetAccHfmPartnersCodes> lstAcc;
    private List<SetAccHfmPartnersCodes> lstSelectdAcc; 
    private SetAccHfmPartnersCodes currentAcc; // actual iterator
    
    private List<HfmOracleAcc> lstOrcl;
    private HfmOracleAcc currentOrcl; // actual iterator
    
    @Autowired
    private IHfmOracleAccService serviceOrcl;

    @PostConstruct
    public void init() {
        LOG.info("Initializing lstAccountsHFM...");
       // this.lstAcc = service.findAll();
        this.lstOrcl = serviceOrcl.findAll();
    }
    
    public void openNew() {
        this.currentAcc =  new SetAccHfmPartnersCodes();
    }
      
    public void save() {
        LOG.info("Entering to save Account => {}", this.currentAcc);
        this.currentAcc = service.save(currentAcc);
        this.lstAcc = service.findAll();
        Functions.addInfoMessage("Succes", "Account saved");
        PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void delete() {
        LOG.info("Entering to delete Account => {}", this.currentAcc);
        service.delete(this.currentAcc);
        this.currentAcc = null;
        this.lstAcc = service.findAll();
        Functions.addInfoMessage("Succes", "Code Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void deleteSelected(ActionEvent event) {
        LOG.info("[deleteSelected] = > Entering to delete Account: {}", this.lstSelectdAcc);
        service.deleteAll(this.lstSelectdAcc);
        this.lstSelectdAcc = null;
        this.lstAcc = service.findAll();
        Functions.addInfoMessage("Succes", "Account Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void update() {
        LOG.info("Entering to update Account => {}", currentAcc);
        save();
    }

    public boolean hasSelectedCodes() {
        return this.lstSelectdAcc != null && !this.lstSelectdAcc.isEmpty();
    }

    public String getDeleteButtonMessage() {
        String message = "Delete %s code%s selected";
        String retval = "Delete";
        if (hasSelectedCodes()) {
            int size = this.lstSelectdAcc.size();
            if (size > 1) {
                retval = String.format(message, size, "s");
            } else {
                retval = String.format(message, size, "");
            }
        }
        return retval;
    }

    
    public String getTitle() {
        return "Accounting Accounts And HFM Codes Setting";
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

	public List<SetAccHfmPartnersCodes> getLstAcc() {
		return lstAcc;
	}

	public void setLstAcc(List<SetAccHfmPartnersCodes> lstAcc) {
		this.lstAcc = lstAcc;
	}

	public List<SetAccHfmPartnersCodes> getLstSelectdAcc() {
		return lstSelectdAcc;
	}

	public void setLstSelectdAcc(List<SetAccHfmPartnersCodes> lstSelectdAcc) {
		this.lstSelectdAcc = lstSelectdAcc;
	}

	public SetAccHfmPartnersCodes getCurrentAcc() {
		return currentAcc;
	}

	public void setCurrentAcc(SetAccHfmPartnersCodes currentAcc) {
		this.currentAcc = currentAcc;
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
	
	
}
