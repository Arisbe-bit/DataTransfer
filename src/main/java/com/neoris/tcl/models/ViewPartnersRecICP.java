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
@Table(name = "ROLLUP_VPARTNERS_RECEIVABLES")
public class ViewPartnersRecICP implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1609477439372659034L;

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
    private String userid;
	@Column(name = "modified",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false)
	@Temporal(TemporalType.DATE)
	private Date modified;

    public ViewPartnersRecICP() {

    }

   

    public ViewPartnersRecICP(Long num, int organization_id, String companynm, String custno, String custname,
			String icpcode, String userid) {
		
		this.num = num;
		this.organization_id = organization_id;
		this.companynm = companynm;
		this.custno = custno;
		this.custname = custname;
		this.icpcode = icpcode;
		this.userid = userid;
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
		return "ViewPartnersRecICP [num=" + num + ", organization_id=" + organization_id + ", companynm=" + companynm
				+ ", custno=" + custno + ", custname=" + custname + ", icpcode=" + icpcode + ", userid=" + userid
				+ ", modified=" + modified + "]";
	}


}
