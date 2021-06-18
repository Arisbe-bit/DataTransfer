package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
	
	public SetReclassifAccounts() {
	    this.setId(new SetReclassifAccountsPK());
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

	@Override
	public String toString() {
		return "SetReclassifAccounts [id=" + id + ", userid=" + userid + ", segment1=" + segment1 + "]";
	}

	

	

	

}
