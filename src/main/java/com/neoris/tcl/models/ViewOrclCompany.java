package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VW_ROLLUP_ORGS")
public class ViewOrclCompany implements Serializable{
	
	private static final long serialVersionUID = -3840011262592129052L;
	
	@Id
	private Long companyid;
	
	@Column
	private String companynm;
	@Column
	private String currencycode;
	@Column
	private String companycode;
	
	public ViewOrclCompany() {
		
	}
	public ViewOrclCompany(Long companyid, String companynm, String currencycode, String companycode) {
		
		this.companyid = companyid;
		this.companynm = companynm;
		this.currencycode = currencycode;
		this.companycode = companycode;
	}
	
	
	
	public Long getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}
	public String getCompanynm() {
		return companynm;
	}
	public void setCompanynm(String companynm) {
		this.companynm = companynm;
	}
	public String getCurrencycode() {
		return currencycode;
	}
	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	@Override
	public String toString() {
		return "ViewOrclCompany [companyid=" + companyid + ", companynm=" + companynm + ", currencycode=" + currencycode
				+ ", companycode=" + companycode + "]";
	}
	
	
	

}
