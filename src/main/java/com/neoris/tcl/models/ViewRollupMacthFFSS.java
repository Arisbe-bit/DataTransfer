package com.neoris.tcl.models;

import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLLUP_VIEW_MATCH_FFSS")
public class ViewRollupMacthFFSS {

    @Id
    private Long companyid;

    private String hfmcode;

    private int balance;

    private String classification;

    private String naturalb;

    private String period;

    private int error_text;

    private String description;

    public ViewRollupMacthFFSS() {

    }

    public ViewRollupMacthFFSS(Long companyid, String hfmcode, int balance, String classification, String naturalb,
            String period, int error_text, String description) {
        this.companyid = companyid;
        this.hfmcode = hfmcode;
        this.balance = balance;
        this.classification = classification;
        this.naturalb = naturalb;
        this.period = period;
        this.error_text = error_text;
        this.description = description;
    }

    public Long getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }

    public String getHfmcode() {
        return hfmcode;
    }

    public void setHfmcode(String hfmcode) {
        this.hfmcode = hfmcode;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getNaturalb() {
        return naturalb;
    }

    public void setNaturalb(String naturalb) {
        this.naturalb = naturalb;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getError_text() {
        return error_text;
    }

    public void setError_text(int error_text) {
        this.error_text = error_text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getFormatedamount() {
        String retval;
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        retval = nf.format(this.balance);
        return retval;
    }

    @Override
    public String toString() {
        return "ViewRollupMacthFFSS [companyid=" + companyid + ", hfmcode=" + hfmcode + ", balance=" + balance
                + ", classification=" + classification + ", naturalb=" + naturalb + ", period=" + period
                + ", error_text=" + error_text + ", description=" + description + "]";
    }

}
