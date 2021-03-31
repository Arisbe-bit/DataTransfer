package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the hfm_ffss_details database table.
 * 
 */
@Embeddable
public class HfmFfssDetailsPK implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -4230285608917531891L;

    private String accountid;

    private String amount;

    private String areadid;

    private Long companyid;

    private String costcenter;

    @Column(name = "CURRENCY_CODE", columnDefinition = "VARCHAR(3) default 'USD'")
    private String currencyCode;

    private String datasource;

    private String hfmcode;

    @Column(name = "hfmcode_old")
    private String hfmcodeOld;

    private String icp;

    private String partnerid;

    private String period;

    public HfmFfssDetailsPK() {
    }

    public HfmFfssDetailsPK(String accountid, String amount, String areadid, Long companyid, String costcenter,
            String currencyCode, String datasource, String hfmcode, String hfmcodeOld, String icp, String partnerid,
            String period) {
        super();
        this.accountid = accountid;
        this.amount = amount;
        this.areadid = areadid;
        this.companyid = companyid;
        this.costcenter = costcenter;
        this.currencyCode = currencyCode;
        this.datasource = datasource;
        this.hfmcode = hfmcode;
        this.hfmcodeOld = hfmcodeOld;
        this.icp = icp;
        this.partnerid = partnerid;
        this.period = period;
    }

    public String getAccountid() {
        return this.accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAreadid() {
        return this.areadid;
    }

    public void setAreadid(String areadid) {
        this.areadid = areadid;
    }

    public Long getCompanyid() {
        return this.companyid;
    }

    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }

    public String getCostcenter() {
        return this.costcenter;
    }

    public void setCostcenter(String costcenter) {
        this.costcenter = costcenter;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDatasource() {
        return this.datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getHfmcode() {
        return this.hfmcode;
    }

    public void setHfmcode(String hfmcode) {
        this.hfmcode = hfmcode;
    }

    public String getHfmcodeOld() {
        return this.hfmcodeOld;
    }

    public void setHfmcodeOld(String hfmcodeOld) {
        this.hfmcodeOld = hfmcodeOld;
    }

    public String getIcp() {
        return this.icp;
    }

    public void setIcp(String icp) {
        this.icp = icp;
    }

    public String getPartnerid() {
        return this.partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPeriod() {
        return this.period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountid, amount, areadid, companyid, costcenter, currencyCode, datasource, hfmcode,
                hfmcodeOld, icp, partnerid, period);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HfmFfssDetailsPK other = (HfmFfssDetailsPK) obj;
        return Objects.equals(accountid, other.accountid) && Objects.equals(amount, other.amount)
                && Objects.equals(areadid, other.areadid) && Objects.equals(companyid, other.companyid)
                && Objects.equals(costcenter, other.costcenter) && Objects.equals(currencyCode, other.currencyCode)
                && Objects.equals(datasource, other.datasource) && Objects.equals(hfmcode, other.hfmcode)
                && Objects.equals(hfmcodeOld, other.hfmcodeOld) && Objects.equals(icp, other.icp)
                && Objects.equals(partnerid, other.partnerid) && Objects.equals(period, other.period);
    }

    @Override
    public String toString() {
        return "HfmFfssDetailsPK [accountid=" + accountid + ", amount=" + amount + ", areadid=" + areadid
                + ", companyid=" + companyid + ", costcenter=" + costcenter + ", currencyCode=" + currencyCode
                + ", datasource=" + datasource + ", hfmcode=" + hfmcode + ", hfmcodeOld=" + hfmcodeOld + ", icp=" + icp
                + ", partnerid=" + partnerid + ", period=" + period + "]";
    }

}