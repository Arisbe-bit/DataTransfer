package com.neoris.tcl.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmAccEntries;
import com.neoris.tcl.models.HfmAccEntriesDet;
import com.neoris.tcl.models.HfmFfss;
import com.neoris.tcl.models.HfmPeriodFfss;
import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.services.IHfmAccEntriesDetService;
import com.neoris.tcl.services.IHfmAccEntriesService;
import com.neoris.tcl.services.IHfmPeriodFfssService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "hfmaccentriesControllerBean")
@Scope(ViewScope.VIEW)
public class HfmAccEntriesController {

	private final static Logger LOG = LoggerFactory.getLogger(HfmAccEntriesController.class);

	@Autowired
	private IHfmAccEntriesService service;
	@Autowired
	private IHfmPeriodFfssService servperiod;
	@Autowired
	private IHfmAccEntriesDetService servicedet;
    @Autowired
    private IHfmRollupEntriesService serviceEntries;

    private List<HfmRollupEntries> lstEntries;

	private List<HfmAccEntries> lstaccent;
	private List<HfmAccEntries> lstSlctedentries;

	private HfmAccEntries currentries;
	private HfmAccEntries currentmanual;

	private List<HfmFfss> lstHfmFfss;
	private HfmFfss curHfmFfss;

	private List<HfmPeriodFfss> lstperiod;
	private HfmPeriodFfss curperiod;

	private List<HfmAccEntriesDet> lstaccentdet;
	private List<HfmAccEntriesDet> lstSlctedentdet;
	private HfmAccEntriesDet currentdet;

	private User user;

	@PostConstruct
	public void init() {

		this.user = Functions.getUser();
		this.lstEntries = serviceEntries.findAll();
		this.currentries = new HfmAccEntries();
		
		if(this.lstEntries != null && !this.lstEntries.isEmpty()) {
			LOG.info("lstEntries filled! Initializing currentries. => {}", this.lstEntries);
			this.currentries.setApplied(0);
			this.currentries.setUserid(this.user.getUsername());			
			this.currentries.setCompanyid(this.lstEntries.get(0).getCompanyid().intValue());

			LOG.info("Gettin lstaccent with company id = {}", this.currentries.getCompanyid());
			this.lstaccent = service.findByCompanyid(this.currentries.getCompanyid());
		}		

		try {
			LOG.info("Getting lstperiod...");
			this.lstperiod = servperiod.findAll();
			LOG.info("init lstperiod with {} elements.", this.lstperiod.size());
		} catch (Exception e) {
			LOG.error("init lstperiod ERROR -> {}", e.getMessage(), e);
		}

		LOG.info("Initializing finish!");
	}

	public void openNew() {

		this.currentmanual = new HfmAccEntries();
		this.currentmanual.setCompanyid(this.currentries.getCompanyid());
		this.currentmanual.setUserid(this.user.getUsername());
		this.currentmanual.setApplied(0);

		LOG.info("manual currentries company  => {}", this.currentmanual.getCompanyid());
	}

