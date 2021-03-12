package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the hfm_ffss_details database table.
 * 
 */
@Entity
@Table(name="hfm_ffss_details")
public class HfmFfssDetails implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5676213260556508605L;

	@EmbeddedId
	private HfmFfssDetailsPK id;

	@Column(name="batch_name")
	private String batchName;

	private String docnumber;

	private String tpname;

	private String userid;

	public HfmFfssDetails() {
	    this.setId(new HfmFfssDetailsPK());
	}

	public HfmFfssDetails(HfmFfssDetailsPK id, String batchName, String docnumber, String tpname, String userid) {
		this.id = id;
		this.batchName = batchName;
		this.docnumber = docnumber;
		this.tpname = tpname;
		this.userid = userid;
	}

	public HfmFfssDetailsPK getId() {
		return this.id;
	}

	public void setId(HfmFfssDetailsPK id) {
		this.id = id;
	}

	public String getBatchName() {
		return this.batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getDocnumber() {
		return this.docnumber;
	}

	public void setDocnumber(String docnumber) {
		this.docnumber = docnumber;
	}

	public String getTpname() {
		return this.tpname;
	}

	public void setTpname(String tpname) {
		this.tpname = tpname;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "HfmFfssDetails [id=" + id + ", batchName=" + batchName + ", docnumber=" + docnumber + ", tpname="
				+ tpname + ", userid=" + userid + "]";
	}

}