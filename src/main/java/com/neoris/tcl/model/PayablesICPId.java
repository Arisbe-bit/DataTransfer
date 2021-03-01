package com.neoris.tcl.model;

import java.io.Serializable;

public class PayablesICPId  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8203144612573919670L;
	private String icpCode;
	private String supplierno;
	private String tPartnerType;
	private Long companyId;
	
	public PayablesICPId(String icpCode, String supplierno, String tPartnerType, Long companyId) {
		this.icpCode = icpCode;
		this.supplierno = supplierno;
		this.tPartnerType = tPartnerType;
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "PayablesICPId [icpCode=" + icpCode + ", supplierno=" + supplierno + ", tPartnerType=" + tPartnerType
				+ ", companyId=" + companyId + "]";
	}
	

	
}
