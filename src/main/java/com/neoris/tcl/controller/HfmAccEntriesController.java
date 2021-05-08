package com.neoris.tcl.controller;

import java.math.BigDecimal;
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
import com.neoris.tcl.models.SetIcpcodes;
import com.neoris.tcl.models.ViewCostCenter;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.services.IHfmAccEntriesDetService;
import com.neoris.tcl.services.IHfmAccEntriesService;
import com.neoris.tcl.services.IHfmPeriodFfssService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.services.ISetHfmCodesService;
import com.neoris.tcl.services.ISetIcpcodesService;
import com.neoris.tcl.services.IViewCostCenterService;
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
	@Autowired
	private ISetHfmCodesService serviceHfmcodes;
	@Autowired
	private ISetIcpcodesService serviceIcpCodes;

	private List<SetHfmCodes> lstHfmcodes;
	private List<SetIcpcodes> lstIcpcodes;

	private List<HfmRollupEntries> lstEntries;

	private List<HfmAccEntries> lstaccent;
	private List<HfmAccEntries> lstSlctedentries;
	private HfmAccEntries currentries;

	private List<HfmFfss> lstHfmFfss;
	private HfmFfss curHfmFfss;

	private List<HfmPeriodFfss> lstperiod;
	private HfmPeriodFfss curperiod;

	private List<HfmAccEntriesDet> lstaccentdet;
	private List<HfmAccEntriesDet> lstSelectedEntDet;
	private HfmAccEntriesDet currentdet;

	private List<ViewCostCenter> lstCC;
	@Autowired
	private IViewCostCenterService servcc;

	private User user;

	private int vcompanyid;
	private String vperiodnm;
	private int numColums = 1;

	@PostConstruct
	public void init() {

		this.user = Functions.getUser();
		this.currentries = new HfmAccEntries();
		this.lstEntries = serviceEntries.findAll(); // this is for combobox

		if (this.lstEntries != null && !this.lstEntries.isEmpty()) {
			// Get the first company ID from list  and set to curent entry
			this.currentries.setCompanyid(this.lstEntries.get(0).getCompanyid().intValue());

			this.lstHfmcodes = serviceHfmcodes.findAll();
			this.lstIcpcodes = serviceIcpCodes.findAll();
			// find the list of accent for first company...
			this.lstaccent = service.findByCompanyid(this.currentries.getCompanyid());

			// finally set the first element of lstaccent to current entry.
			if(this.lstaccent != null && !this.lstaccent.isEmpty()) {
				this.currentries = this.lstaccent.get(0);
			}
		}

		/*
		 * if(this.lstEntries != null && !this.lstEntries.isEmpty()) {
		 * 
		 * LOG.info("lstEntries filled! Initializing currentries. => {}",
		 * this.lstEntries); this.currentries.setApplied(0);
		 * this.currentries.setUserid(this.user.getUsername());
		 * this.currentries.setCompanyid(this.lstEntries.get(0).getCompanyid().intValue(
		 * ));
		 * 
		 * LOG.info("Gettin lstaccent with company id = {}",
		 * this.currentries.getCompanyid()); this.lstaccent =
		 * service.findByCompanyid(this.currentries.getCompanyid()); }
		 */

		try {
			LOG.info("[init] Initializing Cost Centers...");
			this.lstCC = servcc.findAll();
			LOG.info(" lstCC " + this.lstCC.size());
		} catch (Exception e) {
			LOG.error("[init] init lstCC ERRor -> {}", e.getMessage(), e);
		}
		LOG.info("[init] Initializing finish!");
	}

	/**
	 * 
	 */
	public void openNew() {
		this.currentries = new HfmAccEntries();
		this.currentries.setCompanyid(this.currentries.getCompanyid());
		this.currentries.setUserid(this.user.getUsername());
		this.currentries.setApplied(0);

		LOG.info("[openNew] manual currentries company  => {}", this.currentries.getCompanyid());

	}

	/**
	 * 
	 */
	public void save() {
		LOG.info("[save] Entering to save item  => {}", this.currentries);
		try {
			this.currentries = service.save(currentries);
			this.lstaccent = service.findByCompanyid(this.currentries.getCompanyid());
			LOG.info("[save] lstaccent " + this.lstaccent.size());
			Functions.addInfoMessage("Succes", "item saved");
		} catch (Exception e) {
			LOG.error("[save]  lstaccent Exception -> {}", e.getMessage());
			Functions.addErrorMessage("Error", "Error saving:" + e.getMessage());
		}
		PrimeFaces.current().executeScript("PF('entryDialogWV').hide()");
		refreshUI();
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
			LOG.info("[delete] lstaccent " + this.lstaccent.size());
			Functions.addInfoMessage("Succes", "Entry Removed");
		} catch (Exception e) {
			Functions.addErrorMessage("Error", "Error deleting:" + e.getMessage());
			LOG.error("[delete] Exception deleting -> {}", e.getMessage());
		}

		refreshUI();
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
		refreshUI();
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
		LOG.info("[setCurrentries] Recibo currentries = {}", currentries);
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

	public List<HfmAccEntriesDet> getLstaccentdet() {
		return lstaccentdet;
	}

	public void setLstaccentdet(List<HfmAccEntriesDet> lstaccentdet) {
		this.lstaccentdet = lstaccentdet;
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
		LOG.info("[companyidChange] manual entries company  => {}", this.currentries);

		try {
			this.lstaccent = service.findByCompanyid(this.currentries.getCompanyid());
			this.lstaccent.forEach(entry -> LOG.info("entry => {}", entry));
			LOG.info("[companyidChange]  lstaccent size = {}", this.lstaccent.size());
		} catch (Exception e) {
			LOG.error("[companyidChange] Exception in companyidChange -> service.findByCompanyid -> {}", e.getMessage());
		}

		try {
			LOG.info("[companyidChange] change lstaccentdet with ItemID ={} ", this.currentries.getItemid().intValue());
			this.lstaccentdet = servicedet.findByItemid(this.currentries.getItemid());			
			LOG.info("[companyidChange]  servicedet.findByItemid item size ={} ", this.lstaccentdet.size());

		} catch (Exception e) {
			LOG.error("[companyidChange] Exception in lservicedet.findByItemid -> {}", e.getMessage());
		}

		LOG.info("[companyidChange] companyidChange Finish!!");
		refreshUI();
	}

	/**
	 * 
	 * @param ev
	 */
	public void companyidChangeSel(AjaxBehaviorEvent ev) {
		LOG.info("companyidChangeSel company  => {}", this.currentries.getCompanyid());

		try {
			LOG.info("Getting lstperiod...");
			this.lstperiod = servperiod.findByCompanyid(this.currentries.getCompanyid());
			LOG.info("init lstperiod with {} elements.", this.lstperiod.size());

		} catch (Exception e) {
			LOG.error("init lstperiod ERROR -> {}", e.getMessage(), e);
		}

		this.refreshUI();
	}

	/**
	 * 
	 */
	public void openNewDet() {

		LOG.info("click para crear un nuevo HfmAccEntriesDet");
		double num = 0;

		this.currentdet = new HfmAccEntriesDet();
		this.currentdet.setItemid(this.currentries.getItemid());
		this.currentdet.setDebits(new BigDecimal(num));
		this.currentdet.setCredits(new BigDecimal(num));
		LOG.info("currentdet = {}", this.currentdet);
	}

	/**
	 * Save de Table details form data
	 */
	public void saveDet() {

		LOG.info("[saveDet] itemdId " + this.currentries.getItemid().intValue());
		LOG.info("[saveDet] Entering to save item  => {}", this.currentdet);
		this.lstaccentdet = servicedet.findByItemid(this.currentries.getItemid());

		try {
			this.currentdet = servicedet.save(currentdet);
			LOG.info("[saveDet] save lstaccentdet " + this.lstaccentdet.size());
			Functions.addInfoMessage("Succes", "Record saved");
		} catch (Exception e) {
			Functions.addErrorMessage("Error", "Error saving record: " + e.getMessage());
			LOG.error("[saveDet] save lstaccentdet ERROR -> {}", e.getMessage());
		}

		PrimeFaces.current().executeScript("PF('entryDialogDetailWV').hide()");
		this.refreshUI();
	}

	public void deleteDet() {
		LOG.info("[deleteDet] Entering to delete row => {}", this.currentdet);
		servicedet.delete(this.currentdet);
		this.currentdet = null;

		try {
			this.lstaccentdet = servicedet.findByItemid(this.currentries.getItemid());
			LOG.info("[deleteDet] delete lstaccentdet => {}", this.lstaccentdet.size());
		} catch (Exception e) {
			LOG.error("[deleteDet] delete lstaccentdet Error -> {}", e.getMessage());
		}
		Functions.addInfoMessage("Succes", "Row Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableNameDet());
		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearFilters()");
	}

	/**
	 * 
	 * @param event
	 */
	public void deleteSelectedDet(ActionEvent event) {
		LOG.info("[deleteSelected] = > Entering to delete items: {}", this.lstSelectedEntDet);
		servicedet.deleteAll(this.lstSelectedEntDet);
		this.lstSelectedEntDet = null;

		try {
			this.lstaccentdet = servicedet.findByItemid(this.currentries.getItemid());
			LOG.info("[deleteSelected] lstaccentdet = {}", this.lstaccentdet.size());
		} catch (Exception e) {
			LOG.error("deleteSelectedDet lstaccentdet ERRor -> {}", e.getMessage());
		}
		Functions.addInfoMessage("Succes", "Row Removed");
		PrimeFaces.current().ajax().update("form:messages", "form:" + getDataTableNameDet());
		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearFilters()");
	}

	public boolean hasSelectedCodesDet() {
		return this.lstSelectedEntDet != null && !this.lstSelectedEntDet.isEmpty();
	}

	public String getDeleteButtonMessageDet() {
		String message = "Delete %s item%s selected";
		String retval = "Delete";
		if (hasSelectedCodesDet()) {
			int size = this.lstSelectedEntDet.size();
			if (size > 1) {
				retval = String.format(message, size, "s");
			} else {
				retval = String.format(message, size, "");
			}
		}
		return retval;
	}

	public List<HfmAccEntriesDet> getLstSelectedEntDet() {
		return lstSelectedEntDet;
	}

	public void setLstSelectedEntDet(List<HfmAccEntriesDet> lstSelectedEntDet) {
		LOG.info("Recibo lstSelectedEntDet = {}", lstSelectedEntDet);
		this.lstSelectedEntDet = lstSelectedEntDet;
	}

	public String getTitleDet() {
		return "Manual Entries Details Setting";
	}

//	public String getDialogNameDet() {
//		return "entryDialogDetailWV";
//	}

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

		LOG.info("***********Running apply entries*********** ");
		try {
			LOG.info("applyprocess ItemID ={} ,perdiodnm ={}", this.vcompanyid, this.vperiodnm);

			LOG.info("start apply ");
			service.rollUpApplyEntries(this.vcompanyid, this.vperiodnm, this.user.getUsername(),
					this.currentries.getItemid().intValue());

		} catch (Exception e) {
			LOG.error("Exception in applyprocess -> {}", e.getMessage());
		}
	}

	public List<HfmRollupEntries> getLstEntries() {
		return lstEntries;
	}

	/**
	 * Event fired when click on the parent row.
	 * 
	 * @param ev
	 */
	public void dtParent_rowSelect(AjaxBehaviorEvent ev) {
		LOG.info("Me hicieron click en row. ev = {}", ev);
		LOG.info("lstSlctedentries = {}", lstSlctedentries);
		// DataTable td = (DataTable) ev.getSource();

		// LOG.info("Row Index = {}, rowData = {}, rows ={}, selectiion =
		// {}",td.getRowIndex(), td.getRowData(), td.getRows(), td.getSelection());
		// this.currentries = (HfmAccEntries) td.getSelection();
		this.currentries = lstSlctedentries.get(0);
		this.lstaccentdet = servicedet.findByItemid(this.currentries.getItemid());
		LOG.info("Regreso con lstaccentdet = {}", lstaccentdet);

		this.vcompanyid = this.currentries.getCompanyid();
		this.vperiodnm = this.currentries.getPeriodnm();

		LOG.info("Company id " + this.vcompanyid + " period " + this.vperiodnm);
	}

	public List<SetHfmCodes> getLstHfmcodes() {
		return lstHfmcodes;
	}

	public List<SetIcpcodes> getLstIcpcodes() {
		return lstIcpcodes;
	}

	public List<ViewCostCenter> getLstCC() {
		return lstCC;
	}

	public void setLstCC(List<ViewCostCenter> lstCC) {
		this.lstCC = lstCC;
	}

	public int getNumColums() {
		return numColums;
	}

	public void setNumColums(int numColums) {
		this.numColums = numColums;
	}
	
	/**
	 * Clear filters and selection in details table.
	 */
	private void refreshUI() {
		PrimeFaces.current().executeScript("PF('dtParentWV').clearFilters()");
		PrimeFaces.current().executeScript("PF('dtParentWV').clearSelection()");
		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearFilters()");
		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearSelection()");
		PrimeFaces.current().ajax().update("form:panelGridRollUpFFSS");

	}

}
