package com.neoris.tcl.controller;

import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.model.TradingPartner;
import com.neoris.tcl.services.TradingPartnerService;
import com.neoris.tcl.utils.Functions;

@Controller(value = "tradingpartnerControllerBean")
@Scope("view")

public class TradingPartnerController {

	private final static Logger LOG = LoggerFactory.getLogger(TradingPartnerController.class);
	
	@Autowired
	private TradingPartnerService service;	
	private List<TradingPartner> lstTP;
    private List<TradingPartner> lstSelectdTP; 
    private TradingPartner curtp; // actual iterator
    
    @PostConstruct
    public void init() {
        LOG.info("Initializing lstTradingPartner...");
        this.lstTP = service.listar();
    }
    
    public void openNew() {
        this.curtp = new TradingPartner();
    }
      
    public void save() {
        LOG.info("Entering to save Trading Partner Type => {}", this.curtp);
        this.curtp = service.save(curtp);
        Functions.addInfoMessage("Succes", "Trading Partner Type saved");
        
        this.lstTP = service.listar();
        if(this.lstTP != null)
        LOG.info("La lista viene con {} registros 1ro={}", lstTP.size(), lstTP.get(0));
        
        PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void delete() {
        LOG.info("Entering to delete Trading Partner Type => {}", this.curtp);
        service.delete(this.curtp);
        this.curtp = null;
        this.lstTP = service.listar();
        Functions.addInfoMessage("Succes", "Code Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void deleteSelected(ActionEvent event) {
        LOG.info("[deleteSelected] = > Entering to delete Trading Partner Type: {}", this.lstSelectdTP);
        service.deleteAll(this.lstSelectdTP);
        this.lstSelectdTP = null;
        this.lstTP = service.listar();
        Functions.addInfoMessage("Succes", "Trading Partner Type Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void update() {
        LOG.info("Entering to update Trading Partner Type => {}", curtp);
        save();
    }

    public boolean hasSelectedCodes() {
        return this.lstSelectdTP != null && !this.lstSelectdTP.isEmpty();
    }

    public String getDeleteButtonMessage() {
        String message = "Delete %s code%s selected";
        String retval = "Delete";
        if (hasSelectedCodes()) {
            int size = this.lstSelectdTP.size();
            if (size > 1) {
                retval = String.format(message, size, "s");
            } else {
                retval = String.format(message, size, "");
            }
        }
        return retval;
    }
    
	public TradingPartner getCurtp() {
		return curtp;
	}
	public void setCurtp(TradingPartner curtp) {
		this.curtp = curtp;
	}

	public List<TradingPartner> getLstTP() {
		return lstTP;
	}

	public void setLstTP(List<TradingPartner> lstTP) {
		this.lstTP = lstTP;
	}

	public List<TradingPartner> getLstSelectdTP() {
		return lstSelectdTP;
	}

	public void setLstSelectdTP(List<TradingPartner> lstSelectdTP) {
		this.lstSelectdTP = lstSelectdTP;
	}
    
	public String getTitle() {
        return "Trading Partner Type Setting";
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
	
}
