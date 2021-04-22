package com.neoris.tcl.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HfmFfssDetailsHistPK implements Serializable {

	private static final long serialVersionUID = -4230285608917531891L;

	private String hfmcode;
	private String accountid;
	private String icp;
	private BigDecimal amount;
	private String period;
	private String partnerid;
	private int companyid;
	private String datasource;

	@Column(name = "CURRENCY_CODE")
	private String currencycode;

	@Column(name = "COSTCENTER")
	private String costcenter;

	@Column(name = "PERIOD_NAME")
	private String periodnm;

	public HfmFfssDetailsHistPK() {
		this.amount = new BigDecimal(0);
	}

	public HfmFfssDetailsHistPK(String hfmcode, String accountid, String icp, BigDecimal amount, String period,
			String partnerid, int companyid, String datasource, String currencycode, String costcenter,
			String periodnm) {

		this.hfmcode = hfmcode;
		this.accountid = accountid;
		this.icp = icp;
		this.amount = amount;
		this.period = period;
		this.partnerid = partnerid;
		this.companyid = companyid;
		this.datasource = datasource;
		this.currencycode = currencycode;
		this.costcenter = costcenter;
		this.periodnm = periodnm;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountid, amount, companyid, costcenter, currencycode, datasource, hfmcode, icp, partnerid,
				period, periodnm);
	}

	public String getPeriodnm() {
		return periodnm;
	}

	public void setPeriodnm(String periodnm) {
		this.periodnm = periodnm;
	}

	public String getHfmcode() {
		return hfmcode;
	}

	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getIcp() {
		return icp;
	}

	public void setIcp(String icp) {
		this.icp = icp;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
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

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
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
		HfmFfssDetailsHistPK other = (HfmFfssDetailsHistPK) obj;
		return Objects.equals(accountid, other.accountid) && Objects.equals(amount, other.amount)
				&& companyid == other.companyid && Objects.equals(costcenter, other.costcenter)
				&& Objects.equals(currencycode, other.currencycode) && Objects.equals(datasource, other.datasource)
				&& Objects.equals(hfmcode, other.hfmcode) && Objects.equals(icp, other.icp)
				&& Objects.equals(partnerid, other.partnerid) && Objects.equals(period, other.period)
				&& Objects.equals(periodnm, other.periodnm);
	}

	@Override
	public String toString() {
		return "HfmFfssDetailsHistPK [hfmcode=" + hfmcode + ", accountid=" + accountid + ", icp=" + icp + ", amount="
				+ amount + ", period=" + period + ", partnerid=" + partnerid + ", companyid=" + companyid
				+ ", datasource=" + datasource + ", currencycode=" + currencycode + ", costcenter=" + costcenter
				+ ", periodnm=" + periodnm + "]";
	}

}
