package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "hfm_manual_entries")
public class HfmAccEntries implements Serializable {

	private static final long serialVersionUID = 1717875553545677091L;
	private final static Logger LOG = LoggerFactory.getLogger(HfmAccEntries.class);

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ")
	@SequenceGenerator(sequenceName = "SEQROLLUPMENTRIES", allocationSize = 1, name = "ITEM_SEQ")
	private Long itemid;

	private int companyid;
	private String periodnm;
	private String userid;

	@ColumnDefault(value = "0")
	private int applied;
	
	private String description;
	
	@ColumnDefault(value = "UnPosting")
	private String status;

//	@OneToMany
//	@JoinColumn(name = "itemid")
	@Transient
	private List<HfmAccEntriesDet> lstEntriesDet;

	public HfmAccEntries() {
		this.lstEntriesDet = new ArrayList<HfmAccEntriesDet>();
	}

	

	public HfmAccEntries(Long itemid, int companyid, java.lang.String periodnm, java.lang.String userid, int applied,
			java.lang.String description, java.lang.String status, List<HfmAccEntriesDet> lstEntriesDet) {
		
		this.itemid = itemid;
		this.companyid = companyid;
		this.periodnm = periodnm;
		this.userid = userid;
		this.applied = applied;
		this.description = description;
		this.status = status;
		this.lstEntriesDet = lstEntriesDet;
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

	public List<HfmAccEntriesDet> getLstEntriesDet() {
		return lstEntriesDet;
	}

	public void setLstEntriesDet(List<HfmAccEntriesDet> lstEntriesDet) {
		LOG.info("Recibo lstEntriesDet = {}", lstEntriesDet);
		this.lstEntriesDet = lstEntriesDet;
	}



	@Override
	public String toString() {
		return "HfmAccEntries [itemid=" + itemid + ", companyid=" + companyid + ", periodnm=" + periodnm + ", userid="
				+ userid + ", applied=" + applied + ", description=" + description + ", status=" + status
				+ ", lstEntriesDet=" + lstEntriesDet + "]";
	}

	

}
