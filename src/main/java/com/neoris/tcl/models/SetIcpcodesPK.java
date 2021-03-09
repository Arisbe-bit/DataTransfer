package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * The primary key class for the set_icpcodes database table.
 * 
 */
@Embeddable
public class SetIcpcodesPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2195369749848443020L;

	private String icpcode;

	private String icpid;

	public SetIcpcodesPK() {
	}

	public SetIcpcodesPK(String icpcode, String icpid) {
		this.icpcode = icpcode;
		this.icpid = icpid;
	}

	public String getIcpcode() {
		return this.icpcode;
	}

	public void setIcpcode(String icpcode) {
		this.icpcode = icpcode;
	}

	public String getIcpid() {
		return this.icpid;
	}

	public void setIcpid(String icpid) {
		this.icpid = icpid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(icpcode, icpid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetIcpcodesPK other = (SetIcpcodesPK) obj;
		return Objects.equals(icpcode, other.icpcode) && Objects.equals(icpid, other.icpid);
	}

	@Override
	public String toString() {
		return "SetIcpcodesPK [icpcode=" + icpcode + ", icpid=" + icpid + "]";
	}

}