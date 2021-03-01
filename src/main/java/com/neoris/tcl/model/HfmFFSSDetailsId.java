package com.neoris.tcl.model;

import java.io.Serializable;

public class HfmFFSSDetailsId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7901386456668628000L;
	
	private Long companyId;
	private String partnerId;
	private String accountId;
	private String areadId;
		
	public HfmFFSSDetailsId(Long companyId, String partnerId, String accountId, String areadId) {
		this.companyId = companyId;
		this.partnerId = partnerId;
		this.accountId = accountId;
		this.areadId = areadId;
	}


	@Override
	public String toString() {
		return "HfmFFSSDetailsId [companyId=" + companyId + ", partnerId=" + partnerId + ", accountId=" + accountId
				+ ", areadId=" + areadId + "]";
	}
		
}
