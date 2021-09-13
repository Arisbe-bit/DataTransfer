package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the set_icpcodes database table.
 * 
 */
@Entity
@Table(name = "set_icpcodes")
public class SetIcpcodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1388880172612949672L;

	// @EmbeddedId
	// private SetIcpcodesPK id;
	@Id
	private String icpcode;
	private String icpdesc;
	private String icpid;
	private String tptype;
	private String userid;
	
	@Column(name = "modified",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false)
	@Temporal(TemporalType.DATE)
	private Date modified;

	public SetIcpcodes() {

	}

	public SetIcpcodes(String icpcode, String icpdesc, String icpid, String tptype, String userid) {

		this.icpcode = icpcode;
		this.icpdesc = icpdesc;
		this.icpid = icpid;
		this.tptype = tptype;
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIcpdesc() {
		return this.icpdesc;
	}

	public void setIcpdesc(String icpdesc) {
		this.icpdesc = icpdesc;
	}

	public String getTptype() {
		return tptype;
	}

	public void setTptype(String tptype) {
		this.tptype = tptype;
	}

	public String getIcpid() {
		return this.icpid;
	}

	public void setIcpid(String icpid) {
		this.icpid = icpid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(icpcode);
	}

	public String getIcpcode() {
		return icpcode;
	}

	public void setIcpcode(String icpcode) {
		this.icpcode = icpcode;
	}

	public Date getModified() {

		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "SetIcpcodes [icpcode=" + icpcode + ", icpdesc=" + icpdesc + ", icpid=" + icpid + ", tptype=" + tptype
				+ ", userid=" + userid + ", modified=" + modified + "]";
	}
	

}