package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ROLLUP_VEBS_SUPPLIERS")
public class ViewPayablesSupp implements Serializable{
 

private static final long serialVersionUID = -732130139597244868L;
	
	@Id
	private String suppliernum;
	
	private String vendorname;
	private int organizationid;
	
	
	
	public ViewPayablesSupp() {
		
	}
	
	public ViewPayablesSupp(String suppliernum, String vendorname, int organizationid) {
		
		
		this.suppliernum = suppliernum;
		this.vendorname = vendorname;
		this.organizationid = organizationid;
	}
	
	public String getSuppliernum() {
		return suppliernum;
	}
	public void setSuppliernum(String suppliernum) {
		this.suppliernum = suppliernum;
	}
	public String getVendorname() {
		return vendorname;
	}
	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
	}
	public int getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(int organizationid) {
		this.organizationid = organizationid;
	}

	@Override
	public String toString() {
		return "ViewPayablesSupp [suppliernum=" + suppliernum + ", vendorname=" + vendorname + ", organizationid="
				+ organizationid + "]";
	}

	
	
}
