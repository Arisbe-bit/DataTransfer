package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the set_acc_hfm_partners_codes database table.
 * 
 */
@Entity
@Table(name = "SET_ACC_HFM_CODES")
public class SetAccHfmPartnersCodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6102853690474970823L;

	@EmbeddedId
	private SetAccHfmPartnersCodesPK id;

	private int companyid;

	private String icpcode;

	public SetAccHfmPartnersCodes() {
		this.setId(new SetAccHfmPartnersCodesPK());
	}

	public SetAccHfmPartnersCodes(SetAccHfmPartnersCodesPK id, int companyid, String icpcode, String partnerid,
			String tpartnertype) {
		this.id = id;

		this.companyid = companyid;
		this.icpcode = icpcode;

	}

	public SetAccHfmPartnersCodesPK getId() {
		return this.id;
	}

	public void setId(SetAccHfmPartnersCodesPK id) {
		this.id = id;
	}

	public int getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	public String getIcpcode() {
		return this.icpcode;
	}

	public void setIcpcode(String icpcode) {
		this.icpcode = icpcode;
	}

	@Override
	public String toString() {
		return "SetAccHfmPartnersCodes [id=" + id + ", companyid=" + companyid + ", icpcode=" + icpcode + "]";
	}

}