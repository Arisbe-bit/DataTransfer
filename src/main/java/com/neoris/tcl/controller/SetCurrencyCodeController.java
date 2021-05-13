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

	import com.neoris.tcl.models.SetCurrecyCode;
	import com.neoris.tcl.services.ISetCurrecyCodeService;
	import com.neoris.tcl.utils.Functions;
	import com.neoris.tcl.utils.ViewScope;

	@Controller(value = "currenciesControllerBean")
	@Scope(ViewScope.VIEW)
	public class SetCurrencyCodeController {
		 private final static Logger LOG = LoggerFactory.getLogger(SetCurrencyCodeController.class);
		    
		    @Autowired
		    private ISetCurrecyCodeService service;
		    
		    private List<SetCurrecyCode> lstcurrency;
		    private List<SetCurrecyCode> lstSelectdccs; 
		    private SetCurrecyCode currcurrency;
		    private boolean isNewcurrency;
		    
		        
		    @PostConstruct
		    public void init() {
		        LOG.info("Initializing lstcurrency...");
		        this.lstcurrency = service.findAll();
		    }
		    
		    public void openNew() {
		        this.isNewcurrency = true;
		        this.currcurrency = new SetCurrecyCode();
		    }
		      
		    public void save() {
		        LOG.info("Entering to save currcurrency => {}", currcurrency);
		        
		        if(this.isNewcurrency) {
		            Optional<SetCurrecyCode> code = service.findById(currcurrency.getcurrencycode());
		            if(code.isPresent()) {
		                String errorMessage = String.format("The record with code = %s already exist with value= %s. Can't create new record.",  currcurrency.getcurrencycode()); 
		                Functions.addErrorMessage("Error adding new currcurrency", errorMessage);
		                PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		                return;
		            }
		        }
		        
		        currcurrency = service.save(currcurrency);
		        this.lstcurrency = service.findAll();
		        Functions.addInfoMessage("Succes", "currcurrency saved");
		        PrimeFaces.current().executeScript("PF('" + getDialogName() + "').hide()");
		        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
		    }
		    
		    public void delete() {
		        LOG.info("Entering to delete currcurrency => {}", this.currcurrency);
		        service.delete(this.currcurrency);
		        this.currcurrency = null;
		        this.lstcurrency = service.findAll();
		        Functions.addInfoMessage("Succes", "Code Removed");
		        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
		    }
		    
		    public void deleteSelected(ActionEvent event) {
		        LOG.info("[deleteSelected] = > Entering to delete codes: {}", this.lstSelectdccs);
		        //service.deleteAll(this.lstSelectdccs);
		        service.deleteAll(this.lstSelectdccs);
		        this.lstSelectdccs = null;
		        this.lstcurrency = service.findAll();
		        Functions.addInfoMessage("Succes", "Codes Removed");
		        PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		        PrimeFaces.current().executeScript("PF('dtCodes').clearFilters()");
		    }
		    
		    public void update() {
		        LOG.info("Entering to update currcurrency => {}", currcurrency);
		        save();
		    }

		    public boolean hasSelectedCodes() {
		        return this.lstSelectdccs != null && !this.lstSelectdccs.isEmpty();
		    }

		    public String getDeleteButtonMessage() {
		        String message = "Delete %s code%s selected";
		        String retval = "Delete";
		        if (hasSelectedCodes()) {
		            int size = this.lstSelectdccs.size();
		            if (size > 1) {
		                retval = String.format(message, size, "s");
		            } else {
		                retval = String.format(message, size, "");
		            }
		        }
		        return retval;
		    }



			public Optional<SetCurrecyCode> getOptionacurrcurrency(String code) {
		        return service.findById(code);
		    }
		    
		    public List<SetCurrecyCode> getlstSelectdccs() {
		        return lstSelectdccs;
		    }

		    public void setlstSelectdccs(List<SetCurrecyCode> lstSelectdccs) {
		        this.lstSelectdccs = lstSelectdccs;
		    }
		    
		    public SetCurrecyCode getcurrcurrency() {
		        return currcurrency;
		    }

		    public void setcurrcurrency(SetCurrecyCode currcurrency) {
		        LOG.info("Receive SetCurrecyCode => {}", currcurrency);
		        this.isNewcurrency = false;
		        this.currcurrency = currcurrency;
		    }

		    public String getTitle() {
		        return "Currency Codes Maintenance";
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
