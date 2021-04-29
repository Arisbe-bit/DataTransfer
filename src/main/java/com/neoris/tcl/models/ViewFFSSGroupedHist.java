package com.neoris.tcl.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Subselect;


@Entity
@Subselect("select num,companyid,hfmcode,periodname,costcenter,accountid,icp,partnerid,tpname,omit,hfmparent,amount from ROLLUP_VIEW_FFSS_GROUPEDHIST" )
public class ViewFFSSGroupedHist implements Serializable{
	
private static final long serialVersionUID = 5676214260556508605L;
	
	@Id
	private String num;
	
	private String companyid;
	private String periodname;
	private String costcenter;
	private String hfmcode;
	private String accountid;
	private String icp;
	private String partnerid;
	private String tpname;
	private String omit;
	private BigDecimal amount;
	private String hfmparent;
	
	public ViewFFSSGroupedHist() {
		
	}
	
	
	

	public ViewFFSSGroupedHist(String num, String companyid, String periodname, String costcenter, String hfmcode,
			String accountid, String icp, String partnerid, String tpname, String omit, BigDecimal amount,
			String hfmparent) {
		this.num = num;
		this.companyid = companyid;
		this.periodname = periodname;
		this.costcenter = costcenter;
		this.hfmcode = hfmcode;
		this.accountid = accountid;
		this.icp = icp;
		this.partnerid = partnerid;
		this.tpname = tpname;
		this.omit = omit;
		this.amount = amount;
		this.hfmparent = hfmparent;
	}




	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getPeriodname() {
		return periodname;
	}

	public void setPeriodname(String periodname) {
		this.periodname = periodname;
	}

	public String getCostcenter() {
		return costcenter;
	}

	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}

	public String getHfmcode() {
		return hfmcode;
	}

	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getTpname() {
		return tpname;
	}

	public void setTpname(String tpname) {
		this.tpname = tpname;
	}

	public String getOmit() {
		return omit;
	}

	public void setOmit(String omit) {
		this.omit = omit;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getHfmparent() {
		return hfmparent;
	}

	public void setHfmparent(String hfmparent) {
		this.hfmparent = hfmparent;
	}

	@Override
	public String toString() {
		return "ViewFFSSGrouped [num=" + num + ", companyid=" + companyid + ", periodname=" + periodname
				+ ", costcenter=" + costcenter + ", hfmcode=" + hfmcode + ", accountid=" + accountid + ", icp=" + icp
				+ ", partnerid=" + partnerid + ", tpname=" + tpname + ", omit=" + omit + ", amount=" + amount
				+ ", hfmparent=" + hfmparent + "]";
	}
	
	
	
	
	
	
	
	


}
