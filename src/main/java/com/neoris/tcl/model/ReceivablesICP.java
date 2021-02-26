package com.neoris.tcl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="SET_RECEIVABLES_ICP")
public class ReceivablesICP implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 1312691632195810751L;

    @Id
	@Column(name = "COMPANYID")
    private Long companyId; //from other  oracle table schema
	
	@Column(name = "ICPCODE")
    private String icpcode; //SET_ICPCODES
	
	@Column(name = "CUSTNO")
    private int custno;  // from other  oracle table schema
	
	@Column(name = "TPARTNERTYPE")
    private String tPartnerType; //from  SET_TRADING_PARTNERS_TYPES table

	
	public ReceivablesICP() {

	}

    public ReceivablesICP(Long companyid, String icpcode, int custno, String tpartnertype) {
        super();
        this.companyId = companyid;
        this.icpcode = icpcode;
        this.custno = custno;
        this.tPartnerType = tpartnertype;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getIcpcode() {
        return icpcode;
    }

    public void setIcpcode(String icpcode) {
        this.icpcode = icpcode;
    }

    public int getCustno() {
        return custno;
    }

    public void setCustno(int custno) {
        this.custno = custno;
    }

    public String gettPartnerType() {
        return tPartnerType;
    }

    public void settPartnerType(String tPartnerType) {
        this.tPartnerType = tPartnerType;
    }

    @Override
    public String toString() {
        return String.format("ReceivablesICP [companyId=%s, icpcode=%s, custno=%s, tPartnerType=%s]", companyId,
                icpcode, custno, tPartnerType);
    }

}
