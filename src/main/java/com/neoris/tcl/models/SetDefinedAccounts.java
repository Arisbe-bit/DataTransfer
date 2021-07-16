package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "set_defined_accounts")
public class SetDefinedAccounts implements Serializable {
	private static final long serialVersionUID = 6102853690474970823L;

	@EmbeddedId
	private SetDefinedAccountsPK id;

	private String icpcode;
	private String userid;
	private String cperiod;

	@Transient
	private String uuid;

	public SetDefinedAccounts() {
		this.setId(new SetDefinedAccountsPK());
		this.uuid = UUID.randomUUID().toString();
	}

	public SetDefinedAccounts(SetDefinedAccountsPK id, String icpcode, String userid, String cperiod) {
		this.id = id;
		this.icpcode = icpcode;
		this.userid = userid;
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

	public String getCperiod() {
		return cperiod;
	}

	public void setCperiod(String cperiod) {
		this.cperiod = cperiod;
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public String toString() {
		return String.format("SetDefinedAccounts [id=%s, icpcode=%s, userid=%s, cperiod=%s, uuid=%s]", id, icpcode,
				userid, cperiod, uuid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cperiod, icpcode, id, userid, uuid);
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
				&& Objects.equals(id, other.id) && Objects.equals(userid, other.userid)
				&& Objects.equals(uuid, other.uuid);
	}

}
