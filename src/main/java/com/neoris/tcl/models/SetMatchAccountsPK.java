package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * The primary key class for the set_match_accounts database table.
 * 
 */
@Embeddable
public class SetMatchAccountsPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5157908045428807574L;

	private String hmfchild;

	private String hmfparent;

	public SetMatchAccountsPK() {
	}

	public SetMatchAccountsPK(String hmfchild, String hmfparent) {
		this.hmfchild = hmfchild;
		this.hmfparent = hmfparent;
	}

	public String getHmfchild() {
		return this.hmfchild;
	}

	public void setHmfchild(String hmfchild) {
		this.hmfchild = hmfchild;
	}

	public String getHmfparent() {
		return this.hmfparent;
	}

	public void setHmfparent(String hmfparent) {
		this.hmfparent = hmfparent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hmfchild, hmfparent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetMatchAccountsPK other = (SetMatchAccountsPK) obj;
		return Objects.equals(hmfchild, other.hmfchild) && Objects.equals(hmfparent, other.hmfparent);
	}

	@Override
	public String toString() {
		return "SetMatchAccountsPK [hmfchild=" + hmfchild + ", hmfparent=" + hmfparent + "]";
	}

}