package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class SetDefinedAccountsPK implements Serializable {

	private static final long serialVersionUID = 6102853690474970823L;

	private String accountid;
	private String accountidfin;
	private int companyid;
	private String costcenter;
	private String source;
	public SetDefinedAccountsPK() {

	}

	
	public SetDefinedAccountsPK(String accountid, String accountidfin, int companyid, String costcenter,
			String source) {
	
		this.accountid = accountid;
		this.accountidfin = accountidfin;
		this.companyid = companyid;
		this.costcenter = costcenter;
		this.source = source;
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

	public String getCostcenter() {
		return costcenter;
	}

	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	


	public String getAccountidfin() {
		return accountidfin;
	}


	public void setAccountidfin(String accountidfin) {
		this.accountidfin = accountidfin;
	}


	@Override
	public String toString() {
		return "SetDefinedAccountsPK [accountid=" + accountid + ", accountidfin=" + accountidfin + ", companyid="
				+ companyid + ", costcenter=" + costcenter + ", source=" + source + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(companyid, accountid, costcenter, source);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetDefinedAccountsPK other = (SetDefinedAccountsPK) obj;
		return Objects.equals(accountid, other.accountid) && Objects.equals(companyid, other.companyid)
				&& Objects.equals(costcenter, other.costcenter) && Objects.equals(source, other.source);
	}

}
