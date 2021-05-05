package com.neoris.tcl.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name="hfm_layout_hist")
public class HfmLayoutHist implements Serializable  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1707875553545677091L;

	@EmbeddedId
	private HfmLayoutHistPK id;

	
	private String data;

	private String entity;
	
	private String userid;

	private Timestamp updated;
	
	private String periodid;

	public HfmLayoutHist() {
	    this.setId(new HfmLayoutHistPK());
	}

	

	public HfmLayoutHist(HfmLayoutHistPK id, String data, String entity, String userid, Timestamp updated,
			String periodid) {
		
		this.id = id;
		this.data = data;
		this.entity = entity;
		this.userid = userid;
		this.updated = updated;
		this.periodid = periodid;
	}



	public HfmLayoutHistPK getId() {
		return this.id;
	}

	public void setId(HfmLayoutHistPK id) {
		this.id = id;
	}

	
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getPeriodid() {
		return periodid;
	}



	public void setPeriodid(String periodid) {
		this.periodid = periodid;
	}



	@Override
	public String toString() {
		return "HfmLayoutHist [id=" + id + ", data=" + data + ", entity=" + entity + ", userid=" + userid + ", updated="
				+ updated + ", periodid=" + periodid + "]";
	}

	
	

	
}
