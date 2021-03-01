package com.neoris.tcl.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "HFM_FFSS_DETAILS")
@IdClass(HfmFFSSDetailsId.class)
public class HfmFFSSDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4977420856798738359L;

	@Column(name = "HFMCODE", length = 20, nullable = false)
	private String hfmcode;

	@Id
	@Column(name = "ACCOUNTID", length = 30, nullable = false)
	private String accountId;

	@Column(name = "ICP", length = 20)
	private String icp;

	@Column(name = "AMOUNT", length = 20, nullable = false)
	private String amount;

	@Column(name = "PERIOD", length = 20, nullable = false)
	private String period;

	@Id
	@Column(name = "PARTNERID", length = 20)
	private String partnerId;

	@Column(name = "TPNAME", length = 150)
	private String tpName;

	@Column(name = "HFMCODE_OLD", length = 20)
	private String hfmcodeOld;

	@Id
	@Column(name = "AREADID", length = 20)
	private String areadId;

	@Id
	@Column(name = "COMPANYID", nullable = false)
	private Long companyId;

	@Column(name = "DATASOURCE", length = 30)
	private String datasource;

	@Column(name = "CURRENCY_CODE", length = 5)
	private String currencyCode;

	@Column(name = "BATCH_NAME", length = 200)
	private String batchName;

	@Column(name = "DOCNUMBER", length = 100)
	private String docnumber;

	@Column(name = "COSTCENTER", length = 10)
	private String costcenter;

	@Column(name = "UPD")
	private LocalDate upd;
	
	public HfmFFSSDetails() {
		
	}

	public HfmFFSSDetails(String hfmcode, String accountId, String icp, String amount, String period, String partnerId,
			String tpName, String hfmcodeOld, String areadId, Long companyId, String datasource, String currencyCode,
			String batchName, String docnumber, String costcenter, LocalDate upd) {
		this.hfmcode = hfmcode;
		this.accountId = accountId;
		this.icp = icp;
		this.amount = amount;
		this.period = period;
		this.partnerId = partnerId;
		this.tpName = tpName;
		this.hfmcodeOld = hfmcodeOld;
		this.areadId = areadId;
		this.companyId = companyId;
		this.datasource = datasource;
		this.currencyCode = currencyCode;
		this.batchName = batchName;
		this.docnumber = docnumber;
		this.costcenter = costcenter;
		this.upd = upd;
	}

	public String getHfmcode() {
		return hfmcode;
	}

	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getTpName() {
		return tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}

	public String getHfmcodeOld() {
		return hfmcodeOld;
	}

	public void setHfmcodeOld(String hfmcodeOld) {
		this.hfmcodeOld = hfmcodeOld;
	}

	public String getAreadId() {
		return areadId;
	}

	public void setAreadId(String areadId) {
		this.areadId = areadId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getDocnumber() {
		return docnumber;
	}

	public void setDocnumber(String docnumber) {
		this.docnumber = docnumber;
	}

	public String getCostcenter() {
		return costcenter;
	}

	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}

	public LocalDate getUpd() {
		return upd;
	}

	public void setUpd(LocalDate upd) {
		this.upd = upd;
	}

	@Override
	public String toString() {
		return "HfmFFSSDetails [hfmcode=" + hfmcode + ", accountId=" + accountId + ", icp=" + icp + ", amount=" + amount
				+ ", period=" + period + ", partnerId=" + partnerId + ", tpName=" + tpName + ", hfmcodeOld="
				+ hfmcodeOld + ", areadId=" + areadId + ", companyId=" + companyId + ", datasource=" + datasource
				+ ", currencyCode=" + currencyCode + ", batchName=" + batchName + ", docnumber=" + docnumber
				+ ", costcenter=" + costcenter + ", upd=" + upd + "]";
	}
	
}
