package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * The primary key class for the  HfmRollupExceptions database table.
 * 
 */

@Embeddable
public class HfmRollupExceptionsPK implements Serializable{

	private static final long serialVersionUID = 7000122966653268829L;
	
	private int partnerid;
	private String costcenter;
	private String accountid;
	private int companyid;
	
	public int getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(int partnerid) {
		this.partnerid = partnerid;
	}
	public String getCostcenter() {
		return costcenter;
	}
	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}
	
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public int getCompanyid() {
		return companyid;
	}
	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}
	
	public HfmRollupExceptionsPK() {
		
	}
	
	public HfmRollupExceptionsPK(int partnerid, String costcenter, String accountid,
			int companyid) {
		
		this.partnerid = partnerid;
		this.costcenter = costcenter;
		this.accountid = accountid;
		this.companyid = companyid;
	}
	
	
	@Override
	public String toString() {
		return "HfmRollupExceptionsPK [partnerid=" + partnerid + ", costcenter=" + costcenter + ", accountid="
				+ accountid + ", companyid=" + companyid + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(companyid, costcenter, accountid,partnerid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HfmRollupExceptionsPK other = (HfmRollupExceptionsPK) obj;
		return Objects.equals(accountid, other.accountid) 
				&& Objects.equals(companyid, other.companyid) && Objects.equals(costcenter, other.costcenter)
				&& Objects.equals(partnerid, other.partnerid) ;
	}
	
}