	/**
	 * 
	 */
	public void save() {
		LOG.info("Entering to save item  => {}", this.currentmanual);
		// this.currentmanual.setUserid(user.getUsername());
		try {
			this.currentmanual = service.save(currentmanual);
			this.lstaccent = service.findByCompanyid(this.currentries.getCompanyid());
			LOG.info("save lstaccent " + this.lstaccent.size());
			Functions.addInfoMessage("Succes", "item saved");
		} catch (Exception e) {
			LOG.error("save lstaccent Exception -> {}", e.getMessage());
			Functions.addErrorMessage("Error", "Error saving:" + e.getMessage());
		}

		PrimeFaces.current().executeScript("PF('entryDetailsDialogWV').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		PrimeFaces.current().executeScript("PF('dtParentWV').clearFilters()");
	}

	/**
	 * 
	 */
	public void delete() {
		LOG.info("Entering to delete item => {}", this.currentmanual);

		try {
			service.delete(this.currentmanual);
			this.currentmanual = null;
			this.lstaccent = service.findByCompanyid(this.currentmanual.getCompanyid());
			LOG.info("delete lstaccent " + this.lstaccent.size());
			Functions.addInfoMessage("Succes", "item Removed");
		} catch (Exception e) {
			Functions.addErrorMessage("Error", "Error deleting:" + e.getMessage());
			LOG.error("Exception deleting -> {}", e.getMessage());
		}

		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		PrimeFaces.current().executeScript("PF('dtParentWV').clearFilters()");
	}

	/**
	 * 
	 * @param event
	 */
	public void deleteSelected(ActionEvent event) {
		LOG.info("[deleteSelected] = > Entering to delete item: {}", this.lstSlctedentries);
		service.deleteAll(this.lstSlctedentries);
		this.lstSlctedentries = null;
		this.lstaccent = service.findAll();
		Functions.addInfoMessage("Succes", "item Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableName());
		PrimeFaces.current().executeScript("PF('dtParentWV').clearFilters()");
	}

	public boolean hasSelectedCodes() {
		return this.lstSlctedentries != null && !this.lstSlctedentries.isEmpty();
	}

	/**
	 * 
	 * @return
	 */
	public String getDeleteButtonMessage() {
		String message = "Delete %s item%s selected";
		String retval = "Delete";
		if (hasSelectedCodes()) {
			int size = this.lstSlctedentries.size();
			if (size > 1) {
				retval = String.format(message, size, "s");
			} else {
				retval = String.format(message, size, "");
			}
		}
		return retval;
	}

	public String getTitle() {
		return "Manual Entries Setting";
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

	public List<HfmAccEntries> getLstaccent() {
		return lstaccent;
	}

	public void setLstaccent(List<HfmAccEntries> lstaccent) {
		this.lstaccent = lstaccent;
	}

	public List<HfmAccEntries> getLstSlctedentries() {
		return lstSlctedentries;
	}

	public void setLstSlctedentries(List<HfmAccEntries> lstSlctedentries) {
		this.lstSlctedentries = lstSlctedentries;
	}

	public HfmAccEntries getCurrentries() {
		return currentries;
	}

	public void setCurrentries(HfmAccEntries currentries) {
		this.currentries = currentries;
	}

	public List<HfmFfss> getLstHfmFfss() {
		return lstHfmFfss;
	}

	public void setLstHfmFfss(List<HfmFfss> lstHfmFfss) {
		this.lstHfmFfss = lstHfmFfss;
	}

	public HfmFfss getCurHfmFfss() {
		return curHfmFfss;
	}

	public void setCurHfmFfss(HfmFfss curHfmFfss) {
		this.curHfmFfss = curHfmFfss;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public HfmAccEntries getCurrentmanual() {
		return currentmanual;
	}

	public void setCurrentmanual(HfmAccEntries currentmanual) {
		this.currentmanual = currentmanual;
	}

	public List<HfmAccEntriesDet> getLstaccentdet() {
		return lstaccentdet;
	}

	public void setLstaccentdet(List<HfmAccEntriesDet> lstaccentdet) {
		this.lstaccentdet = lstaccentdet;
	}

	public List<HfmAccEntriesDet> getLstSlctedentdet() {
		return lstSlctedentdet;
	}

	public void setLstSlctedentdet(List<HfmAccEntriesDet> lstSlctedentdet) {
		this.lstSlctedentdet = lstSlctedentdet;
	}

	public HfmAccEntriesDet getCurrentdet() {
		return currentdet;
	}

	public void setCurrentdet(HfmAccEntriesDet currentdet) {
		this.currentdet = currentdet;
	}

	/**
	 * Fire event for company ID Change
	 */
	public void companyidChange(AjaxBehaviorEvent ev) {
		LOG.info("manual entries company  => {}", this.currentries);

		try {
			this.lstaccent = service.findByCompanyid(this.currentries.getCompanyid());
			this.lstaccent.forEach(entry -> LOG.info("entry => {}", entry));
			LOG.info("companyidChange lstaccent size = {}", this.lstaccent.size());
		} catch (Exception e) {
			LOG.error("Exception in companyidChange -> service.findByCompanyid -> {}", e.getMessage());
		}

		try {
			this.lstperiod = servperiod.findByCompanyid(this.currentries.getCompanyid());
			LOG.info("companyidChange servperiod.findByCompanyid items size = {} ", this.lstperiod.size());
		} catch (Exception e) {
			LOG.error("** Exception in change lstperiod -> {}", e.getMessage());
		}

		if(this.currentmanual != null) {
			try {	
				LOG.info("change lstaccentdet with ItemID ={} ", this.currentmanual.getItemid().intValue());
				this.lstaccentdet = servicedet.findByItemid(this.currentmanual.getItemid().intValue());	
				LOG.info("companyidChange servicedet.findByItemid item size ={} ", this.lstaccentdet.size());
	
			} catch (Exception e) {
				LOG.error("Exception in lservicedet.findByItemid -> {}", e.getMessage());
			}
		} else {
			LOG.warn("currentmanual is null!!");
		}
		
		LOG.info("companyidChange Finish!!");
		//PrimeFaces.current().ajax().update("form:dtParent", "form:period");
	}

	/**
	 * 
	 */
	public void openNewDet() {

		this.currentdet = new HfmAccEntriesDet();

		try {
			LOG.info("curman itemdId " + this.currentmanual.getItemid().intValue());
			this.currentdet.setItemid(this.currentmanual.getItemid());
			LOG.info("curdet item id ", this.currentdet.getItemid());
		} catch (Exception e) {
			LOG.error("new currentdet ERRor -> {}", e.getMessage());
		}

	}

	public void saveDet() {

		LOG.info("itemdId " + this.currentmanual.getItemid().intValue());
		LOG.info("Entering to save item  => {}", this.currentdet);
		this.currentdet = servicedet.save(currentdet);

		try {
			this.lstaccentdet = servicedet.findByItemid(this.currentmanual.getItemid().intValue());
			LOG.info("save lstaccentdet " + this.lstaccentdet.size());
		} catch (Exception e) {
			LOG.error("save lstaccentdet ERRor -> {}", e.getMessage());
		}

		Functions.addInfoMessage("Succes", "item saved");
		PrimeFaces.current().executeScript("PF('" + getDialogNameDet() + "').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableNameDet());
		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearFilters()");
	}

	public void deleteDet() {
		LOG.info("Entering to delete row => {}", this.currentdet);
		servicedet.delete(this.currentdet);
		this.currentdet = null;
		try {

			this.lstaccentdet = servicedet.findByItemid(this.currentmanual.getItemid().intValue());
			LOG.info("delete lstaccentdet " + this.lstaccentdet.size());
		} catch (Exception e) {
			LOG.error("delete lstaccentdet ERRor -> {}", e.getMessage());
		}
		Functions.addInfoMessage("Succes", "Row Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableNameDet());
		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearFilters()");
	}

	public void deleteSelectedDet(ActionEvent event) {
		LOG.info("[deleteSelected] = > Entering to delete item: {}", this.lstSlctedentdet);
		servicedet.deleteAll(this.lstSlctedentdet);
		this.lstSlctedentdet = null;
		try {

			this.lstaccentdet = servicedet.findByItemid(this.currentmanual.getItemid().intValue());
			LOG.info("deleteSelectedDet lstaccentdet " + this.lstaccentdet.size());
		} catch (Exception e) {
			LOG.error("deleteSelectedDet lstaccentdet ERRor -> {}", e.getMessage());
		}
		Functions.addInfoMessage("Succes", "Row Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableNameDet());
		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearFilters()");
	}

	public void updateDet() {
		LOG.info("Entering to update Row => {}", currentdet);
		save();
	}

	public boolean hasSelectedCodesDet() {
		return this.lstSlctedentdet != null && !this.lstSlctedentdet.isEmpty();
	}

	public String getDeleteButtonMessageDet() {
		String message = "Delete %s item%s selected";
		String retval = "Delete";
		if (hasSelectedCodesDet()) {
			int size = this.lstSlctedentdet.size();
			if (size > 1) {
				retval = String.format(message, size, "s");
			} else {
				retval = String.format(message, size, "");
			}
		}
		return retval;
	}

	public String getTitleDet() {
		return "Manual Entries Details Setting";
	}

	public String getDialogNameDet() {
		return "manageCodeDialogdet";
	}

	public String getDataTableNameDet() {
		return "dtDetails";
	}

	public String getDeleteCodesButtonDet() {
		return "delete-codes-button-id-det";
	}

	public void applyprocess() {

	}

	public List<HfmRollupEntries> getLstEntries() {
		return lstEntries;
	}
}
