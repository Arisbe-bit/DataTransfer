package com.neoris.tcl.controller;

import java.time.Year;
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

import com.neoris.tcl.models.HfmFFSSDetailsHist;
import com.neoris.tcl.models.HfmFfSsHist;
import com.neoris.tcl.models.HfmLayoutHist;
import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.models.ViewFFSSGroupedHist;
import com.neoris.tcl.models.ViewRollupMatchFFSSHist;
import com.neoris.tcl.services.HfmLayoutHistService;
import com.neoris.tcl.services.IHfmFfssDetailsHistService;
import com.neoris.tcl.services.IHfmFfssHistService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.services.IViewRollupFFSSGconsHistService;
import com.neoris.tcl.services.IViewRollupMatchFFSSHistService;
import com.neoris.tcl.utils.ViewScope;

@Controller(value = "rolluphistControllerBean")
@Scope("session")
public class RollupHistController {

	private final static Logger LOG = LoggerFactory.getLogger(RollupHistController.class);
	
	private final static String DT_ROLLUP = "rollupForm:dt-rolluphist";
	private final static String FFSS = "/rolluphist/ffsshist";
	private static final String SUMARY = "/rolluphist/sumaryhist";
	private static final String LAYOUT = "/rolluphist/layouthist";
	private static final String MOVEMENTS = "/rolluphist/movementshist";

	private List<HfmRollupEntries> lstRollUps;
	private List<HfmRollupEntries> lstSelectedRollups;
	private HfmRollupEntries curRollUp;

	private List<HfmFfSsHist> lstHfmFfss;
	private HfmFfSsHist curHfmFfss;

	private List<HfmFFSSDetailsHist> lstHfmFfssDetails;

	private List<ViewRollupMatchFFSSHist> lstMatchAcc;
	private ViewRollupMatchFFSSHist curMacthAcc;

	// HFM Layout
	private List<HfmLayoutHist> lstlayout;
	private HfmLayoutHist curlayout;

	// sumarized FFSS
	private List<ViewFFSSGroupedHist> lstSumFS;
	private ViewFFSSGroupedHist curFSgroup;

	@Autowired
	private IHfmRollupEntriesService service;
	@Autowired
	private IHfmFfssHistService hfmFfSsService;
	@Autowired
	private IHfmFfssDetailsHistService hfmFfssDetailsService;
	@Autowired
	private IViewRollupMatchFFSSHistService matchaccService;
	@Autowired
	private IViewRollupFFSSGconsHistService serviceFSG;
	@Autowired
	private HfmLayoutHistService serviceLay;

	@PostConstruct
	public void init() {
		// Fill the rollup entity list
		//setLstRollUps(service.findAll());
		this.lstRollUps = service.findAll();
	}

	public void openNew() {
		this.setCurRollUp(new HfmRollupEntries());
	}

	public String getprocessButtonMessage() {
		String retval = "Process";
		String message = "Start processing %s Rollups";
		if (hasSelectedRollUps()) {
			retval = String.format(message, lstSelectedRollups.size());
		}
		return retval;
	}

	public String getProcessButtonStyleClass() {
		if (hasSelectedRollUps()) {
			return "ui-button-primary";
		} else {
			return "ui-button-secondary";
		}
	}

	public String getFormNameId() {
		return "rollupForm";
	}

	public boolean hasSelectedRollUps() {
		return this.lstSelectedRollups != null && !this.lstSelectedRollups.isEmpty();
	}

	/**
	 * 
	 * @param event
	 */
	public void viewRollUp(ActionEvent event) {
		LOG.info("Entering to view detail of rollUpBean = {}, event = {}", this.curRollUp, event);
		PrimeFaces.current().ajax().update(getFormNameId() + ":messages", getFormNameId() + ":dt-rollup");
		PrimeFaces.current().executeScript("PF('dtRollUps').unselectAllRows()");
	}

	public List<HfmRollupEntries> getLstRollUps() {
		return lstRollUps;
	}

	public void setLstRollUps(List<HfmRollupEntries> lstRollUps) {
		lstRollUps.forEach(r -> r.clean());
		this.lstRollUps = lstRollUps;
	}

	public HfmRollupEntries getCurRollUp() {
		return curRollUp;
	}

