package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the set_reclassif_accounts database table.
 * 
 */
@Entity
@Table(name = "set_reclassif_accounts")
public class SetReclassifAccounts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4212642143691598086L;

	@EmbeddedId
	private SetReclassifAccountsPK id;

	private String destcc;

	private String origincc;

	public SetReclassifAccounts() {
	    this.setId(new SetReclassifAccountsPK());
	}

	public SetReclassifAccountsPK getId() {
		return this.id;
	}

	public void setId(SetReclassifAccountsPK id) {
		this.id = id;
	}

	public String getDestcc() {
		return this.destcc;
	}

	public void setDestcc(String destcc) {
		this.destcc = destcc;
	}

	public String getOrigincc() {
		return this.origincc;
	}

	public void setOrigincc(String origincc) {
		this.origincc = origincc;
	}

	@Override
	public String toString() {
		return "SetReclassifAccounts [id=" + id + ", destcc=" + destcc + ", origincc=" + origincc + "]";
	}

}