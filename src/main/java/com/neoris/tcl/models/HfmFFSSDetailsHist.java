package com.neoris.tcl.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ROLLUP_VFFSSDET_HIST")
public class HfmFFSSDetailsHist implements Serializable {

	private static final long serialVersionUID = 5676213260556508605L;

	@EmbeddedId
	private HfmFfssDetailsHistPK id;

	private String tpname;

	@Column(name = "HFMCODE_OLD")
	private String hfmcodeold;

	@Column(name = "BATCH_NAME")
	private String batchname;
	private String docnumber;

	private String upd;
	private String hfmparent;
	private String userid;

	@Column(name = "ERROR_TEXT")
	private String errortext;

	@Column(name = "JE_HEADER_ID")
	private int headerid;

	@Column(name = "JE_HEADER_ID_REV", columnDefinition = "INTEGER default 0 ")
	private int headeridrev;

	private BigDecimal debit;
	private BigDecimal credit;

	@Column(name = "TRANS_DEBIT")
	private BigDecimal transdebit;

	@Column(name = "TRANS_CREDIT")
	private BigDecimal transcredit;

	@Column(name = "INVOICE_DATE")
	private String invoicedate;

	private String category;

	private int omit;
	private String areaid;
	private String periodid;

	public HfmFFSSDetailsHist() {
		this.setId(new HfmFfssDetailsHistPK());
	}

	public HfmFFSSDetailsHist(HfmFfssDetailsHistPK id, String tpname, String hfmcodeold, String batchname,
			String docnumber, String upd, String hfmparent, String userid, String errortext, int headerid,
			int headeridrev, BigDecimal debit, BigDecimal credit, BigDecimal transdebit, BigDecimal transcredit,
			String invoicedate, String category, int omit, String areaid, String periodid) {

		this.id = id;
		this.tpname = tpname;
		this.hfmcodeold = hfmcodeold;
		this.batchname = batchname;
		this.docnumber = docnumber;
		this.upd = upd;
		this.hfmparent = hfmparent;
		this.userid = userid;
		this.errortext = errortext;
		this.headerid = headerid;
		this.headeridrev = headeridrev;
		this.debit = debit;
		this.credit = credit;
		this.transdebit = transdebit;
		this.transcredit = transcredit;
		this.invoicedate = invoicedate;
		this.category = category;
		this.omit = omit;
		this.areaid = areaid;
		this.periodid = periodid;
	}

	public HfmFfssDetailsHistPK getId() {
		return id;
	}

	public void setId(HfmFfssDetailsHistPK id) {
		this.id = id;
	}

	public String getTpname() {
		return tpname;
	}

	public void setTpname(String tpname) {
		this.tpname = tpname;
	}

	public String getHfmcodeold() {
		return hfmcodeold;
	}

	public void setHfmcodeold(String hfmcodeold) {
		this.hfmcodeold = hfmcodeold;
	}

	public String getBatchname() {
		return batchname;
	}

	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}

	public String getDocnumber() {
		return docnumber;
	}

	public void setDocnumber(String docnumber) {
		this.docnumber = docnumber;
	}

	public String getUpd() {
		return upd;
	}

	public void setUpd(String upd) {
		this.upd = upd;
	}

	public String getHfmparent() {
		return hfmparent;
	}

	public void setHfmparent(String hfmparent) {
		this.hfmparent = hfmparent;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getErrortext() {
		return errortext;
	}

	public void setErrortext(String errortext) {
		this.errortext = errortext;
	}

	public int getHeaderid() {
		return headerid;
	}

	public void setHeaderid(int headerid) {
		this.headerid = headerid;
	}

	public int getHeaderidrev() {
		return headeridrev;
	}

	public void setHeaderidrev(int headeridrev) {
		this.headeridrev = headeridrev;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCredit() {
		return credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public BigDecimal getTransdebit() {
		return transdebit;
	}

	public void setTransdebit(BigDecimal transdebit) {
		this.transdebit = transdebit;
	}

	public BigDecimal getTranscredit() {
		return transcredit;
	}

	public void setTranscredit(BigDecimal transcredit) {
		this.transcredit = transcredit;
	}

	public String getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getOmit() {
		return omit;
	}

	public void setOmit(int omit) {
		this.omit = omit;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getPeriodid() {
		return periodid;
	}

	public void setPeriodid(String periodid) {
		this.periodid = periodid;
	}

	@Override
	public String toString() {
		return "HfmFFSSDetailsHist [id=" + id + ", tpname=" + tpname + ", hfmcodeold=" + hfmcodeold + ", batchname="
				+ batchname + ", docnumber=" + docnumber + ", upd=" + upd + ", hfmparent=" + hfmparent + ", userid="
				+ userid + ", errortext=" + errortext + ", headerid=" + headerid + ", headeridrev=" + headeridrev
				+ ", debit=" + debit + ", credit=" + credit + ", transdebit=" + transdebit + ", transcredit="
				+ transcredit + ", invoicedate=" + invoicedate + ", category=" + category + ", omit=" + omit
				+ ", areaid=" + areaid + ", periodid=" + periodid + "]";
	}

}
