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
@Table(name = "set_receivables_icp")
public class SetReceivablesIcp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6327999594931698296L;

	@EmbeddedId
	private SetReceivablesIcpPK id;

	private String hfmcode;

	private String tpartnertype;

	public SetReceivablesIcp() {
	}

	public SetReceivablesIcpPK getId() {
		return this.id;
	}

	public void setId(SetReceivablesIcpPK id) {
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
		return "SetReceivablesIcp [id=" + id + ", hfmcode=" + hfmcode + ", tpartnertype=" + tpartnertype + "]";
	}

}