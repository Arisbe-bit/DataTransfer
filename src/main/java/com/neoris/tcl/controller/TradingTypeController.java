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


import com.neoris.tcl.model.TradingPartnerTypes;
import com.neoris.tcl.services.TradingPartnerTypesService;
import com.neoris.tcl.utils.Functions;

@Controller(value = "tptypesControllerBean")
@Scope("view")
public class TradingTypeController {

	
	private final static Logger LOG = LoggerFactory.getLogger(TradingTypeController.class);
	
	@Autowired
	private TradingPartnerTypesService service;
	private List<TradingPartnerTypes> lsttpType;
    private List<TradingPartnerTypes> lstSelectdtpType; 
    private TradingPartnerTypes curtptypes; // actual iterator
    
    @PostConstruct
    public void init() {
        LOG.info("Initializing lstTradingPartnerTypes...");
        this.lsttpType = service.listar();
    }
    
    public void openNew() {
        this.curtptypes = new TradingPartnerTypes();
    }
      
    public void save() {
        LOG.info("Entering to save Trading Partner Type => {}", this.curtptypes);
        this.curtptypes = service.save(curtptypes);
        this.lsttpType = service.listar();
        Functions.addInfoMessage("Succes", "Trading Partner Type saved");
        PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void delete() {
        LOG.info("Entering to delete Trading Partner Type => {}", this.curtptypes);
        service.delete(this.curtptypes);
        this.curtptypes = null;
        this.lsttpType = service.listar();
        Functions.addInfoMessage("Succes", "Code Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void deleteSelected(ActionEvent event) {
        LOG.info("[deleteSelected] = > Entering to delete Trading Partner Type: {}", this.lstSelectdtpType);
        service.deleteAll(this.lstSelectdtpType);
        this.lstSelectdtpType = null;
        this.lsttpType = service.listar();
        Functions.addInfoMessage("Succes", "Trading Partner Type Removed");
        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
    }
    
    public void update() {
        LOG.info("Entering to update Trading Partner Type => {}", curtptypes);
        save();
    }

    public boolean hasSelectedCodes() {
        return this.lstSelectdtpType != null && !this.lstSelectdtpType.isEmpty();
    }

    public String getDeleteButtonMessage() {
        String message = "Delete %s code%s selected";
        String retval = "Delete";
        if (hasSelectedCodes()) {
            int size = this.lstSelectdtpType.size();
            if (size > 1) {
                retval = String.format(message, size, "s");
            } else {
                retval = String.format(message, size, "");
            }
        }
        return retval;
    }

    public List<TradingPartnerTypes> getlsttpType() {
        return lsttpType;
    }

    public void setlsttpType(List<TradingPartnerTypes> lsttpType) {
        this.lsttpType = lsttpType;
    }

    public Optional<TradingPartnerTypes> getOptionatpTypes(String tpType) {
        return service.listarID(tpType);
    }
    
    public List<TradingPartnerTypes> getLstSelectdtpType() {
        return lstSelectdtpType;
    }

    public void setLstSelectdtpType(List<TradingPartnerTypes> lstSelectdtpType) {
        this.lstSelectdtpType = lstSelectdtpType;
    }
    
   
    public TradingPartnerTypes getCurtptypes() {
		return curtptypes;
	}

	public void setCurtptypes(TradingPartnerTypes curtptypes) {
		this.curtptypes = curtptypes;
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
