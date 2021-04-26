package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * The primary key class for the set_receivables_icp database table.
 * 
 */
@Embeddable
public class SetReceivablesIcpPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6093789913297889768L;

	private Long companyid;
	private String custno;

	public SetReceivablesIcpPK() {
	}

	public SetReceivablesIcpPK(Long companyid, String custno) {
		this.companyid = companyid;
		this.custno = custno;
	}

	public Long getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public String getCustno() {
		return this.custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(companyid, custno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetReceivablesIcpPK other = (SetReceivablesIcpPK) obj;
		return Objects.equals(companyid, other.companyid) && Objects.equals(custno, other.custno);
	}

	@Override
	public String toString() {
		return "SetReceivablesIcpPK [companyid=" + companyid + ", custno=" + custno + "]";
	}

}