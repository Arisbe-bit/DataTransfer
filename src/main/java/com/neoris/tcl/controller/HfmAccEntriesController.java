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
import com.neoris.tcl.models.SetHfmCodes;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.services.IHfmAccEntriesDetService;
import com.neoris.tcl.services.IHfmAccEntriesService;
import com.neoris.tcl.services.IHfmPeriodFfssService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.services.ISetHfmCodesService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.utils.ViewScope;

import org.primefaces.component.datatable.DataTable;
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
    @Autowired
    private ISetHfmCodesService serviceHfmcodes;
    
    private List<SetHfmCodes> lstHfmcodes;    

    private List<HfmRollupEntries> lstEntries;

	private List<HfmAccEntries> lstaccent;
	private List<HfmAccEntries> lstSlctedentries;

	private HfmAccEntries currentries;

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
		this.lstEntries = serviceEntries.findAll();  // this is for combobox
		this.lstHfmcodes = serviceHfmcodes.findAll();
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

		this.currentries = new HfmAccEntries();
		this.currentries.setCompanyid(this.currentries.getCompanyid());
		this.currentries.setUserid(this.user.getUsername());
		this.currentries.setApplied(0);

		LOG.info("manual currentries company  => {}", this.currentries.getCompanyid());
	}

	/**
	 * 
	 */
	public void save() {
		LOG.info("Entering to save item  => {}", this.currentries);
		// this.currentmanual.setUserid(user.getUsername());
		try {
			this.currentries = service.save(currentries);
			this.lstaccent = service.findByCompanyid(this.currentries.getCompanyid());
			LOG.info("save lstaccent " + this.lstaccent.size());
			Functions.addInfoMessage("Succes", "item saved");
		} catch (Exception e) {
			LOG.error("save lstaccent Exception -> {}", e.getMessage());
			Functions.addErrorMessage("Error", "Error saving:" + e.getMessage());
		}

		PrimeFaces.current().executeScript("PF('entryDialogWV').hide()");
		PrimeFaces.current().executeScript("PF('dtParentWV').clearFilters()");
		//PrimeFaces.current().ajax().update("form:messages", "form:dtParent");
	}

	/**
	 * 
	 */
	public void delete() {
		LOG.info("Entering to delete item => {}", this.currentries);

		int companyId = currentries.getCompanyid();
		try {
			service.delete(this.currentries);
			this.currentries = null;
			this.lstaccent = service.findByCompanyid(companyId);
			LOG.info("delete lstaccent " + this.lstaccent.size());
			Functions.addInfoMessage("Succes", "Entry Removed");
		} catch (Exception e) {
			Functions.addErrorMessage("Error", "Error deleting:" + e.getMessage());
			LOG.error("Exception deleting -> {}", e.getMessage());
		}

		PrimeFaces.current().ajax().update("form:messages", "form:dtParent");
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
		Functions.addInfoMessage("Succes", "Entries Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:dtParent");
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

//	public String getDataTableName() {
//		return "dt-codes";
//	}

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
		LOG.info("Recibo currentries = {}", currentries);
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

//	public HfmAccEntries getCurrentmanual() {
//		return currentmanual;
//	}
//
//	public void setCurrentmanual(HfmAccEntries currentmanual) {
//		this.currentmanual = currentmanual;
//	}

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

//		try {
//			this.lstperiod = servperiod.findByCompanyid(this.currentries.getCompanyid());
//			LOG.info("companyidChange servperiod.findByCompanyid items size = {} ", this.lstperiod.size());
//		} catch (Exception e) {
//			LOG.error("** Exception in change lstperiod -> {}", e.getMessage());
//		}

		try {	
			LOG.info("change lstaccentdet with ItemID ={} ", this.currentries.getItemid().intValue());
			this.lstaccentdet = servicedet.findByItemid(this.currentries.getItemid());	
			LOG.info("companyidChange servicedet.findByItemid item size ={} ", this.lstaccentdet.size());

		} catch (Exception e) {
			LOG.error("Exception in lservicedet.findByItemid -> {}", e.getMessage());
		}
		
		LOG.info("companyidChange Finish!!");
		//PrimeFaces.current().ajax().update("form:dtParent", "form:period");
	}

	/**
	 * 
	 */
	public void openNewDet() {
		this.currentdet = new HfmAccEntriesDet();
		this.currentdet.setItemid(this.currentries.getItemid());			
		LOG.info("currentdet = {}", this.currentdet);
	}

	public void saveDet() {

		LOG.info("itemdId " + this.currentries.getItemid().intValue());
		LOG.info("Entering to save item  => {}", this.currentdet);
		this.lstaccentdet = servicedet.findByItemid(this.currentries.getItemid());
		
		try {
			this.currentdet = servicedet.save(currentdet);			
			LOG.info("save lstaccentdet " + this.lstaccentdet.size());
			Functions.addInfoMessage("Succes", "Record saved");
		} catch (Exception e) {
			Functions.addErrorMessage("Error", "Error saving record: " +e.getMessage());
			LOG.error("save lstaccentdet ERROR -> {}", e.getMessage());
		}
		
		PrimeFaces.current().executeScript("PF('" + getDialogNameDet() + "').hide()");		
		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearFilters()");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableNameDet());
	}

	public void deleteDet() {
		LOG.info("Entering to delete row => {}", this.currentdet);
		servicedet.delete(this.currentdet);
		this.currentdet = null;
		try {

			this.lstaccentdet = servicedet.findByItemid(this.currentries.getItemid());
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

			this.lstaccentdet = servicedet.findByItemid(this.currentries.getItemid());
			LOG.info("deleteSelectedDet lstaccentdet " + this.lstaccentdet.size());
		} catch (Exception e) {
			LOG.error("deleteSelectedDet lstaccentdet ERRor -> {}", e.getMessage());
		}
		Functions.addInfoMessage("Succes", "Row Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableNameDet());
		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearFilters()");
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
		return "entryDialogDetailWV";
	}

	public String getDataTableNameDet() {
		return "dtDetails";
	}

	public String getDeleteCodesButtonDet() {
		return "delete-codes-button-id-det";
	}

	public String getFormNameId() {
		return "form";
	}

	public void applyprocess() {
		LOG.info("Running apply entries with currentmanual = {}", currentries);
		try {	
			LOG.info("applyprocess ItemID ={} ", this.currentries.getItemid().intValue());
			
			service.rollUpApplyEntries(this.currentries.getCompanyid(), 
					this.currentries.getPeriodnm(), this.user.getUsername(), this.currentries.getItemid().intValue());
			Functions.addInfoMessage("Process", "Apply entries Finished!");
			PrimeFaces.current().ajax().update(getFormNameId() + ":messages");
		} catch (Exception e) {
			LOG.error("Exception in applyprocess -> {}", e.getMessage());
		}		
	}

	public List<HfmRollupEntries> getLstEntries() {
		return lstEntries;
	}
	
	/**
	 * Event fired when clic on the parent row.
	 * @param ev
	 */
	public void dtParent_rowSelect(AjaxBehaviorEvent ev) {
		LOG.info("Me hicieron click en row. ev = {}", ev);
		LOG.info("lstSlctedentries = {}", lstSlctedentries);
		//DataTable td = (DataTable) ev.getSource();
		
		//LOG.info("Row Index = {}, rowData = {}, rows ={}, selectiion = {}",td.getRowIndex(), td.getRowData(), td.getRows(), td.getSelection());
		//this.currentries = (HfmAccEntries) td.getSelection();
		this.currentries = lstSlctedentries.get(0);
		this.lstaccentdet = servicedet.findByItemid(this.currentries.getItemid());
		LOG.info("Regreso con lstaccentdet = {}", lstaccentdet);
	}

	public List<SetHfmCodes> getLstHfmcodes() {
		return lstHfmcodes;
	}
}