	/**
	 * 
	 * @param curRollUp
	 */
	public void setCurRollUp(HfmRollupEntries curRollUp) {
		LOG.info("Obtain curRollUp = {}", curRollUp);
		this.curRollUp = curRollUp;
		Long companyId = curRollUp.getCompanyid();
		String perdionm = curRollUp.getRperiod() + "-" + curRollUp.getRyear().substring(2);

		try {
			LOG.info("Query HFM_FFSS hist with company = {}, period = {}", companyId, perdionm);
			this.lstHfmFfss = hfmFfSsService.findByCompanyidAndPeriodid(companyId, perdionm);

			LOG.info("return lstHfmFfss hist with items => {}", lstHfmFfss != null ? lstHfmFfss.size() : "is null");

			LOG.info("Query MATCH ACCOUNT Hist LIST with company = {}, period = {}", companyId, perdionm);

			this.lstMatchAcc = matchaccService.findByCompanyidAndPeriodid(companyId, perdionm);
			LOG.info("return lstMatchAcc with items => {}", lstMatchAcc != null ? lstMatchAcc.size() : "is null");

			if (this.lstMatchAcc == null || this.lstMatchAcc.isEmpty()) {
				String mensaje = String.format("No Match account records found for companyId=%s", companyId);
				LOG.info(mensaje);

			} else {
				LOG.info("Records for hist lstMatchAcc = {}", lstMatchAcc);
			}

			LOG.info("Query lstlayout hist LIST with company = {}", companyId);

			this.lstlayout = serviceLay.findByIdCompanyid(companyId.intValue());
			LOG.info("return lstlayout with items => {}", lstlayout != null ? lstlayout.size() : "is null");

			if (this.lstlayout == null || this.lstlayout.isEmpty()) {
				String mensaje = String.format("No records found for companyId=%s", companyId);
				LOG.info(mensaje);
				// Functions.addWarnMessage("Attention", mensaje);
			} else {
				LOG.info("Records for lstlayout = {}", lstlayout);
			}

		} catch (Exception e) {
			LOG.error("ERRor -> {}", e.getMessage());
		}
//	         LOG.info("Update view...");
//	         PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:tabViewRollUps:dt-hfm-tab-ffss", "rollupForm:tabViewRollUps:dt-macthacc");

	}

	public void setRollUpOnRowClick(HfmRollupEntries rollUp) {
		LOG.info("Obtains curRollUp = {}. Finding companies", rollUp);
	}

	public HfmFfSsHist getCurHfmFfss() {
		return curHfmFfss;
	}

	/**
	 * 
	 * @param curHfmFfss
	 */
	public void setCurHfmFfss(HfmFfSsHist curHfmFfss) {
		LOG.info("Obtains curHfmFfss = {}", curHfmFfss);
		this.curHfmFfss = curHfmFfss;
		Long companyid = curHfmFfss.getcompanyid();
		String vhfmcode = curHfmFfss.getHfmcode();

		try {

			LOG.info("Query SUM FFSS  LIST with company = {}, vhfmcode {}", companyid, vhfmcode);

			// this.lstFSgrouped =
			// serviceFSG.findByCompanyidAndhfmparentAndhfmcode(companyId.intValue(),vhfmcode,vhfmcode);
			this.lstSumFS = serviceFSG.findByCompanyidAndHfmcode(companyid.toString(), vhfmcode);
			// LOG.info("return lstFSgrouped with items => {}", lstFSgrouped != null ?
			// lstFSgrouped.size() : "is null");
			LOG.info("return lstFSgrouped with items => {}", lstSumFS != null ? lstSumFS.size() : "is null");

			if (this.lstSumFS == null || this.lstSumFS.isEmpty()) {
				String mensaje = String.format("No records found (Sumarized FFSS )for companyId=%s", companyid);
				LOG.info(mensaje);
				// Functions.addWarnMessage("Attention", mensaje);
			} else {
				LOG.info("Records for lstSumFS = {}", lstSumFS);

				// this.lstSumFS.forEach(i -> LOG.info( i != null ? i.toString() : "item is
				// null!!!"));
			}
		} catch (Exception e) {
			LOG.error("ERRor -> {}", e.getMessage());
		}

		LOG.info("Update view grouped FFSS ...");
		PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:tabViewRollUps:dtfsgrouped");

	}

