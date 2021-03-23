package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ROLLUP_VCUST_RECEIVABLES")
public class ViewCustReceivables implements Serializable{
	
private static final long serialVersionUID = -732130139597244868L;
	
	@Id
	private String custno;
	@Column(name = "CUSTNAME")
	private String custname;
	@Column(name = "ORGANIZATION_ID")
	private int organization_id;
	
	public ViewCustReceivables()
	{
		
	}

	public ViewCustReceivables(String custno, String custname, int organization_id) {
		
		this.custno = custno;
		this.custname = custname;
		this.organization_id = organization_id;
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

	public int getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(int organization_id) {
		this.organization_id = organization_id;
	}

	@Override
	public String toString() {
		return "ViewCustReceivables [custno=" + custno + ", custname=" + custname + ", organization_id="
				+ organization_id + "]";
	}
	

}
