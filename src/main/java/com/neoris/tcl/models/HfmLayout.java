package com.neoris.tcl.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the hfm_layout database table.
 * 
 */
@Entity
@Table(name="hfm_layout")
public class HfmLayout implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1707875553545677091L;

	@EmbeddedId
	private HfmLayoutPK id;



	private BigDecimal data;

	private String entity;
	
	private String userid;

	private Timestamp updated;

	public HfmLayout() {
	    this.setId(new HfmLayoutPK());
	}

	public HfmLayout(HfmLayoutPK id,  BigDecimal data, String entity, Timestamp updated, String userid) {
		this.id = id;
		
		this.data = data;
		this.entity = entity;
		this.updated = updated;
		this.userid = userid;
	}

	public HfmLayoutPK getId() {
		return this.id;
	}

	public void setId(HfmLayoutPK id) {
		this.id = id;
	}

	

	public BigDecimal getData() {
		return this.data;
	}

	public void setData(BigDecimal data) {
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

	@Override
	public String toString() {
		return "HfmLayout [id=" + id + ", data=" + data + ", entity=" + entity + ", userid=" + userid + ", updated="
				+ updated + "]";
	}

	

	

}