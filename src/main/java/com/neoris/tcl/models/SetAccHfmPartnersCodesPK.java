package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * The primary key class for the set_acc_hfm_partners_codes database table.
 * 
 */
@Embeddable
public class SetAccHfmPartnersCodesPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6389104512054734051L;

	private String accountid;

	private String hfmcode;
	
	private String costcenter;

	public SetAccHfmPartnersCodesPK() {
	}

	public SetAccHfmPartnersCodesPK(String accountid, String hfmcode, String costcenter) {
		this.accountid = accountid;
		this.hfmcode = hfmcode;
		this.costcenter = costcenter;
	}

	public String getAccountid() {
		return this.accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getHfmcode() {
		return this.hfmcode;
	}

	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountid, hfmcode, costcenter);
	}

	public String getCostcenter() {
		return costcenter;
	}

	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetAccHfmPartnersCodesPK other = (SetAccHfmPartnersCodesPK) obj;
		return Objects.equals(accountid, other.accountid) && Objects.equals(hfmcode, other.hfmcode)  
				&& Objects.equals(costcenter, other.costcenter);
	}

	@Override
	public String toString() {
		return "SetAccHfmPartnersCodesPK [accountid=" + accountid + ", hfmcode=" + hfmcode + ", costcenter="
				+ costcenter + "]";
	}

	
	

}