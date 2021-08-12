package com.neoris.tcl.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.PrimeFaces;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
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
import com.neoris.tcl.models.SetCurrecyCode;
import com.neoris.tcl.models.SetHfmCodes;
import com.neoris.tcl.models.SetIcpcodes;
import com.neoris.tcl.models.ViewCostCenter;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.services.IHfmAccEntriesDetService;
import com.neoris.tcl.services.IHfmAccEntriesService;
import com.neoris.tcl.services.IHfmPeriodFfssService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.services.ISetCurrecyCodeService;
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

	private Optional<SetHfmCodes> lstHfmdesc;
	private List<SetHfmCodes> lstHfmcodes;
	private List<SetIcpcodes> lstIcpcodes;

	private List<HfmRollupEntries> lstEntries;

	private List<HfmAccEntries> lstaccent;

	private HfmAccEntries currentries;

	private List<HfmFfss> lstHfmFfss;
	private HfmFfss curHfmFfss;

	private List<HfmPeriodFfss> lstperiod;
	private HfmPeriodFfss curperiod;

	//
	private HfmAccEntriesDet currentdet;

	private List<ViewCostCenter> lstCC;
	@Autowired
	private IViewCostCenterService servcc;
	@Autowired
	private ISetCurrecyCodeService servcur;

	private List<SetCurrecyCode> lstcurrencies;

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
			// Get the first company ID from list and set to curent entry
			// this.currentries.setCompanyid(this.lstEntries.get(0).getCompanyid().intValue());
			this.vcompanyid = this.lstEntries.get(0).getCompanyid().intValue();

			this.lstHfmcodes = serviceHfmcodes.findAll();
			//this.lstIcpcodes = serviceIcpCodes.findAll();
			// find the list of accent for first company...
			this.lstaccent = service.findByCompanyid(this.vcompanyid);

			// finally set the first element of lstaccent to current entry.
			if (this.lstaccent != null && !this.lstaccent.isEmpty()) {
				this.currentries = this.lstaccent.get(0);
			}
		}

		

		LOG.info("[init] Initializing finish!");
	}
	
	public void openNew(AjaxBehaviorEvent ev) {
		LOG.info("[openNew] AjaxBehaviorEven  => {}", ev);
		openNew();		
	}

	/**
	 * 
	 */
	public void openNew() {
		this.currentries = new HfmAccEntries();
		this.currentries.setCompanyid(this.vcompanyid);
		this.currentries.setUserid(this.user.getUsername());
		this.currentries.setApplied(0);
		this.currentries.setStatus("UnPosting");
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
			LOG.info("[save] lstaccent size = {}", this.lstaccent.size());
			Functions.addInfoMessage("[save] Succes", "item saved");
		} catch (Exception e) {
			LOG.error("[save]  lstaccent Exception -> {}", e.getMessage());
			Functions.addErrorMessage("[save] Error", "Error saving:" + e.getMessage());
		}
		PrimeFaces.current().executeScript("PF('entryDialogWV').hide()");
		PrimeFaces.current().executeScript("PF('entryDialogWVMod').hide()"); //add 270721
		PrimeFaces.current().ajax().update("form:messages");
		//refreshUI();
	}

	/**
	 * 
	 */
	public void delete() {
		LOG.info("Entering to delete item => {}", this.currentries);

		try {
			servicedet.deleteAll(this.currentries.getLstEntriesDet());
			LOG.info("[delete] Done! Delete Entry....");
		}
		 catch (Exception e) {
			 LOG.error("[delete] Exception deleting details -> {}", e.getMessage());
		 }
		
		try {
			LOG.info("[delete] Deleting Entries Detail....");
			
			service.delete(this.currentries);
			this.currentries = null;
			LOG.info("[delete] Done! Refreshing Entries List for company = {}", this.vcompanyid);
			this.lstaccent = service.findByCompanyid(this.vcompanyid);
			LOG.info("[delete] Done! lstaccent size = {} items!", this.lstaccent.size());
			
			PrimeFaces.current().executeScript("PF('dtDetailsWV').clearSelection()"); //add 270721
			PrimeFaces.current().ajax().update("form:dtDetails"); //add 270721
			Functions.addInfoMessage("Succes", "Entry and Entry Child Items Removed");
		} catch (Exception e) {
			Functions.addErrorMessage("Error", "Error deleting:" + e.getMessage());
			LOG.error("[delete] Exception deleting -> {}", e.getMessage());
		}
		PrimeFaces.current().ajax().update("form:messages");
		//refreshUI();
	}

	/**
	 * 
	 * @param event
	 */