	/**
	 * 
	 * @param event
	 */
	public void curHfmffssClic(AjaxBehaviorEvent event) {
		LOG.info("curHfmffssClic with event = {}", event.getComponent());
	}

	/**
	 * 
	 * @return
	 */
	public List<HfmRollupEntries> getLstSelectedRollups() {
		return lstSelectedRollups;
	}

	public void setLstSelectedRollups(List<HfmRollupEntries> lstSelectedRollups) {
		this.lstSelectedRollups = lstSelectedRollups;
	}

	public List<HfmFfSsHist> getLstHfmFfss() {
		return lstHfmFfss;
	}

	public void setLstHfmFfss(List<HfmFfSsHist> lstHfmFfss) {
		this.lstHfmFfss = lstHfmFfss;
	}

	public List<HfmFFSSDetailsHist> getLstHfmFfssDetails() {
		return lstHfmFfssDetails;
	}

	public void setLstHfmFfssDetails(List<HfmFFSSDetailsHist> lstHfmFfssDetails) {
		this.lstHfmFfssDetails = lstHfmFfssDetails;
	}

	public int getCurrYear() {
		return Year.now().getValue();
	}

	public String getTitle() {
		return "RollUps";
	}

	public List<ViewRollupMatchFFSSHist> getLstMatchAcc() {
		return lstMatchAcc;
	}

	public void setLstMatchAcc(List<ViewRollupMatchFFSSHist> lstMatchAcc) {
		this.lstMatchAcc = lstMatchAcc;
	}

	public ViewRollupMatchFFSSHist getCurMacthAcc() {
		return curMacthAcc;
	}

	public void setCurMacthAcc(ViewRollupMatchFFSSHist curMacthAcc) {
		this.curMacthAcc = curMacthAcc;
	}

	public List<ViewFFSSGroupedHist> getLstSumFS() {
		return lstSumFS;
	}

	public void setLstSumFS(List<ViewFFSSGroupedHist> lstSumFS) {
		this.lstSumFS = lstSumFS;
	}

	public ViewFFSSGroupedHist getCurFSgroup() {
		return curFSgroup;
	}

	public void setCurFSgroup(ViewFFSSGroupedHist curFSgroup) {
		this.curFSgroup = curFSgroup;

		LOG.info("Obtains curFSgroup = {}", curFSgroup);

		int companyid = Integer.valueOf(curFSgroup.getCompanyid());
		String vhfmcode = curFSgroup.getHfmcode();
		String partnerid = curFSgroup.getPartnerid();
		String costcenter = curFSgroup.getCostcenter();
		String accountid = curFSgroup.getAccountid();
		String periodnm = curFSgroup.getPeriodname();
		String periodid = curRollUp.getRperiod() + curRollUp.getRyear().substring(2);

		try {

			LOG.info(
					"Query FFSS Details Hist LIST with company = {}, hfmcode {}, partnerid {}, costcenter {}, accountid {}, periodnm {}",
					companyid, vhfmcode, partnerid, costcenter, accountid, periodnm);
//findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndIdPeriodnm
			this.lstHfmFfssDetails = hfmFfssDetailsService
					.findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndIdPeriodnm(companyid,
							vhfmcode, costcenter, accountid, partnerid, periodnm);

			LOG.info("return lstHfmFfssDetails with items => {}",
					lstHfmFfssDetails != null ? lstHfmFfssDetails.size() : "is null");

			if (this.lstHfmFfssDetails == null || this.lstHfmFfssDetails.isEmpty()) {
				String mensaje = String.format("No records found ( FFSS Details )for companyId=%s", companyid);
				LOG.info(mensaje);
				// Functions.addWarnMessage("Attention", mensaje);
			} else {
				LOG.info("Records for lstHfmFfssDetails = {}", lstHfmFfssDetails);

				this.lstHfmFfssDetails.forEach(i -> LOG.info(i != null ? i.toString() : "item is null!!!"));
			}
		} catch (Exception e) {
			LOG.error("ERRor -> {}", e.getMessage());
		}

		LOG.info("Update view FFSS Details Hist ...");
		PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:tabViewRollUps:tabHHFFDetail");

	}

	public List<HfmLayoutHist> getLstlayout() {
		return lstlayout;
	}

	public void setLstlayout(List<HfmLayoutHist> lstlayout) {
		this.lstlayout = lstlayout;
	}

	public HfmLayoutHist getCurlayout() {
		return curlayout;
	}

