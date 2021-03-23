package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the set_payables_icp database table.
 * 
 */
@Entity
@Table(name = "set_payables_icp")
public class SetPayablesIcp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1316855062351354754L;

	@EmbeddedId
	private SetPayablesIcpPK id;

	private String icpcode;
	public String getIcpcode() {
		return icpcode;
	}

	public void setIcpcode(String icpcode) {
		this.icpcode = icpcode;
	}

	private String userid;
	private String modified;
	


	public SetPayablesIcp() {
	    this.setId(new SetPayablesIcpPK());
	}

	public SetPayablesIcp(SetPayablesIcpPK id,  String userid, String modified) {
		this.id = id;
		this.userid = userid;
		this.modified = modified;
		
	}

	public SetPayablesIcpPK getId() {
		return this.id;
	}

	public void setId(SetPayablesIcpPK id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SetPayablesIcp [id=" + id + ", userid=" + userid + ", modified=" + modified + "]";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}



}