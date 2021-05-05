package com.neoris.tcl.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLLUP_VIEW_FFSSHIST")
public class HfmFfSsHist implements Serializable {

	private static final long serialVersionUID = 5362381129863237217L;

	@Id
	private Long num;
	// private HfmFfssPK id;

	@Column(name = "companyid")
	private Long companyid;

	@Column(name = "CURRENCY_CODE", columnDefinition = "VARCHAR(3) default 'USD'")
	private String currencyCode;

	@Column(name = "HFMCODE")
	private String hfmcode;

	@Column(name = "PERIOD")
	private String period;

	private BigDecimal balance;

	@Column(name = "hfmcode_old")
	private String hfmcodeOld;

	@Column(name = "UPD")
	private Timestamp updated;

	private String userid;
	private String classification;
	private String naturalb;

	@Column(name = "error_text")
	private String errortext;

	private String description;

	
	private String periodid;

	public HfmFfSsHist() {

		this.balance = new BigDecimal(0);
	}

	public HfmFfSsHist(Long num, Long companyid, String currencyCode, String hfmcode, String period, BigDecimal balance,
			String hfmcodeOld, Timestamp updated, String userid, String classification, String naturalb,
			String errortext, String description, String periodid) {

		this.num = num;
		this.companyid = companyid;
		this.currencyCode = currencyCode;
		this.hfmcode = hfmcode;
		this.period = period;
		this.balance = balance;
		this.hfmcodeOld = hfmcodeOld;
		this.updated = updated;
		this.userid = userid;
		this.classification = classification;
		this.naturalb = naturalb;
		this.errortext = errortext;
		this.description = description;
		this.periodid = periodid;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Long getcompanyid() {
		return companyid;
	}

	public void setcompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getHfmcode() {
		return hfmcode;
	}

	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getErrortext() {
		return errortext;
	}

	public void setErrortext(String errortext) {
		this.errortext = errortext;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getHfmcodeOld() {
		return this.hfmcodeOld;
	}

	public void setHfmcodeOld(String hfmcodeOld) {
		this.hfmcodeOld = hfmcodeOld;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFormatedBalance() {
		String retval;
		// NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
		retval = nf.format(this.balance); // + " " + this.getId().getCurrencyCode();
		return retval;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getNaturalb() {
		return naturalb;
	}

	public void setNaturalb(String naturalb) {
		this.naturalb = naturalb;
	}

	public String getError_text() {
		return errortext;
	}

	public void seterrortext(String errortext) {
		this.errortext = errortext;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPeriodid() {
		return periodid;
	}

	public void setPeriodid(String periodid) {
		this.periodid = periodid;
	}

	@Override
	public String toString() {
		return "HfmFfSsHist [num=" + num + ", companyid=" + companyid + ", currencyCode=" + currencyCode + ", hfmcode="
				+ hfmcode + ", period=" + period + ", balance=" + balance + ", hfmcodeOld=" + hfmcodeOld + ", updated="
				+ updated + ", userid=" + userid + ", classification=" + classification + ", naturalb=" + naturalb
				+ ", errortext=" + errortext + ", description=" + description + ", periodid=" + periodid + "]";
	}

}
