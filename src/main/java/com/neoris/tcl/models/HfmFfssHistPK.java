package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class HfmFfssHistPK implements Serializable {
	

	private static final long serialVersionUID = 1554517960434348057L;

	@Column(name = "COMPANYID")
	private Long companyId;

	@Column(name = "CURRENCY_CODE", columnDefinition = "VARCHAR(3) default 'USD'")
	private String currencyCode;

	@Column(name = "HFMCODE")
	private String hfmcode;

	@Column(name = "PERIOD")
	private String period;

	public HfmFfssHistPK() {
	}

	public HfmFfssHistPK(Long companyId, String currencyCode, String hfmcode, String period) {
		this.companyId = companyId;
		this.currencyCode = currencyCode;
		this.hfmcode = hfmcode;
		this.period = period;
	}

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getHfmcode() {
		return this.hfmcode;
	}

	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}

	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	@Override
	public int hashCode() {
		return Objects.hash(companyId, currencyCode, hfmcode, period);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HfmFfssHistPK other = (HfmFfssHistPK) obj;
		return Objects.equals(companyId, other.companyId) && Objects.equals(currencyCode, other.currencyCode)
				&& Objects.equals(hfmcode, other.hfmcode) && Objects.equals(period, other.period);
	}

	@Override
	public String toString() {
		return "HfmFfssPK [companyId=" + companyId + ", currencyCode=" + currencyCode + ", hfmcode=" + hfmcode
				+ ", period=" + period + "]";
	}


}
