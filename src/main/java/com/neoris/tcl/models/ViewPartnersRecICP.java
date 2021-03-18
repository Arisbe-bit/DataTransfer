package com.neoris.tcl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLLUP_VPARTNERS_RECEIVABLES")
public class ViewPartnersRecICP {
	
private static final long serialVersionUID = -732130139597244868L;
	
	@Id
	private Long num;
	
	@Column
	private int organization_id;
	@Column
	private String companynm;
	@Column
	private String custno;
	@Column
	private String custname;
	@Column
	private String icpcode;
	
	
	public ViewPartnersRecICP()
	{
	
	}
	
	public ViewPartnersRecICP(Long num,int organization_id, String companynm, String custno, String custname, String icpcode) {
		
		this.organization_id = organization_id;
		this.companynm = companynm;
		this.custno = custno;
		this.custname = custname;
		this.icpcode = icpcode;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public int getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(int organization_id) {
		this.organization_id = organization_id;
	}

	public String getCompanynm() {
		return companynm;
	}

	public void setCompanynm(String companynm) {
		this.companynm = companynm;
	}

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getIcpcode() {
		return icpcode;
	}

	public void setIcpcode(String icpcode) {
		this.icpcode = icpcode;
	}
	
	
	
}
