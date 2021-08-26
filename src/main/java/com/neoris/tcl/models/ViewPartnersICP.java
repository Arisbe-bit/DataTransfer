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
@Table(name = "ROLLUP_VPARTNERS_PAYABLES")
public class ViewPartnersICP implements Serializable {

	private static final long serialVersionUID = -732130139597244868L;
	
	@Id
	private Long num;
	
	@Column
	private int organizationid;
	@Column
	private String companynm;
	@Column
	private String suppliernum;
	@Column
	private String vendorname;
	@Column
	private String icpcode;
	
	private String userid;
	@Column(name = "modified",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false)
	@Temporal(TemporalType.DATE)
	private Date modified;
	
	public String getIcpcode() {
		return icpcode;
	}

    public void setIcpcode(String icpcode) {
        this.icpcode = icpcode;
    }


	public ViewPartnersICP()
	{
	
	}
	
	
	

    public ViewPartnersICP(Long num, int organizationid, String companynm, String suppliernum, String vendorname,
			String icpcode, String userid) {
		
		this.num = num;
		this.organizationid = organizationid;
		this.companynm = companynm;
		this.suppliernum = suppliernum;
		this.vendorname = vendorname;
		this.icpcode = icpcode;
		this.userid = userid;
	}

	public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }


	public int getorganizationid() {
		return organizationid;
	}


	public void setorganizationid(int organizationid) {
		this.organizationid = organizationid;
	}


	public String getCompanynm() {
		return companynm;
	}


	public void setCompanynm(String companynm) {
		this.companynm = companynm;
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


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "ViewPartnersICP [num=" + num + ", organizationid=" + organizationid + ", companynm=" + companynm
				+ ", suppliernum=" + suppliernum + ", vendorname=" + vendorname + ", icpcode=" + icpcode + ", userid="
				+ userid + ", modified=" + modified + "]";
	}

	
}
