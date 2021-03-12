package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the set_hfm_codes database table.
 * 
 */
@Entity
@Table(name = "set_hfm_codes")
public class SetHfmCodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -732130139597244868L;

	@Id
	private String hfmcode;

	private String tptype;

	public SetHfmCodes() {
	}

	public SetHfmCodes(String hfmcode, String tptype) {
		super();
		this.hfmcode = hfmcode;
		this.tptype = tptype;
	}

	public String getHfmcode() {
		return this.hfmcode;
	}

	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}

	public String getTptype() {
		return this.tptype;
	}

	public void setTptype(String tptype) {
		this.tptype = tptype;
	}

	@Override
	public String toString() {
		return "SetHfmCodes [hfmcode=" + hfmcode + ", tptype=" + tptype + "]";
	}

}