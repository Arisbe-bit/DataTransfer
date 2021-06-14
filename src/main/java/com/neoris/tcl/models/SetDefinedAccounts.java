package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "set_defined_accounts")
public class SetDefinedAccounts implements Serializable {
	private static final long serialVersionUID = 6102853690474970823L;

	@EmbeddedId
	private SetDefinedAccountsPK id;

	private String icpcode;
	private String userid;
	private String modified;
	private String cperiod;

	public SetDefinedAccounts() {
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

	public String getUUID() {
		return UUID.randomUUID().toString();
	}

	@Override
	public String toString() {
		return "SetDefinedAccounts [id=" + id + ", icpcode=" + icpcode + ", userid=" + userid + ", modified=" + modified
				+ ", cperiod=" + cperiod + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cperiod, icpcode, id, modified, userid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetDefinedAccounts other = (SetDefinedAccounts) obj;
		return Objects.equals(cperiod, other.cperiod) && Objects.equals(icpcode, other.icpcode)
				&& Objects.equals(id, other.id) && Objects.equals(modified, other.modified)
				&& Objects.equals(userid, other.userid);
	}

}
