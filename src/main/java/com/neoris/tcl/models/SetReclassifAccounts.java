package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the set_reclassif_accounts database table.
 * 
 */
@Entity
@Table(name = "set_reclassif_accounts")
public class SetReclassifAccounts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4212642143691598086L;

	@EmbeddedId
	private SetReclassifAccountsPK id;
	private String userid;
	private String segment1;
	@Transient
	private String uuid;
	
	public SetReclassifAccounts() {
	    this.setId(new SetReclassifAccountsPK());
	    this.uuid = UUID.randomUUID().toString();
	}

	
	public SetReclassifAccounts(SetReclassifAccountsPK id, String userid, String segment1, String uuid) {
		
		this.id = id;
		this.userid = userid;
		this.segment1 = segment1;
		this.uuid = uuid;
	}


	public SetReclassifAccountsPK getId() {
		return this.id;
	}

	
	
	public void setId(SetReclassifAccountsPK id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	
	public String getSegment1() {
		return segment1;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "SetReclassifAccounts [id=" + id + ", userid=" + userid + ", segment1=" + segment1 + ", uuid=" + uuid
				+ "]";
	}

	
	@Override
	public int hashCode() {
		return Objects.hash( id, uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetReclassifAccounts other = (SetReclassifAccounts) obj;
		return Objects.equals(id, other.id) && Objects.equals(uuid, other.uuid)				;
	}
	

	

}
