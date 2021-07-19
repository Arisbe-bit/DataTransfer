package com.neoris.tcl.controller;

import java.time.Year;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.validation.constraints.Min;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.models.HfmFfss;
import com.neoris.tcl.models.HfmFfssDetails;
import com.neoris.tcl.models.HfmLayout;
import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.models.ViewFFSSGrouped;
import com.neoris.tcl.models.ViewRollupMatchFFSS;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.services.HfmLayoutService;
import com.neoris.tcl.services.IHfmFfssDetailsService;
import com.neoris.tcl.services.IHfmFfssService;
import com.neoris.tcl.services.IHfmRollupEntriesService;
import com.neoris.tcl.services.IRollUpProcessService;
import com.neoris.tcl.services.IViewRollupFFSSGconsService;
import com.neoris.tcl.services.IViewRollupMatchFFSSService;
import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.websocket.IWebSocketService;
import com.neoris.tcl.websocket.WebSocketConfig;

@Controller(value = "rollupControllerBean")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RollupController {

	private final static Logger LOG = LoggerFactory.getLogger(RollupController.class);
//	private final static String DT_ROLLUP = "rollupForm:dt-rollup";

//	private final static String ROLLUPS = "/rollup/rollups";
	private final static String FFSS = "/rollup/ffss";
	private static final String SUMARY = "/rollup/sumary";
	private static final String LAYOUT = "/rollup/layout";
	private static final String MOVEMENTS = "/rollup/movements";

	private List<HfmRollupEntries> lstRollUps;
	private List<HfmRollupEntries> lstSelectedRollups;
	private HfmRollupEntries curRollUp;

	private List<HfmFfss> lstHfmFfss;
	private HfmFfss curHfmFfss;

	private List<HfmFfssDetails> lstHfmFfssDetails;

	private List<ViewRollupMatchFFSS> lstMatchAcc;
	private ViewRollupMatchFFSS curMacthAcc;

	// sumarized FFSS
	private List<ViewFFSSGrouped> lstSumFS;
	private ViewFFSSGrouped curFSgroup;

	// HFM Layout
	private List<HfmLayout> lstlayout;
	private HfmLayout curlayout;

	private User user;

	@Autowired
	private IHfmRollupEntriesService service;
	@Autowired
	private IHfmFfssService hfmFfSsService;
	@Autowired
	private IHfmFfssDetailsService hfmFfssDetailsService;
	@Autowired
	private IViewRollupMatchFFSSService matchaccService;
	@Autowired
	private IViewRollupFFSSGconsService serviceFSG;
	@Autowired
	private HfmLayoutService serviceLay;
	@Autowired
	private IWebSocketService webSocketService;
	@Autowired
	private IRollUpProcessService rollUpProcessService;

	private Calendar calendar;
	@Min(1998)
	private int zyear;
	private String zmonth;
	
	@PostConstruct
	public void init() {
		this.user = Functions.getUser();
		this.calendar = Calendar.getInstance();
		this.calendar.add(Calendar.MONTH, -1);

		this.zyear = this.getCurrYear();
		this.zmonth = this.getMonth(calendar.get(Calendar.MONTH) + 1);

		LOG.info("Year:{}, Period:{}", this.zyear, this.zmonth);

		LOG.info("Init rollupControllerBean...");
		setLstRollUps(service.findAll());
		
		LOG.info("Init setting rollup service to webSocketService...");
		webSocketService.setRollUpService(service);
		
		LOG.info("Init setting rollup service to rollUpProcessService...");
		rollUpProcessService.setService(service);
		rollUpProcessService.setUser(user);
		rollUpProcessService.setWebSocketService(webSocketService);		

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

	public int getZyear() {
		return zyear;
	}

	public void setZyear(int zyear) {
		if (zyear < 1998) {
			Functions.addWarnMessage("Not valid Year", "The year must be bigger than 1998");
			PrimeFaces.current().ajax().update("rollupForm:messages");
			return;
		}
		String zID = "rollupForm:dt-rollup:%s:year";
		for (int i = 0; i < this.lstRollUps.size(); i++) {
			this.lstRollUps.get(i).setRyear(String.valueOf(zyear));
			PrimeFaces.current().ajax().update(String.format(zID, i));
		}
		this.zyear = zyear;
	}

	public String getZmonth() {
		return zmonth;
	}

	public void setZmonth(String zmonth) {
		String zID = "rollupForm:dt-rollup:%s:month";
		for (int i = 0; i < this.lstRollUps.size(); i++) {
			this.lstRollUps.get(i).setRperiod(zmonth);
			PrimeFaces.current().ajax().update(String.format(zID, i));
		}
		this.zmonth = zmonth;
	}

	/**
	 * 
	 * @param event
	 */
	public void processSelectedRollUps(ActionEvent event) {
		LOG.info("[processSelectedRollUps] Running process with rollUpBean = {}, event = {}", lstSelectedRollups, event);
		// rollUpProcessService.processRollUps(lstRollUps, lstSelectedRollups);
		LOG.info("[processSelectedRollUps] executing in ASYNC mode. Returning while rollUps are processing...");

		// Initilize status of each rollup pending for processing...
		lstSelectedRollups.stream().forEach(roll -> roll.pending());
		int current = 1;

		for (HfmRollupEntries rollup : lstSelectedRollups) {
			// Review if this is the last rollup to process...
			if(current++ == lstSelectedRollups.size()) {
				rollUpProcessService.setLastProcess(true);
				
			}
			// find rollup and its processing from original list
			// if not, the messages don't refresh
			int idx = this.lstRollUps.indexOf(rollup);
			rollUpProcessService.processRollUp(this.lstRollUps.get(idx));
			//webSocketService.sendPushNotification("Finished company " + rollup.getEntity(), "Sucess", "info");
		}
		
		// clean the selected rollups list...
		lstSelectedRollups = null;

		PrimeFaces.current().executeScript("PF('dtRollUps').unselectAllRows()");
		setLstRollUps(service.findAll());

		String zID = "rollupForm:dt-rollup:%s:year";
		for (int i = 0; i < this.lstRollUps.size(); i++) {
			this.lstRollUps.get(i).setRyear(String.valueOf(zyear));
			PrimeFaces.current().ajax().update(String.format(zID, i));
		}

		String mID = "rollupForm:dt-rollup:%s:month";
		for (int i = 0; i < this.lstRollUps.size(); i++) {
			this.lstRollUps.get(i).setRperiod(zmonth);
			PrimeFaces.current().ajax().update(String.format(mID, i));
		}

		this.curRollUp = null;
	}

	

	public void layoutprocess() {
		LOG.info("Running process with curRollUp = {}", curRollUp);
		try {
			int comapnyid = this.curRollUp.getCompanyid().intValue();
			String period = this.curRollUp.getRperiod();
			String vyear = this.curRollUp.getRyear();

			serviceLay.rollUpLayout(comapnyid, period, vyear, user.getUsername());
		
			
			Functions.addInfoMessage("Layout Process", "Rollup Layout Finished!");
			PrimeFaces.current().ajax().update(getFormNameId() + ":messages");
			
			webSocketService.sendPushNotification( "Finished Layout ","Sucess", "info");
			
			
		} catch (Exception e) {
			LOG.error("Exception in layoutprocess: => {}", e.getMessage());
		}
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
		lstRollUps.forEach(r -> {
			r.setRyear(String.valueOf(this.calendar.get(Calendar.YEAR)));
			r.setRperiod(this.getMonth(this.calendar.get(Calendar.MONTH) + 1));
		});
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
	}

	/**
	 * 
	 * @param rollUp
	 */
	public void setRollUpOnRowClick(HfmRollupEntries rollUp) {
		LOG.info("Obtains curRollUp = {}. Finding companies", rollUp);
	}

	public HfmFfss getCurHfmFfss() {
		return curHfmFfss;
	}

	/**
	 * 
	 * @param curHfmFfss
	 */
	public void setCurHfmFfss(HfmFfss curHfmFfss) {
		LOG.info("Obtains curHfmFfss = {}", curHfmFfss);
		this.curHfmFfss = curHfmFfss;

//		LOG.info("Update view grouped FFSS ...");
//		PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:dtfsgrouped");

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

	public List<HfmFfss> getLstHfmFfss() {
		return lstHfmFfss;
	}

	public void setLstHfmFfss(List<HfmFfss> lstHfmFfss) {
		this.lstHfmFfss = lstHfmFfss;
	}

	public List<HfmFfssDetails> getLstHfmFfssDetails() {
		return lstHfmFfssDetails;
	}

	public void setLstHfmFfssDetails(List<HfmFfssDetails> lstHfmFfssDetails) {
		this.lstHfmFfssDetails = lstHfmFfssDetails;
	}

	public int getCurrYear() {
		return Year.now().getValue();
	}

	public String getTitle() {
		return "RollUps";
	}

	public List<ViewRollupMatchFFSS> getLstMatchAcc() {
		return lstMatchAcc;
	}

	public void setLstMatchAcc(List<ViewRollupMatchFFSS> lstMatchAcc) {
		this.lstMatchAcc = lstMatchAcc;
	}

	public ViewRollupMatchFFSS getCurMacthAcc() {
		return curMacthAcc;
	}

	public void setCurMacthAcc(ViewRollupMatchFFSS curMacthAcc) {
		this.curMacthAcc = curMacthAcc;
	}

	public List<ViewFFSSGrouped> getLstSumFS() {
		return lstSumFS;
	}

	public void setLstSumFS(List<ViewFFSSGrouped> lstSumFS) {
		this.lstSumFS = lstSumFS;
	}

	public ViewFFSSGrouped getCurFSgroup() {
		return curFSgroup;
	}

	public void setCurFSgroup(ViewFFSSGrouped curFSgroup) {
		this.curFSgroup = curFSgroup;

		LOG.info("Obtains curFSgroup = {}", curFSgroup);

		int companyid = Integer.valueOf(curFSgroup.getCompanyid());
		String vhfmcode = curFSgroup.getHfmcode();
		String partnerid = curFSgroup.getPartnerid();
		String costcenter = curFSgroup.getCostcenter();
		String accountid = curFSgroup.getAccountid();

		try {

			LOG.info("Query FFSS Details LIST with company = {}", companyid);

			this.lstHfmFfssDetails = hfmFfssDetailsService
					.findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartnerid(companyid, vhfmcode,
							costcenter, accountid, partnerid);

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

//		LOG.info("Update view FFSS Details ...");
//		PrimeFaces.current().ajax().update("rollupForm:messages", "rollupForm:tabHHFFDetail");

	}

	public List<HfmLayout> getLstlayout() {
		return lstlayout;
	}

	public void setLstlayout(List<HfmLayout> lstlayout) {
		this.lstlayout = lstlayout;
	}

	public HfmLayout getCurlayout() {
		return curlayout;
	}

	public void setCurlayout(HfmLayout curlayout) {
		this.curlayout = curlayout;
	}

	public void periodChange() {
		try {

			LOG.info("periodchange company");

		} catch (Exception e) {
			LOG.error("period change ERRor -> {}", e.getMessage());
		}

	}

	

	/**
	 * 
	 * @return
	 */
	public String submitToFFSS() {

		Long companyId = curRollUp.getCompanyid();

		try {
			LOG.info("Query HFM_FFSS with company = {}", companyId);
			this.lstHfmFfss = hfmFfSsService.findByCompanyId(companyId);

			LOG.info("return lstHfmFfss with items => {}", lstHfmFfss != null ? lstHfmFfss.size() : "is null");

			LOG.info("Query MATCH ACCOUNT LIST with company = {}", companyId);

			this.lstMatchAcc = matchaccService.findByCompanyid(companyId);
			LOG.info("return lstMatchAcc with items => {}", lstMatchAcc != null ? lstMatchAcc.size() : "is null");

			if (this.lstMatchAcc == null || this.lstMatchAcc.isEmpty()) {
				String mensaje = String.format("No Match account records found for companyId=%s", companyId);
				LOG.info(mensaje);
				// Functions.addWarnMessage("Attention", mensaje);
			} else {
				LOG.info("Records for lstMatchAcc = {}", lstMatchAcc);
			}

			
		} catch (Exception e) {
			LOG.error("ERROR in setCurRollUp -> {}", e.getMessage());
		}

		LOG.info("Redirecting to {}....", FFSS);
		return FFSS;
	}

	/**
	 * 
	 * @return
	 */
	public String submitToSumary() {

		Long companyid = curHfmFfss.getcompanyId();
		String hfmcode = curHfmFfss.getHfmcode().toString();

		try {
			LOG.info("Query SUM FFSS  LIST with company = {}, hfmcode = {}", companyid, hfmcode);

			this.lstSumFS = serviceFSG.findByCompanyidAndHfmcode(companyid.toString(), hfmcode);

		
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
		// layoutprocess();
		int companyid = curRollUp.getCompanyid().intValue();

		LOG.info("Redirecting to {}....", LAYOUT);
		LOG.info("Initializing lstLayout...");
		// this.lstlayout = serviceLay.findAll();

		LOG.info("Query lstlayout LIST with company = {}", companyid);

		this.lstlayout = serviceLay.findByIdCompanyid(companyid);
		LOG.info("return lstlayout with items => {}", lstlayout != null ? lstlayout.size() : "is null");

		if (this.lstlayout == null || this.lstlayout.isEmpty()) {
			String mensaje = String.format("No records found for companyId: %s", companyid);
			LOG.info(mensaje);
		} else {
			LOG.info("Records for lstlayout = {}", lstlayout);
		}

		return LAYOUT;
	}

	/**
	 * 
	 * @param rollup
	 * @param companyId
	 * @param numDrill
	 * @return
	 */
//	private Thread createRollUpTread(ProcessRollUps rollup) {
//		LOG.info("create thread for ProcessRollUps => {}", rollup);
//		Thread thead = new Thread(rollup);
//		thead.setName(rollup.getProcessId());
//		LOG.info("Return with thead => {}", thead);
//		return thead;
//	}

	private String getMonth(int month) {
		return String.format("%02d", month);
	}

	public String getWebSocketEndPoint() {
		return WebSocketConfig.WS_ROLLUPS_ENDPOINT;
	}

	public String getWebSocketTopic() {
		return WebSocketConfig.WS_ROLLUPS_TOPIC;
	}

	public String getWebSocketApp() {
		return WebSocketConfig.WS_ROLLUPS_APP;
	}

	public String getWebSocketMapping() {
		return WebSocketConfig.WS_ROLLUPS_MAPPING;
	}

	public String getContextPath() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	}
	
//	private void sendPushNotification(HfmRollupEntries rollup) {
//		sendPushNotification("","","",rollup);
//	}
//
//	/**
//	 * Sends a Push notification to all clients for update the status of the current rollup
//	 * @param message .- Optional message to show in notification browser
//	 * @param title .- Title of the optional  message
//	 * @param severity . Severity of message (info, warn, error)
//	 * @param rollup .- Current rollup been processed.
//	 */
//	private void sendPushNotification(String message, String title, String severity, HfmRollupEntries rollup) {
//		Optional<HfmRollupEntries> ru = service.findById(rollup.getCompanyid());
//		RollUpMessage rum = new RollUpMessage(message, title, severity, ru.orElse(rollup));
//		webSocketService.notyfyRollUpProcess(rum);
//	}
}
