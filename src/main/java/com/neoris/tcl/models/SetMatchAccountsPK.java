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

	private String hfmchild;

	private String hfmparent;

	public SetMatchAccountsPK() {
	}

	public SetMatchAccountsPK(String hfmchild, String hfmparent) {
		this.hfmchild = hfmchild;
		this.hfmparent = hfmparent;
	}

	public String gethfmchild() {
		return this.hfmchild;
	}

	public void sethfmchild(String hfmchild) {
		this.hfmchild = hfmchild;
	}

	public String gethfmparent() {
		return this.hfmparent;
	}

	public void sethfmparent(String hfmparent) {
		this.hfmparent = hfmparent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hfmchild, hfmparent);
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
		return Objects.equals(hfmchild, other.hfmchild) && Objects.equals(hfmparent, other.hfmparent);
	}

	@Override
	public String toString() {
		return "SetMatchAccountsPK [hfmchild=" + hfmchild + ", hfmparent=" + hfmparent + "]";
	}

}