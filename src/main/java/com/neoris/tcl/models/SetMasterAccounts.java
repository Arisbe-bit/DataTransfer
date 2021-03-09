package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the set_master_accounts database table.
 * 
 */
@Entity
@Table(name = "set_master_accounts")
public class SetMasterAccounts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1310531147662914808L;

	@Id
	private String hmfparent;

	private String naturalsign;

	public SetMasterAccounts() {
	}

	public String getHmfparent() {
		return this.hmfparent;
	}

	public void setHmfparent(String hmfparent) {
		this.hmfparent = hmfparent;
	}

	public String getNaturalsign() {
		return this.naturalsign;
	}

	public void setNaturalsign(String naturalsign) {
		this.naturalsign = naturalsign;
	}

	@Override
	public String toString() {
		return "SetMasterAccounts [hmfparent=" + hmfparent + ", naturalsign=" + naturalsign + "]";
	}

}