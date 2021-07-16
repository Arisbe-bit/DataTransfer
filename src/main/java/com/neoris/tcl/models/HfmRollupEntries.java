package com.neoris.tcl.models;

import java.io.Serializable;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The persistent class for the hfm_rollup_entries database table.
 * 
 */
@Entity
@Table(name = "hfm_rollup_entries")
public class HfmRollupEntries implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4557949662258406721L;

	private final static Logger LOG = LoggerFactory.getLogger(HfmRollupEntries.class);
	private final static String PROCESS_ICON = "fa fa-refresh fa-spin fa-2x";
	private static final String PROCESS_ICON_OK = "fa fa-check-square-o fa-2x icon-green";
	private static final String PROCESS_ICON_ERROR = "fa fa-window-close-o fa-2x icon-red";
	private final static String PENDING_ICON = "fa fa-spinner fa-pulse fa-2x fa-fw";

	public final static String STATUS_PROCESSING = "PROCESSING";
	public final static String STATUS_PENDING = "PROCESSING";
	public final static String STATUS_OK = "OK";
	public final static String STATUS_ERROR = "ERROR";

	@Id
	private Long companyid;

	private String entity;

	private String attribute1;

	private String attribute2;

	private String attribute3;

	private String attribute4;

	private String attribute5;

	private String attribute6;

	private String rapplication;

	private String reclassifications;

	private String rperiod;

	private String rvalue;

	private String rview;

	private String ryear;

	private String scenario;

	private String segment;

	private String segment1;

	private String validations;

	private String initdate;

	private String findate;
	
	private String stockvar;

	public HfmRollupEntries() {

	}

	
	public HfmRollupEntries(Long companyid, String entity, String attribute1, String attribute2, String attribute3,
			String attribute4, String attribute5, String attribute6, String rapplication, String reclassifications,
			String rperiod, String rvalue, String rview, String ryear, String scenario, String segment, String segment1,
			String validations, String initdate, String findate, String stockvar) {
		
		this.companyid = companyid;
		this.entity = entity;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.attribute5 = attribute5;
		this.attribute6 = attribute6;
		this.rapplication = rapplication;
		this.reclassifications = reclassifications;
		this.rperiod = rperiod;
		this.rvalue = rvalue;
		this.rview = rview;
		this.ryear = ryear;
		this.scenario = scenario;
		this.segment = segment;
		this.segment1 = segment1;
		this.validations = validations;
		this.initdate = initdate;
		this.findate = findate;
		this.stockvar = stockvar;
	}


	public String getStockvar() {
		return stockvar;
	}


	public void setStockvar(String stockvar) {
		this.stockvar = stockvar;
	}


	public String getInitdate() {
		return initdate;
	}

	public void setInitdate(String initdate) {
		this.initdate = initdate;
	}

	public String getFindate() {
		return findate;
	}

	public void setFindate(String findate) {
		this.findate = findate;
	}

	public Long getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public String getAttribute1() {
		return this.attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return this.attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return this.attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return this.attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return this.attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return this.attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getRapplication() {
		return this.rapplication;
	}

	public void setRapplication(String rapplication) {
		this.rapplication = rapplication;
	}

	public String getReclassifications() {
		return this.reclassifications;
	}

	public void setReclassifications(String reclassifications) {
		this.reclassifications = reclassifications;
	}

	public String getRperiod() {
		return this.rperiod;
	}

	public void setRperiod(String rperiod) {
		this.rperiod = rperiod;
	}

	public String getRvalue() {
		return this.rvalue;
	}

	public void setRvalue(String rvalue) {
		this.rvalue = rvalue;
	}

	public String getRview() {
		return this.rview;
	}

	public void setRview(String rview) {
		this.rview = rview;
	}

	public String getRyear() {
		return this.ryear;
	}

	public void setRyear(String ryear) {
		this.ryear = ryear;
	}

	public String getScenario() {
		return this.scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getSegment() {
		return this.segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getValidations() {
		return this.validations;
	}

	public void setValidations(String validations) {
		this.validations = validations;
	}

	public String getFullPeriod() {
		return this.getRperiod() + "-" + this.getRyear();
	}

	public String getSegment1() {
		return segment1;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}

	/**
	 * This method is needed for the rollup.xhtml form when changing the year or
	 * period in the page. This does nothing, but is required with ajax callback.
	 * 
	 * @param ev
	 */
	public void changeItemValue(AjaxBehaviorEvent ev) {
		if (ev.getSource() instanceof HtmlSelectOneMenu) {
			HtmlSelectOneMenu selectOne = (HtmlSelectOneMenu) ev.getSource();
			LOG.info("Receive HtmlSelectOneMenu Componet. Id = {}, value = {}", selectOne.getId(),
					selectOne.getValue());
		}

		if (ev.getSource() instanceof HtmlInputText) {
			HtmlInputText inputText = (HtmlInputText) ev.getSource();
			LOG.info("Receive HtmlInputText Componet. Id = {}, value = {}", inputText.getId(), inputText.getValue());
		}
	}

	public void clean() {
		clean("");
	}

	public void pending() {
		clean(STATUS_PENDING);
	}

	public void clean(String status) {
		this.attribute1 = status;
		this.attribute2 = status;
		this.attribute3 = status;
		this.attribute4 = status;
		this.attribute5 = status;
		this.attribute6 = status;
		this.stockvar = status;
	}

	public String getStockvarIcon() {
		return getProcessIcon(this.stockvar);
	}
	
	public String getTrialBalanceIcon() {
		return getProcessIcon(this.attribute1);
	}

	public String getPayablesIcon() {
		return getProcessIcon(this.attribute2);
	}

	public String getReceivablesIcon() {
		return getProcessIcon(this.attribute3);
	}

	public String getBalanceValidationIcon() {
		return getProcessIcon(this.attribute4); // ICP
	}

	public String getCostCenterValidationIcon() {
		return getProcessIcon(this.attribute5); // cost center //opex area
	}

	public String getFinishedProcessIcon() {
		return getProcessIcon(this.attribute6);
	}

	public String getValidationsIcon() {
		return getProcessIcon(this.validations);// accounts
	}

	private String getProcessIcon(String status) {
		if (status == null) {
			status = "";
		}
		String icon = "";
		if (status.equalsIgnoreCase(STATUS_PROCESSING)) {
			icon = PROCESS_ICON;
		}
		if (status.equalsIgnoreCase(STATUS_OK)) {
			icon = PROCESS_ICON_OK;
		}
		if (status.equalsIgnoreCase(STATUS_ERROR)) {
			icon = PROCESS_ICON_ERROR;
		}
		if (status.equalsIgnoreCase(STATUS_PENDING)) {
			icon = PENDING_ICON;
		}

		return icon;
	}

	public String getStockvarValidationID() {
		return "i-stock-va-" + this.companyid;
	}
	
	public String getBalanceValidationID() {
		return "i-ba-va-" + this.companyid;
	}

	public String getTradingPartnerValidationID() {
		return "i-tra-pa-va-" + this.companyid;
	}

	public String getCostCenterValidationID() {
		return "i-cos-ce-va-" + this.companyid;
	}

	public String getAccountBalanceValidationID() {
		return "i-acc-bal-va-" + this.companyid;
	}

	public String getFinishProcessID() {
		return "i-finish-process-" + this.companyid;
	}

	public String getFinDateId() {
		return "fin-date-" + this.companyid;
	}

	public String getIniDateId() {
		return "ini-date-" + this.companyid;
	}


	@Override
	public String toString() {
		return "HfmRollupEntries [companyid=" + companyid + ", entity=" + entity + ", attribute1=" + attribute1
				+ ", attribute2=" + attribute2 + ", attribute3=" + attribute3 + ", attribute4=" + attribute4
				+ ", attribute5=" + attribute5 + ", attribute6=" + attribute6 + ", rapplication=" + rapplication
				+ ", reclassifications=" + reclassifications + ", rperiod=" + rperiod + ", rvalue=" + rvalue
				+ ", rview=" + rview + ", ryear=" + ryear + ", scenario=" + scenario + ", segment=" + segment
				+ ", segment1=" + segment1 + ", validations=" + validations + ", initdate=" + initdate + ", findate="
				+ findate + ", stockvar=" + stockvar + "]";
	}

	

}
