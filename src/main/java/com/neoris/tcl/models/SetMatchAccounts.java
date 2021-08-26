package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the set_match_accounts database table.
 * 
 */
@Entity
@Table(name = "set_match_accounts")
public class SetMatchAccounts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2750715160416744318L;

	@EmbeddedId
	private SetMatchAccountsPK id;

	private String sign;
	private String userid;
	@Column(name = "modified",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false)
	@Temporal(TemporalType.DATE)
	private Date modified;
	
	@Transient
	private String uuid;

	public SetMatchAccounts() {
	    this.setId(new SetMatchAccountsPK());
	    this.uuid = UUID.randomUUID().toString();
	}

	

	public SetMatchAccounts(SetMatchAccountsPK id, String sign, String userid) {
		
		this.id = id;
		this.sign = sign;
		this.userid = userid;
	}



	public SetMatchAccountsPK getId() {
		return this.id;
	}

	public void setId(SetMatchAccountsPK id) {
		this.id = id;
	}

	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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



	public String getUuid() {
		return uuid;
	}




	@Override
	public int hashCode() {
		return Objects.hash(id, uuid);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetMatchAccounts other = (SetMatchAccounts) obj;
		return Objects.equals(id, other.id) && Objects.equals(uuid, other.uuid);
	}



	@Override
	public String toString() {
		return "SetMatchAccounts [id=" + id + ", sign=" + sign + ", userid=" + userid + ", modified=" + modified
				+ ", uuid=" + uuid + "]";
	}




}