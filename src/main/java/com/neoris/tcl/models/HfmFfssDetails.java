package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the hfm_ffss_details database table.
 * 
 */
@Entity
@Table(name="hfm_ffss_details")
public class HfmFfssDetails implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5676213260556508605L;

	@EmbeddedId
	private HfmFfssDetailsPK id;

	@Column(name="batch_name")
	private String batchName;

	private String docnumber;

	private String tpname;

	private String userid;

	private String hfmparent;
	
	
	private String category;
	

	private String debit;
	
	private String credit;
	
	@Column(name = "trans_credit")
	private String transcredit;
	
	@Column(name = "trans_debit")
	private String transdebit;
	
	@Column(name = "error_text")
	private String errortext;
	
	@Column(name = "invoice_date")
	private String invoicedate;
	
	@Column(name = "je_header_id")
	private String jeheaderid;
	
	@Column(name = "je_header_id_rev")
	private String jeheaderidrev;

	
	private String omit;
	
	@Column(name = "period_name")
	private String periodname;
	
	public String getPeriodname() {
		return periodname;
	}



	public void setPeriodname(String periodname) {
		this.periodname = periodname;
	}



	public HfmFfssDetails() {
	    this.setId(new HfmFfssDetailsPK());
	}



	public HfmFfssDetails(HfmFfssDetailsPK id, String batchName, String docnumber, String tpname, String userid,
			String hfmparent, String category, String debit, String credit, String transcredit, String transdebit,
			String errortext, String invoicedate, String jeheaderid, String jeheaderidrev, String omit, String periodname) {
		this.id = id;
		this.batchName = batchName;
		this.docnumber = docnumber;
		this.tpname = tpname;
		this.userid = userid;
		this.hfmparent = hfmparent;
		this.category = category;
		this.debit = debit;
		this.credit = credit;
		this.transcredit = transcredit;
		this.transdebit = transdebit;
		this.errortext = errortext;
		this.invoicedate = invoicedate;
		this.jeheaderid = jeheaderid;
		this.jeheaderidrev = jeheaderidrev;
		this.omit = omit;
		this.periodname = periodname;
	}



	public String getHfmparent() {
		return hfmparent;
	}



	public void setHfmparent(String hfmparent) {
		this.hfmparent = hfmparent;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getDebit() {
		return debit;
	}



	public void setDebit(String debit) {
		this.debit = debit;
	}



	public String getCredit() {
		return credit;
	}



	public void setCredit(String credit) {
		this.credit = credit;
	}



	public String getTranscredit() {
		return transcredit;
	}



	public void setTranscredit(String transcredit) {
		this.transcredit = transcredit;
	}



	public String getTransdebit() {
		return transdebit;
	}



	public void setTransdebit(String transdebit) {
		this.transdebit = transdebit;
	}



	public String getErrortext() {
		return errortext;
	}



	public void setErrortext(String errortext) {
		this.errortext = errortext;
	}



	public String getInvoicedate() {
		return invoicedate;
	}



	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}



	public String getJeheaderid() {
		return jeheaderid;
	}



	public void setJeheaderid(String jeheaderid) {
		this.jeheaderid = jeheaderid;
	}



	public String getJeheaderidrev() {
		return jeheaderidrev;
	}



	public void setJeheaderidrev(String jeheaderidrev) {
		this.jeheaderidrev = jeheaderidrev;
	}



	public String getOmit() {
		return omit;
	}



	public void setOmit(String omit) {
		this.omit = omit;
	}



	public HfmFfssDetailsPK getId() {
		return this.id;
	}

	public void setId(HfmFfssDetailsPK id) {
		this.id = id;
	}

	public String getBatchName() {
		return this.batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getDocnumber() {
		return this.docnumber;
	}

	public void setDocnumber(String docnumber) {
		this.docnumber = docnumber;
	}

	public String getTpname() {
		return this.tpname;
	}

	public void setTpname(String tpname) {
		this.tpname = tpname;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}



	@Override
	public String toString() {
		return "HfmFfssDetails [id=" + id + ", batchName=" + batchName + ", docnumber=" + docnumber + ", tpname="
				+ tpname + ", userid=" + userid + ", hfmparent=" + hfmparent + ", category=" + category + ", debit="
				+ debit + ", credit=" + credit + ", transcredit=" + transcredit + ", transdebit=" + transdebit
				+ ", errortext=" + errortext + ", invoicedate=" + invoicedate + ", jeheaderid=" + jeheaderid
				+ ", jeheaderidrev=" + jeheaderidrev + ", omit=" + omit + ", periodname=" + periodname + "]";
	}



}
