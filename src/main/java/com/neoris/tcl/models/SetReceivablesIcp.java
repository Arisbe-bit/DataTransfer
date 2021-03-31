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

	public String getIcpcode() {
		return icpcode;
	}

	public void setIcpcode(String icpcode) {
		this.icpcode = icpcode;
	}

	private String userid;

	private String modified;

	public SetReceivablesIcp() {
		this.setId(new SetReceivablesIcpPK());
	}

	public SetReceivablesIcpPK getId() {
		return this.id;
	}

	public SetReceivablesIcp(SetReceivablesIcpPK id, String userid, String modified) {
		this.id = id;
		this.userid = userid;
		this.modified = modified;
	}

	public void setId(SetReceivablesIcpPK id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "SetReceivablesIcp [id=" + id + ", icpcode=" + icpcode + ", userid=" + userid + ", modified=" + modified
				+ "]";
	}

}