//	public void deleteSelected(ActionEvent event) {
//		LOG.info("[deleteSelected] = > Entering to delete item: {}", this.lstSelectedEntries);
//		service.deleteAll(this.lstSelectedEntries);
//		this.lstSelectedEntries = null;
//		this.lstaccent = service.findAll();
//		Functions.addInfoMessage("Succes", "Entries Removed");
//		refreshUI();
//	}

//	public boolean hasSelectedCodes() {
//		return this.lstSelectedEntries != null && !this.lstSelectedEntries.isEmpty();
//	}

	/**
	 * 
	 * @return
	 */
//	public String getDeleteButtonMessage() {
//		String message = "Delete %s item%s selected";
//		String retval = "Delete";
//		if (hasSelectedCodes()) {
//			int size = this.lstSelectedEntries.size();
//			if (size > 1) {
//				retval = String.format(message, size, "s");
//			} else {
//				retval = String.format(message, size, "");
//			}
//		}
//		return retval;
//	}

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

//	public List<HfmAccEntries> getLstSelectedEntries() {
//		return lstSelectedEntries;
//	}
//
//	public void setLstSelectedEntries(List<HfmAccEntries> lstSelectedEntries) {
//		this.lstSelectedEntries = lstSelectedEntries;
//	}

	public HfmAccEntries getCurrentries() {
		return currentries;
	}

	public void setCurrentries(HfmAccEntries currentries) {
		LOG.info("[setCurrentries] gets currentries companyId = {}, ItemID = {}", 
				currentries.getCompanyid(), currentries.getItemid());
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

	public HfmAccEntriesDet getCurrentdet() {
		return currentdet;
	}

	public void setCurrentdet(HfmAccEntriesDet currentdet) {
		LOG.info("[setCurrentdet]  currentdet id= {}, Mov Id = {}", currentdet.getItemid(), currentdet.getMovid());
		this.currentdet = currentdet;
	}

	/**
	 * Fire event for company ID Change
	 */
	public void companyidChange(AjaxBehaviorEvent ev) {
		// vcompanyid = this.currentries.getCompanyid();
		LOG.info("[companyidChange] manual entries company  => {}", vcompanyid);

		try {
			this.lstaccent = service.findByCompanyid(vcompanyid);
			if (this.lstaccent.isEmpty()) {
				this.currentries = new HfmAccEntries();
				this.currentries.setCompanyid(vcompanyid);
			} else {
				this.currentries = this.lstaccent.get(0);
			}
			LOG.info("[companyidChange]  lstaccent size = {}", this.lstaccent.size());
		} catch (Exception e) {
			LOG.error("[companyidChange] Exception in companyidChange -> service.findByCompanyid -> {}",
					e.getMessage());
		}

		try {
			LOG.info("[companyidChange] change lstaccentdet with ItemID ={} ", this.currentries.getItemid().intValue());
			currentries.setLstEntriesDet(servicedet.findByItemid(this.currentries.getItemid()));
			LOG.info("[companyidChange]  servicedet.findByItemid item size ={} ", currentries.getLstEntriesDet().size());

		} catch (Exception e) {
			LOG.error("[companyidChange] Exception in lservicedet.findByItemid -> {}", e.getMessage());
		}

//		if(this.lstSelectedEntries != null) {
//			this.lstSelectedEntries.clear();
//		}
		LOG.info("[companyidChange] companyidChange Finish!!");
		//refreshUI();
	}

	/**
	 * 
	 * @param ev
	 */
	public void companyidChangeSel(AjaxBehaviorEvent ev) {
		LOG.info("[companyidChangeSel] company  => {}", this.currentries.getCompanyid());

		try {
			LOG.info("[companyidChangeSel] Getting lstperiod...");
			this.lstperiod = servperiod.findByCompanyid(this.currentries.getCompanyid());
			LOG.info("[companyidChangeSel]init lstperiod with {} elements.", this.lstperiod.size());

		} catch (Exception e) {
			LOG.error("[companyidChangeSel] init lstperiod ERROR -> {}", e.getMessage(), e);
		}

		// this.refreshUI();
	}

	/**
	 * 
	 * @param ev
	 */
	public void tptypeChange(AjaxBehaviorEvent ev) {

		LOG.info("[tptypeChange] hfmcode  => {}", this.currentdet.getHfmcode());

		String tptype = "";
		String desc = "";

		

		try {
			LOG.info("[tptypeChange]...");
			// this.lstHfmcodes = serviceHfmcodes.findByTptype(this.currentdet.getTptype());

			this.lstHfmdesc = serviceHfmcodes.findById(this.currentdet.getHfmcode());

			desc = this.lstHfmdesc.get().getDescription();
			tptype = this.lstHfmdesc.get().getTptype();

			this.currentdet.setDescription(desc);
			this.currentdet.setTptype(tptype);
			LOG.info("Description {}, tptype {} ", desc, tptype);

		} catch (Exception e) {
			LOG.error("[tptypeChange] init lsthfmcodes ERROR -> {}", e.getMessage());
		}

		if (tptype.contains("GOP")) {
			this.lstIcpcodes = null;
			this.lstcurrencies = null;
			try {
				LOG.info("[init] Initializing Cost Centers...");
				this.lstCC = servcc.findAll();
				LOG.info("[init] lstCC = {}", this.lstCC.size());
			} catch (Exception e) {
				LOG.error("[init] init lstCC ERRor -> {}", e.getMessage(), e);
			}

		} else if (tptype.equals("CURRENCIES")) {
			this.lstIcpcodes = null;
			this.lstCC = null;
			try {
				LOG.info("[init] Initializing Currencies...");
				this.lstcurrencies = servcur.findAll();
				LOG.info("[init] lscurrencies = {}", this.lstcurrencies.size());
			} catch (Exception e) {
				LOG.error("[init] init lstCC ERRor -> {}", e.getMessage(), e);
			}
		} 
		
		if  (tptype.contains("INTERCIAS")) { 
			try {
				this.lstCC = null;
				this.lstcurrencies = null;
				LOG.info("[tptypeChange] Getting lsticpcodes...");
				this.lstIcpcodes = serviceIcpCodes.findByTptype("INTERCIAS");
				LOG.info("[tptypeChange]init lstIcpcodes with {} elements.", this.lstIcpcodes.size());

			} catch (Exception e) {
				LOG.error("[tptypeChange] init lstIcpcodes ERROR -> {}", e.getMessage(), e);
			}
		
		}
		// Refresh in xhtml
		// PrimeFaces.current().ajax().update("form:opexarea", "form:icpcode", "form:Currencyc");

	}

	public void openNewDet(AjaxBehaviorEvent ev) {
		LOG.info("[openNewDet] click event: {}", ev);
		openNewDet();
	}
	/**
	 * 
	 */
	public void openNewDet() {
		int vapplied = this.currentries.getApplied();
		LOG.info("[openNewDet] click to create a new item HfmAccEntriesDet {}", vapplied);
		LOG.info("[openNewDet] currentries = {}", currentries);
		this.currentdet = new HfmAccEntriesDet();
		this.currentdet.setItemid(this.currentries.getItemid());
		this.currentdet.setIcpcode(null);
		this.currentdet.setAreaid(null);
		this.currentdet.setCurrencyid(null);
		LOG.info("[openNewDet] currentdet = {}", this.currentdet);
		//PrimeFaces.current().ajax().update(":form:manage-code-contentDet");
	}

	/**
	 * Save the Table details form data
	 */
	public void saveDet() {
		double num = 0;

		LOG.info("[saveDet] itemdId " + this.currentries.getItemid().intValue());
		LOG.info("[saveDet] Entering to save item  => {}", this.currentdet);

		try {
			this.currentdet = servicedet.save(currentdet);
			LOG.info("[saveDet] save lstaccentdet " + currentries.getLstEntriesDet().size());
			Functions.addInfoMessage("Succes", "Record saved");
			LOG.info("[saveDet] getting  currentries.lstEntriesDet...");
			currentries.setLstEntriesDet(servicedet.findByItemid(this.currentries.getItemid()));

			LOG.info("***********Running item validation*********** ");

			for (HfmAccEntriesDet det : this.currentries.getLstEntriesDet()) {
				LOG.info("detamount: " + det.getAmount().toString());
				// vamount.add(det.getAmount());
				num = num + det.getAmount().doubleValue();
			}

			// LOG.info("[saveDet]amount: "+ vamount.toString());
			LOG.info("[saveDet]num: " + num);

			if (num != 0) {
				Functions.addWarnMessage("Warning", "The total amount != 0");
				LOG.info("[saveDet] Warning", "The total amount != 0");
			}

		} catch (Exception e) {
			Functions.addErrorMessage("Error", "Error saving record: " + e.getMessage());
			LOG.error("[saveDet] save lstaccentdet ERROR -> {}", e.getMessage());
		}

		PrimeFaces.current().executeScript("PF('entryDialogDetailWV').hide()");
		PrimeFaces.current().ajax().update("form:messages");
		LOG.info("[saveDet] ajax update=> form:dtDetails");
		
		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearSelection()");
		PrimeFaces.current().ajax().update("form:dtDetails");
		// this.refreshUI();
	}

	public void deleteDet() {
		LOG.info("[deleteDet] Entering to delete row => {}", this.currentdet);
		servicedet.delete(this.currentdet);
		this.currentdet = null;
		LOG.info("[saveDet] ajax update=> form:dtDetails");
		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearSelection()");
		PrimeFaces.current().ajax().update("form:dtDetails");
		try {
			LOG.info("[deleteDet] updating currentries.lstEntriesDet with id={}", this.currentries.getItemid());
			currentries.setLstEntriesDet(servicedet.findByItemid(this.currentries.getItemid()));
		} catch (Exception e) {
			LOG.error("[deleteDet] delete lstaccentdet Error -> {}", e.getMessage());
		}
		Functions.addInfoMessage("Succes", "Entity detail Removed");
		// PrimeFaces.current().ajax().update("form:messages");
		//this.refreshUI();
	}

	
	public String getTitleDet() {
		return "Manual Entries Details Setting";
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

		LOG.info("***********Running apply entries*********** ");
		try {

			LOG.info("[applyprocess] company id ={} , item ={}", this.currentries.getCompanyid(),
					 this.currentries.getItemid());
			LOG.info("[applyprocess] ItemID ={} ,perdiodnm ={}", this.vcompanyid, this.vperiodnm);
			
			if (this.vcompanyid > 0 ) {
				service.rollUpApplyEntries(this.vcompanyid, this.user.getUsername(),
						this.currentries.getItemid().intValue(), 1);
				this.currentries.setApplied(1);
				this.currentries.setStatus("Posting");
				this.currentries.setPeriodnm(this.vperiodnm);
			} else
				LOG.warn("[applyprocess] Need select the company and period  ");

		} catch (Exception e) {
			LOG.error("[applyprocess] Exception in applyprocess -> {}", e.getMessage());
		}
		this.lstaccent = service.findByCompanyid(this.vcompanyid);
		PrimeFaces.current().ajax().update("form:dtParent", "form:dtDetails");
		// this.refreshUI();
	}

	public void unpostingprocess() {

		LOG.info("***********Running unapply entries*********** ");
		try {
			LOG.info("[unposting process] company id ={} ,perdiodnm ={}, item ={}", this.currentries.getCompanyid(),
					this.currentries.getPeriodnm(), this.currentries.getItemid());
			LOG.info("[unposting process] ItemID ={} ,perdiodnm ={}", this.vcompanyid, this.vperiodnm);

			if (this.vcompanyid > 0 ) {
				service.rollUpApplyEntries(this.vcompanyid, this.user.getUsername(),
						this.currentries.getItemid().intValue(), 0);
				this.currentries.setApplied(0);
				this.currentries.setStatus("UnPosting");
				this.currentries.setPeriodnm(this.vperiodnm);
			} else
				LOG.warn("[unposting process] Need select the company and period  ");
		} catch (Exception e) {
			LOG.error("[unpostingprocess] Exception in unpostingprocess -> {}", e.getMessage());
		}

		this.lstaccent = service.findByCompanyid(this.vcompanyid);
		PrimeFaces.current().ajax().update("form:dtParent", "form:dtDetails");
		// this.refreshUI();
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
//		LOG.info("[dtParent_rowSelect] lstSelectedEntries = {}", lstSelectedEntries);
		// DataTable td = (DataTable) ev.getSource();

		// LOG.info("Row Index = {}, rowData = {}, rows ={}, selectiion =
		// {}",td.getRowIndex(), td.getRowData(), td.getRows(), td.getSelection());
		// this.currentries = (HfmAccEntries) td.getSelection();
//		this.currentries = lstSelectedEntries.get(0);
		LOG.info("[dtParent_rowSelect] this.currentries = {}", this.currentries);
		currentries.setLstEntriesDet(servicedet.findByItemid(this.currentries.getItemid()));
		LOG.info("[dtParent_rowSelect] Regreso con lstaccentdet = {}", currentries.getLstEntriesDet());

		this.vcompanyid = this.currentries.getCompanyid();
		this.vperiodnm = this.currentries.getPeriodnm();

		// this.refreshUI();
		LOG.info("[dtParent_rowSelect] Company id = {}, period = {}", this.vcompanyid, this.vperiodnm);
		PrimeFaces.current().ajax().update("form:dtDetails");
	}

	/**
	 * Event fired on rowselect of detail table.
	 * 
	 * @param ev
	 */
	public void dtDetails_rowSelect(AjaxBehaviorEvent ev) {
		DataTable td = (DataTable) ev.getSource();
		LOG.info("[dtDetails_rowSelect] Row Index = {}, rowData = {}, rows ={}, selectiion ={}", td.getRowIndex(),
				td.getRowData(), td.getRows(), td.getSelection());
		this.currentdet = (HfmAccEntriesDet) td.getSelection();
		LOG.info("[dtDetails_rowSelect] this.currentdet = {}", this.currentdet);
	}

	public void btnEditOnClick(ActionEvent ev) {
		LOG.info("[btnEditOnClick] Event = {}", ev);
		if (ev.getSource() instanceof CommandButton) {
			CommandButton button = (CommandButton) ev.getSource();
			LOG.info("[btnEditOnClick] value = {}, title = {}, id=", button.getValue(), button.getTitle(), button.getId());
		}
		if (ev.getSource() instanceof HtmlCommandButton) {
			HtmlCommandButton button = (HtmlCommandButton) ev.getSource();
			LOG.info("[btnEditOnClick] value = {}, title = {}", button.getValue(), button.getTitle());
		}
		PrimeFaces.current().executeScript("PF('entryDialogDetailWV').show()");		
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

	public int getVcompanyid() {
		return vcompanyid;
	}

	public void setVcompanyid(int vcompanyid) {
		LOG.info("[setVcompanyid] Recibo companyId = {}", vcompanyid);
		this.vcompanyid = vcompanyid;
	}

	public List<SetCurrecyCode> getLstcurrencies() {
		return lstcurrencies;
	}

	public void setLstcurrencies(List<SetCurrecyCode> lstcurrencies) {
		this.lstcurrencies = lstcurrencies;
	}

	public boolean hasEntryData() {
		boolean retval = (this.currentries != null && this.currentries.getItemid() != null
				&& this.currentries.getItemid() > 0 && this.currentries.getApplied() == 0);
		return retval;
	}

	
	public void rcRefresh() {
		LOG.info("Refreshing after upload file...");
		currentries.setLstEntriesDet(servicedet.findByItemid(this.currentries.getItemid()));
	}
	/**
	 * Clear filters and selection in details table.
	 */
//	private void refreshUI() {
//
//		LOG.info("[refreshUI]  refresh in UI...");
//		PrimeFaces.current().executeScript("PF('dtParentWV').clearFilters()");
//		PrimeFaces.current().executeScript("PF('dtParentWV').clearSelection()");
//		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearFilters()");
//		PrimeFaces.current().executeScript("PF('dtDetailsWV').clearSelection()");
//
//		LOG.info("[refreshUI] ajax update=> form:panelGridRollUpFFSS");
//		PrimeFaces.current().ajax().update("form:panelGridRollUpFFSS");
//
//		LOG.info("[refreshUI] ajax update=> form:dtParent");
//		PrimeFaces.current().ajax().update("form:dtParent");
//
//		LOG.info("[refreshUI] ajax update=> form:dtDetails");
//		PrimeFaces.current().ajax().update("form:dtDetails");
//
//		LOG.info("[refreshUI] ajax update=> form:messages");
//		PrimeFaces.current().ajax().update("form:messages");
//
//		LOG.info("[refreshUI] Finished");
//
//	}

}
