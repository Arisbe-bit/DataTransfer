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
@Table(name = "set_acc_hfm_partners_codes")
public class SetAccHfmPartnersCodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6102853690474970823L;

	@EmbeddedId
	private SetAccHfmPartnersCodesPK id;

	private String areaid;

	private Long companyid;

	private String icpcode;

	private String partnerid;

	private String tpartnertype;

	public SetAccHfmPartnersCodes() {
	}

	public SetAccHfmPartnersCodes(SetAccHfmPartnersCodesPK id, String areaid, Long companyid, String icpcode,
			String partnerid, String tpartnertype) {
		this.id = id;
		this.areaid = areaid;
		this.companyid = companyid;
		this.icpcode = icpcode;
		this.partnerid = partnerid;
		this.tpartnertype = tpartnertype;
	}

	public SetAccHfmPartnersCodesPK getId() {
		return this.id;
	}

	public void setId(SetAccHfmPartnersCodesPK id) {
		this.id = id;
	}

	public String getAreaid() {
		return this.areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public Long getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public String getIcpcode() {
		return this.icpcode;
	}

	public void setIcpcode(String icpcode) {
		this.icpcode = icpcode;
	}

	public String getPartnerid() {
		return this.partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}

	public String getTpartnertype() {
		return this.tpartnertype;
	}

	public void setTpartnertype(String tpartnertype) {
		this.tpartnertype = tpartnertype;
	}

	@Override
	public String toString() {
		return "SetAccHfmPartnersCodes [id=" + id + ", areaid=" + areaid + ", companyid=" + companyid + ", icpcode="
				+ icpcode + ", partnerid=" + partnerid + ", tpartnertype=" + tpartnertype + "]";
	}

}