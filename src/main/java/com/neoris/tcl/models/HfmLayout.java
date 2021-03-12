package com.neoris.tcl.models;

import java.io.Serializable;
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

	@Column(name="account_old")
	private String accountOld;

	private String data;

	private String entity;

	private Timestamp updated;

	public HfmLayout() {
	    this.setId(new HfmLayoutPK());
	}

	public HfmLayout(HfmLayoutPK id, String accountOld, String data, String entity, Timestamp updated) {
		this.id = id;
		this.accountOld = accountOld;
		this.data = data;
		this.entity = entity;
		this.updated = updated;
	}

	public HfmLayoutPK getId() {
		return this.id;
	}

	public void setId(HfmLayoutPK id) {
		this.id = id;
	}

	public String getAccountOld() {
		return this.accountOld;
	}

	public void setAccountOld(String accountOld) {
		this.accountOld = accountOld;
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

	@Override
	public String toString() {
		return "HfmLayout [id=" + id + ", accountOld=" + accountOld + ", data=" + data + ", entity=" + entity
				+ ", updated=" + updated + "]";
	}

}