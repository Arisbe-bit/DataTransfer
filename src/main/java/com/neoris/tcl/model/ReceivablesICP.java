package com.neoris.tcl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "SET_RECEIVABLES_ICP")
@IdClass(ReceivablesICPId.class)
public class ReceivablesICP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1312691632195810751L;

	@Id
	@Column(name = "COMPANYID", nullable = false)
	private Long companyId; // from other oracle table schema

	@Id
	@Column(name = "ICPCODE", nullable = false)
	private String icpcode; // SET_ICPCODES

	@Id
	@Column(name = "CUSTNO", nullable = false)
	private Long custno; // from other oracle table schema

	@Id
	@Column(name = "TPARTNERTYPE", nullable = false)
	private String tPartnerType; // from SET_TRADING_PARTNERS_TYPES table

	@Column(name = "HFMCODE")
	private String hfmcode;

	public ReceivablesICP() {

	}

	public ReceivablesICP(Long companyId, String icpcode, Long custno, String tPartnerType, String hfmcode) {
		this.companyId = companyId;
		this.icpcode = icpcode;
		this.custno = custno;
		this.tPartnerType = tPartnerType;
		this.hfmcode = hfmcode;
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

	public Long getCustno() {
		return custno;
	}

	public void setCustno(Long custno) {
		this.custno = custno;
	}

	public String gettPartnerType() {
		return tPartnerType;
	}

	public void settPartnerType(String tPartnerType) {
		this.tPartnerType = tPartnerType;
	}

	public String getHfmcode() {
		return hfmcode;
	}

	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}

	@Override
	public String toString() {
		return "ReceivablesICP [companyId=" + companyId + ", icpcode=" + icpcode + ", custno=" + custno
				+ ", tPartnerType=" + tPartnerType + ", hfmcode=" + hfmcode + "]";
	}

}