	public void setCurlayout(HfmLayoutHist curlayout) {
		this.curlayout = curlayout;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String submitToFFSS() {
		
		LOG.info("Redirecting to {}....", FFSS);

		try {
			
			Long companyid = curRollUp.getCompanyid();
			String period = curRollUp.getRperiod() + curRollUp.getRyear().substring(2);

			LOG.info("Query HFM_FFSSHIST with companyid = {},period = {}", companyid,curRollUp.getRperiod() + curRollUp.getRyear().substring(2));
			this.lstHfmFfss = hfmFfSsService.findByCompanyidAndPeriodid(companyid, curRollUp.getRperiod() + curRollUp.getRyear().substring(2));

			LOG.info("return lstHfmFfsshist with items => {}", lstHfmFfss != null ? lstHfmFfss.size() : "is null");

			LOG.info("Query MATCH ACCOUNT HIST LIST with company = {}", companyid);

			this.lstMatchAcc = matchaccService.findByCompanyidAndPeriodid(companyid, period);
			LOG.info("return lstMatchAcc hist with items => {}", lstMatchAcc != null ? lstMatchAcc.size() : "is null");

			if (this.lstMatchAcc == null || this.lstMatchAcc.isEmpty()) {
				String mensaje = String.format("No Match account records found for companyId=%s", companyid);
				LOG.info(mensaje);
				// Functions.addWarnMessage("Attention", mensaje);
			} /*else {
				LOG.info("Records for lstMatchAcc = {}", lstMatchAcc);
			}*/

			
		} catch (Exception e) {
			LOG.error("ERROR in setCurRollUp -> {}", e.getMessage());
		} 
		
		
		return FFSS;
	}

	/**
	 * 
	 * @return
	 */
	public String submitToSumary() {

		Long companyid = curHfmFfss.getcompanyid();
		String hfmcode = curHfmFfss.getHfmcode().toString();

		try {
			LOG.info("Query SUM FFSS  LIST with companyid = {}, hfmcode = {}", companyid, hfmcode);

			this.lstSumFS = serviceFSG.findByCompanyidAndHfmcode(companyid.toString(), hfmcode);

			// LOG.info("return lstFSgrouped with items => {}", lstFSgrouped != null ?
			// lstFSgrouped.size() : "is null");
			LOG.info("return lstFSgrouped with items => {}", lstSumFS != null ? lstSumFS.size() : "is null");

			if (this.lstSumFS == null || this.lstSumFS.isEmpty()) {
				String mensaje = String.format("No records found (Sumarized FFSS )for companyId=%s", companyid);
				LOG.info(mensaje);
				// Functions.addWarnMessage("Attention", mensaje);
			} else {
				LOG.info("Records for lstSumFS = {}", lstSumFS);

				// this.lstSumFS.forEach(i -> LOG.info( i != null ? i.toString() : "item is
				// null!!!"));
			}
		} catch (Exception e) {
			LOG.error("setCurHfmFfss ERRor -> {}", e.getMessage());
		}

		LOG.info("Redirecting to {}....", SUMARY);
		return SUMARY;
	}

	/**
	 * 
	 * @return
	 */
	public String submitToMovements() {
		LOG.info("Redirecting to {}....", MOVEMENTS);
		return MOVEMENTS;
	}

	/**
	 * 
	 * @return
	 */
	public String submitToLayouts() {
		LOG.info("Redirecting to {}....", LAYOUT);
		Long companyId = curRollUp.getCompanyid();
		String periodnm = curRollUp.getRperiod() +  curRollUp.getRyear().substring(2);

		
		LOG.info("Query lstlayoutHist LIST with company = {}, periodnm = {}", companyId,periodnm);

		this.lstlayout = serviceLay.findByIdCompanyidAndPeriodid(companyId.intValue(), periodnm);
		LOG.info("return lstlayouthist with items => {}", lstlayout != null ? lstlayout.size() : "is null");

		if (this.lstlayout == null || this.lstlayout.isEmpty()) {
			String mensaje = String.format("No records found for companyId: %s", companyId);
			LOG.info(mensaje);
			// Functions.addWarnMessage("Attention", mensaje);
		} else { 
			LOG.info("Records for lstlayout = {}", lstlayout);
		}
	        
		return LAYOUT;
	}

	
}
