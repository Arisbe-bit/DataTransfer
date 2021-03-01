package com.neoris.tcl.model;

import java.io.Serializable;

public class ReceivablesICPId implements Serializable {

	private static final long serialVersionUID = 8434071754509575773L;

	private Long companyId; // from other oracle table schema
	private String icpcode; // SET_ICPCODES
	private Long custno; // from other oracle table schema
	private String tPartnerType; // from SET_TRADING_PARTNERS_TYPES table

	public ReceivablesICPId(Long companyId, String icpcode, Long custno, String tPartnerType) {
		this.companyId = companyId;
		this.icpcode = icpcode;
		this.custno = custno;
		this.tPartnerType = tPartnerType;
	}

	@Override
	public String toString() {
		return "ReceivablesICPId [companyId=" + companyId + ", icpcode=" + icpcode + ", custno=" + custno
				+ ", tPartnerType=" + tPartnerType + "]";
	}
}
