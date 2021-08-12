package com.neoris.tcl.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "ROLLUP_VMANUAL_ENTRIES")
public class viewmanualentries implements Serializable{
	private static final long serialVersionUID = 1716895553545677091L;
	
	@Id
	private Long num;
	
	private Long itemid;
	private String entity ;
	private int companyid;
	private String periodnm;
	private String userid;
	private int applied;
	private String description;
	private String status;
	private Long movid;
	private String hfmcode;
	private BigDecimal amount;
	private String icpcode;
	private String areaid;
	private String currencyid;
	private String tptype;
	private Date periodname;
	private int periodid;
	private String recurrent;
	
	public viewmanualentries() {
		
	}
	
	

	public viewmanualentries(Long num, Long itemid, String entity, int companyid, String periodnm, String userid,
			int applied, String description, String status, Long movid, String hfmcode, BigDecimal amount,
			String icpcode, String areaid, String currencyid, String tptype, Date periodname, int periodid,
			String recurrent) {
		
		this.num = num;
		this.itemid = itemid;
		this.entity = entity;
		this.companyid = companyid;
		this.periodnm = periodnm;
		this.userid = userid;
		this.applied = applied;
		this.description = description;
		this.status = status;
		this.movid = movid;
		this.hfmcode = hfmcode;
		this.amount = amount;
		this.icpcode = icpcode;
		this.areaid = areaid;
		this.currencyid = currencyid;
		this.tptype = tptype;
		this.periodname = periodname;
		this.periodid = periodid;
		this.recurrent = recurrent;
	}



	public String getRecurrent() {
		return recurrent;
	}



	public void setRecurrent(String recurrent) {
		this.recurrent = recurrent;
	}



	public int getPeriodid() {
		return periodid;
	}






	public void setPeriodid(int periodid) {
		this.periodid = periodid;
	}






	public Date getPeriodname() {
		return periodname;
	}





	public void setPeriodname(Date periodname) {
		this.periodname = periodname;
	}





	public String getEntity() {
		return entity;
	}





	public void setEntity(String entity) {
		this.entity = entity;
	}





	public Long getItemid() {
		return itemid;
	}
	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	public String getPeriodnm() {
		return periodnm;
	}
	public void setPeriodnm(String periodnm) {
		this.periodnm = periodnm;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getApplied() {
		return applied;
	}
	public void setApplied(int applied) {
		this.applied = applied;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getMovid() {
		return movid;
	}
	public void setMovid(Long movid) {
		this.movid = movid;
	}
	public String getHfmcode() {
		return hfmcode;
	}
	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getIcpcode() {
		return icpcode;
	}
	public void setIcpcode(String icpcode) {
		this.icpcode = icpcode;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getCurrencyid() {
		return currencyid;
	}
	public void setCurrencyid(String currencyid) {
		this.currencyid = currencyid;
	}
	public String getTptype() {
		return tptype;
	}
	public void setTptype(String tptype) {
		this.tptype = tptype;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}



	@Override
	public String toString() {
		return "viewmanualentries [num=" + num + ", itemid=" + itemid + ", entity=" + entity + ", companyid="
				+ companyid + ", periodnm=" + periodnm + ", userid=" + userid + ", applied=" + applied
				+ ", description=" + description + ", status=" + status + ", movid=" + movid + ", hfmcode=" + hfmcode
				+ ", amount=" + amount + ", icpcode=" + icpcode + ", areaid=" + areaid + ", currencyid=" + currencyid
				+ ", tptype=" + tptype + ", periodname=" + periodname + ", periodid=" + periodid + ", recurrent="
				+ recurrent + "]";
	}



	
	
}
