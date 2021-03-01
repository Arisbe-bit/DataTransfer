package com.neoris.tcl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name ="SET_PAYABLES_ICP",
		   indexes = {
				   @Index(name = "ROLL_SET_PAY_ICP1", columnList = "SUPPLIERNO"),
				   @Index(name = "ROLL_SET_PAY_ICP2", columnList = "COMPANYID")
		   }
		)
@IdClass(PayablesICPId.class)
public class PayablesICP implements Serializable{
	
	/**
     * 
     */
    private static final long serialVersionUID = -5696847616125787970L;


    @Id
    @Column(name = "ICPCODE", nullable = false)
    private String icpCode; //SET_ICPCODES
    
    @Id
    @Column(name = "SUPPLIERNO", nullable = false)
    private int supplierno;  // from other  oracle table schema
    
    @Id
    @Column(name = "TPARTNERTYPE", nullable = false)
    private String tPartnerType; //from  SET_TRADING_PARTNERS_TYPES table

    @Id
    @Column(name = "COMPANYID", nullable = false)
    private Long companyId; //from other  oracle table schema

    @Column(name = "HFMCODE")
    private String hfmcode;

	public PayablesICP() {
	    
	}

    public PayablesICP(String icpCode, Long companyId, int supplierno, String tPartnerType, String hfmcode) {
		this.icpCode = icpCode;
		this.companyId = companyId;
		this.supplierno = supplierno;
		this.tPartnerType = tPartnerType;
		this.hfmcode = hfmcode;
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
		return "PayablesICP [icpCode=" + icpCode + ", companyId=" + companyId + ", supplierno=" + supplierno
				+ ", tPartnerType=" + tPartnerType + ", hfmcode=" + hfmcode + "]";
	}
    
}
