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

	private String hfmcode;

	private String tpartnertype;

	public SetPayablesIcp() {
	}

	public SetPayablesIcp(SetPayablesIcpPK id, String hfmcode, String tpartnertype) {
		this.id = id;
		this.hfmcode = hfmcode;
		this.tpartnertype = tpartnertype;
	}

	public SetPayablesIcpPK getId() {
		return this.id;
	}

	public void setId(SetPayablesIcpPK id) {
		this.id = id;
	}

	public String getHfmcode() {
		return this.hfmcode;
	}

	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}

	public String getTpartnertype() {
		return this.tpartnertype;
	}

	public void setTpartnertype(String tpartnertype) {
		this.tpartnertype = tpartnertype;
	}

	@Override
	public String toString() {
		return "SetPayablesIcp [id=" + id + ", hfmcode=" + hfmcode + ", tpartnertype=" + tpartnertype + "]";
	}

}