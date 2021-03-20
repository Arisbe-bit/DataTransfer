package com.neoris.tcl.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ROLLUP_VCUST_RECEIVABLES")
public class ViewCustReceivables implements Serializable{
	
private static final long serialVersionUID = -732130139597244868L;
	
	@Id
	private String custno;
	
	private String custname;
	private int organizationid;
	
	public ViewCustReceivables()
	{
		
	}

	public ViewCustReceivables(String custno, String custname, int organizationid) {
		
		this.custno = custno;
		this.custname = custname;
		this.organizationid = organizationid;
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

	public int getorganizationid() {
		return organizationid;
	}

	public void setorganizationid(int organizationid) {
		this.organizationid = organizationid;
	}

	@Override
	public String toString() {
		return "ViewCustReceivables [custno=" + custno + ", custname=" + custname + ", organizationid="
				+ organizationid + "]";
	}
	

}
