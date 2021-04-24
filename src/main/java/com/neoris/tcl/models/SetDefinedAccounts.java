package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="set_defined_accounts")
public class SetDefinedAccounts implements Serializable{
	private static final long serialVersionUID = 6102853690474970823L;
	
	@EmbeddedId
	private SetDefinedAccountsPK id;
	
	private String icpcode;
	private String userid;
	private String modified;
	private String cperiod;
	
	
	public SetDefinedAccounts()
			{
		 		this.setId(new SetDefinedAccountsPK());
			}


	public SetDefinedAccounts(SetDefinedAccountsPK id, String icpcode, String userid, String modified, String cperiod) {
		
		this.id = id;
		this.icpcode = icpcode;
		this.userid = userid;
		this.modified = modified;
		this.cperiod = cperiod;
	}


	public SetDefinedAccountsPK getId() {
		return id;
	}


	public void setId(SetDefinedAccountsPK id) {
		this.id = id;
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


	public String getModified() {
		return modified;
	}


	public void setModified(String modified) {
		this.modified = modified;
	}


	public String getCperiod() {
		return cperiod;
	}


	public void setCperiod(String cperiod) {
		this.cperiod = cperiod;
	}


	@Override
	public String toString() {
		return "SetDefinedAccounts [id=" + id + ", icpcode=" + icpcode + ", userid=" + userid + ", modified=" + modified
				+ ", cperiod=" + cperiod + "]";
	}
	

}
