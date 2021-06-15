package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the set_receivables_icp database table.
 * 
 */
@Entity
@Table(name = "SET_RECEIVABLES_ICP")
public class SetReceivablesIcp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6327999594931698296L;

	@EmbeddedId
	private SetReceivablesIcpPK id;
	private String icpcode;
	private String userid;
	//private String modified;

	public SetReceivablesIcp() {
		this.id = new SetReceivablesIcpPK();
	}

	public SetReceivablesIcp(SetReceivablesIcpPK id, String icpcode, String userid) {
		this.id = id;
		this.icpcode = icpcode;
		this.userid = userid;
		//this.modified = modified;
	}

	public SetReceivablesIcpPK getId() {
		return id;
	}

	public void setId(SetReceivablesIcpPK id) {
		this.id = id;
	}

	public String getIcpcode() {
		return icpcode;
	}

	public void setIcpcode(String icpcode) {
		this.icpcode = icpcode;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	@Override
	public String toString() {
		return String.format("SetReceivablesIcp [id=%s, icpcode=%s, userid=%s, modified=%s]", id, icpcode, userid);
	}

}