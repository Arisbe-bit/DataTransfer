package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the set_icpcodes database table.
 * 
 */
@Entity
@Table(name = "set_icpcodes")
public class SetIcpcodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1388880172612949672L;

	@EmbeddedId
	private SetIcpcodesPK id;

	private String icpdesc;

	public SetIcpcodes() {
	    this.setId(new SetIcpcodesPK());
	}

	public SetIcpcodesPK getId() {
		return this.id;
	}

	public void setId(SetIcpcodesPK id) {
		this.id = id;
	}

	public String getIcpdesc() {
		return this.icpdesc;
	}

	public void setIcpdesc(String icpdesc) {
		this.icpdesc = icpdesc;
	}

	@Override
	public String toString() {
		return "SetIcpcodes [id=" + id + ", icpdesc=" + icpdesc + "]";
	}

}