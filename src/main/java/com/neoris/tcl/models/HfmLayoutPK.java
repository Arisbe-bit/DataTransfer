package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * The primary key class for the hfm_layout database table.
 * 
 */
@Embeddable
public class HfmLayoutPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4175596679844623203L;

	private String account;

	private Long companyid;

	private String icp;

	private String period;

	private String secenary;

	private String value;

	private String cust1;

	private String cust2;

	private String cust3;

	private String cust4;

	private String zview;

	private String zyear;

	public HfmLayoutPK() {
	}

	public HfmLayoutPK(String account, Long companyid, String cust1, String cust2, String cust3, String cust4,
			String icp, String period, String secenary, String value, String zview, String zyear) {
		this.account = account;
		this.companyid = companyid;
		this.cust1 = cust1;
		this.cust2 = cust2;
		this.cust3 = cust3;
		this.cust4 = cust4;
		this.icp = icp;
		this.period = period;
		this.secenary = secenary;
		this.value = value;
		this.zview = zview;
		this.zyear = zyear;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public String getCust1() {
		return this.cust1;
	}

	public void setCust1(String cust1) {
		this.cust1 = cust1;
	}

	public String getCust2() {
		return this.cust2;
	}

	public void setCust2(String cust2) {
		this.cust2 = cust2;
	}

	public String getCust3() {
		return this.cust3;
	}

	public void setCust3(String cust3) {
		this.cust3 = cust3;
	}

	public String getCust4() {
		return this.cust4;
	}

	public void setCust4(String cust4) {
		this.cust4 = cust4;
	}

	public String getIcp() {
		return this.icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getSecenary() {
		return this.secenary;
	}

	public void setSecenary(String secenary) {
		this.secenary = secenary;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getZview() {
		return this.zview;
	}

	public void setZview(String zview) {
		this.zview = zview;
	}

	public String getZyear() {
		return this.zyear;
	}

	public void setZyear(String zyear) {
		this.zyear = zyear;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, companyid, cust1, cust2, cust3, cust4, icp, period, secenary, value, zview, zyear);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HfmLayoutPK other = (HfmLayoutPK) obj;
		return Objects.equals(account, other.account) && Objects.equals(companyid, other.companyid)
				&& Objects.equals(cust1, other.cust1) && Objects.equals(cust2, other.cust2)
				&& Objects.equals(cust3, other.cust3) && Objects.equals(cust4, other.cust4)
				&& Objects.equals(icp, other.icp) && Objects.equals(period, other.period)
				&& Objects.equals(secenary, other.secenary) && Objects.equals(value, other.value)
				&& Objects.equals(zview, other.zview) && Objects.equals(zyear, other.zyear);
	}

	@Override
	public String toString() {
		return "HfmLayoutPK [account=" + account + ", companyid=" + companyid + ", cust1=" + cust1 + ", cust2=" + cust2
				+ ", cust3=" + cust3 + ", cust4=" + cust4 + ", icp=" + icp + ", period=" + period + ", secenary="
				+ secenary + ", value=" + value + ", zview=" + zview + ", zyear=" + zyear + "]";
	}

}