package com.neoris.tcl.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
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

    private String hfmcode;
	private String accountid;
	private String icp;
	private BigDecimal amount;
	private String period;
	private String partnerid;
	private int companyid;
	private String datasource;
	
	
	@Column(name="CURRENCY_CODE")
	private String currencycode;
	
	@Column(name="COSTCENTER")
	private String costcenter;
	
	@Column(name="PERIOD_NAME")
	private String periodnm;

    public HfmFfssDetailsPK() {
    	
    }

    

    public HfmFfssDetailsPK(String hfmcode, String accountid, String icp, BigDecimal amount, String period, String partnerid,
			int companyid, String datasource, String currencycode, String costcenter, String periodnm) {
		
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



	public String getCurrencycode() {
		return currencycode;
	}



	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}



	public String getPeriodnm() {
		return periodnm;
	}



	public void setPeriodnm(String periodnm) {
		this.periodnm = periodnm;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	


	public String getAccountid() {
        return this.accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    

    public int getCompanyid() {
        return this.companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public String getCostcenter() {
        return this.costcenter;
    }

    public void setCostcenter(String costcenter) {
        this.costcenter = costcenter;
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
        return Objects.hash(accountid, amount,  companyid, costcenter, currencycode, datasource, hfmcode,
                 icp, partnerid, period);
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
                && Objects.equals(companyid, other.companyid)
                && Objects.equals(costcenter, other.costcenter) && Objects.equals(currencycode, other.currencycode)
                && Objects.equals(datasource, other.datasource) && Objects.equals(hfmcode, other.hfmcode)
                 && Objects.equals(icp, other.icp)
                && Objects.equals(partnerid, other.partnerid) && Objects.equals(period, other.period);
    }



	@Override
	public String toString() {
		return "HfmFfssDetailsPK [hfmcode=" + hfmcode + ", accountid=" + accountid + ", icp=" + icp + ", amount="
				+ amount + ", period=" + period + ", partnerid=" + partnerid + ", companyid=" + companyid
				+ ", datasource=" + datasource + ", currencycode=" + currencycode + ", costcenter=" + costcenter
				+ ", periodnm=" + periodnm + "]";
	}

    

}