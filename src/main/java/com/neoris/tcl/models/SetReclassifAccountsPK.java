package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * The primary key class for the set_reclassif_accounts database table.
 * 
 */
@Embeddable
public class SetReclassifAccountsPK implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7650922966653268829L;

	private String hfmcodeorig;
	private String hfmcodedest;
	private String costcenter;
	private String accountidini;
	private String accountidfin;
	private String partnerid;
	private int companyid;

	public SetReclassifAccountsPK() {
	}

	
	public SetReclassifAccountsPK(String hfmcodeorig, String hfmcodedest, String costcenter, String accountidini,
			String accountidfin, String partnerid, int companyid) {
		
		this.hfmcodeorig = hfmcodeorig;
		this.hfmcodedest = hfmcodedest;
		this.costcenter = costcenter;
		this.accountidini = accountidini;
		this.accountidfin = accountidfin;
		this.partnerid = partnerid;
		this.companyid = companyid;
	}


	@Override
	public int hashCode() {
		return Objects.hash(accountidini, accountidfin,hfmcodeorig,hfmcodedest,costcenter,partnerid,companyid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetReclassifAccountsPK other = (SetReclassifAccountsPK) obj;
		return Objects.equals(accountidini, other.accountidini) && Objects.equals(accountidfin, other.accountidfin)
				&& Objects.equals(companyid, other.companyid) && Objects.equals(costcenter, other.costcenter)
				&& Objects.equals(partnerid, other.partnerid) && Objects.equals(hfmcodeorig, other.hfmcodeorig)
				&& Objects.equals(hfmcodedest, other.hfmcodedest) ;
	}


	public String getHfmcodeorig() {
		return hfmcodeorig;
	}


	public void setHfmcodeorig(String hfmcodeorig) {
		this.hfmcodeorig = hfmcodeorig;
	}


	public String getHfmcodedest() {
		return hfmcodedest;
	}


	public void setHfmcodedest(String hfmcodedest) {
		this.hfmcodedest = hfmcodedest;
	}


	public String getCostcenter() {
		return costcenter;
	}


	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}


	public String getAccountidini() {
		return accountidini;
	}


	public void setAccountidini(String accountidini) {
		this.accountidini = accountidini;
	}


	public String getAccountidfin() {
		return accountidfin;
	}


	public void setAccountidfin(String accountidfin) {
		this.accountidfin = accountidfin;
	}


	public String getPartnerid() {
		return partnerid;
	}


	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}


	public int getCompanyid() {
		return companyid;
	}


	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}


	@Override
	public String toString() {
		return "SetReclassifAccountsPK [hfmcodeorig=" + hfmcodeorig + ", hfmcodedest=" + hfmcodedest + ", costcenter="
				+ costcenter + ", accountidini=" + accountidini + ", accountidfin=" + accountidfin + ", partnerid="
				+ partnerid + ", companyid=" + companyid + "]";
	}

	
}