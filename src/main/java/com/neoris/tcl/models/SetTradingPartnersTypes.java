package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the set_trading_partners_types database table.
 * 
 */
@Entity
@Table(name = "set_trading_partners_types")
public class SetTradingPartnersTypes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5339771232153970467L;

	@Id
	private String tptype;
	private String description;
	private String userid;

	public SetTradingPartnersTypes() {
	}

	
	public SetTradingPartnersTypes(String tptype, String description, String userid) {
		
		this.tptype = tptype;
		this.description = description;
		this.userid = userid;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getTptype() {
		return this.tptype;
	}

	public void setTptype(String tptype) {
		this.tptype = tptype;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "SetTradingPartnersTypes [tptype=" + tptype + ", description=" + description + ", userid=" + userid
				+ "]";
	}

	

}