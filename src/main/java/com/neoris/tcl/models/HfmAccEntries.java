package com.neoris.tcl.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "hfm_manual_entries")
public class HfmAccEntries implements Serializable {
	
	private static final long serialVersionUID = 1717875553545677091L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ")
    @SequenceGenerator(sequenceName = "SEQROLLUPMENTRIES", allocationSize = 1, name = "ITEM_SEQ")
	private Long itemid;
	private int companyid;
	private String periodnm;
	private String userid;
	
	@ColumnDefault(value = "0")
	private int applied;
	
	public HfmAccEntries() {
		
	}
	

	

	public HfmAccEntries(Long itemid, int companyid, String periodnm, String userid, int applied) {

		this.itemid = itemid;
		this.companyid = companyid;
		this.periodnm = periodnm;
		this.userid = userid;
		this.applied = applied;
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




	public Long getItemid() {
		return itemid;
	}




	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}




	public int getApplied() {
		return applied;
	}




	public void setApplied(int applied) {
		this.applied = applied;
	}




	@Override
	public String toString() {
		return "HfmAccEntries [itemid=" + itemid + ", companyid=" + companyid + ", periodnm=" + periodnm + ", userid="
				+ userid + ", applied=" + applied + "]";
	}




	
	

}
