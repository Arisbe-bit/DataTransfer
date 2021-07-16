package com.neoris.tcl.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HFM_FFSS_MATCH_FFSSHIST")
public class ViewRollupMatchFFSSHist implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2663649348957971319L;

	@Id
	private Long num;

	private Long companyid;

	private String hfmcode;

	private BigDecimal balance;

	private String classification;

	private String naturalb;

	private String period;

	private String periodid;

	private String errortext;

	private String description;

	public ViewRollupMatchFFSSHist() {

	}

	public ViewRollupMatchFFSSHist(Long num, Long companyid, String hfmcode, BigDecimal balance, String classification,
			String naturalb, String period, String periodid, String errortext, String description) {
		super();
		this.num = num;
		this.companyid = companyid;
		this.hfmcode = hfmcode;
		this.balance = balance;
		this.classification = classification;
		this.naturalb = naturalb;
		this.period = period;
		this.periodid = periodid;
		this.errortext = errortext;
		this.description = description;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public String getHfmcode() {
		return hfmcode;
	}

	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String geterrortext() {
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

	public String getFormatedamount() {
		String retval;
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		retval = nf.format(this.balance);
		return retval;
	}

	public String getPeriodid() {
		return periodid;
	}

	public void setPeriodid(String periodid) {
		this.periodid = periodid;
	}

	@Override
	public String toString() {
		return "ViewRollupMatchFFSSHist [num=" + num + ", companyid=" + companyid + ", hfmcode=" + hfmcode
				+ ", balance=" + balance + ", classification=" + classification + ", naturalb=" + naturalb + ", period="
				+ period + ", periodid=" + periodid + ", errortext=" + errortext + ", description=" + description + "]";
	}

}
