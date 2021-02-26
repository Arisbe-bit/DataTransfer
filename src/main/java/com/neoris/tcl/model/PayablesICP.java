package com.neoris.tcl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="SET_PAYABLES_ICP")
public class PayablesICP implements Serializable{
	
	/**
     * 
     */
    private static final long serialVersionUID = -5696847616125787970L;

    @Id
    @Column(name = "COMPANYID")
    private Long companyId; //from other  oracle table schema
	
    @Column(name = "ICPCODE")
    private String icpCode; //SET_ICPCODES
    
    @Column(name = "SUPPLIERNO")
    private int supplierno;  // from other  oracle table schema
    
    @Column(name = "TPARTNERTYPE")
    private String tPartnerType; //from  SET_TRADING_PARTNERS_TYPES table

	public PayablesICP() {
	    
	}

    public PayablesICP(Long companyId, String icpCode, int supplierno, String tPartnerType) {
        this.companyId = companyId;
        this.icpCode = icpCode;
        this.supplierno = supplierno;
        this.tPartnerType = tPartnerType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getIcpCode() {
        return icpCode;
    }

    public void setIcpCode(String icpCode) {
        this.icpCode = icpCode;
    }

    public int getSupplierno() {
        return supplierno;
    }

    public void setSupplierno(int supplierno) {
        this.supplierno = supplierno;
    }

    public String gettPartnerType() {
        return tPartnerType;
    }

    public void settPartnerType(String tPartnerType) {
        this.tPartnerType = tPartnerType;
    }

    @Override
    public String toString() {
        return String.format("PayablesICP [companyId=%s, icpCode=%s, supplierno=%s, tPartnerType=%s]", companyId,
                icpCode, supplierno, tPartnerType);
    }
    
}
