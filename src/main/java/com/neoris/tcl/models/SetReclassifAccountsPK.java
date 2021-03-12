package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * The primary key class for the set_reclassif_accounts database table.
 * 
 */
@Embeddable
public class SetReclassifAccountsPK implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7650922966653268829L;

	private String destaccount;

	private String originaccount;

	public SetReclassifAccountsPK() {
	}

	public SetReclassifAccountsPK(String destaccount, String originaccount) {
		this.destaccount = destaccount;
		this.originaccount = originaccount;
	}

	public String getDestaccount() {
		return this.destaccount;
	}

	public void setDestaccount(String destaccount) {
		this.destaccount = destaccount;
	}

	public String getOriginaccount() {
		return this.originaccount;
	}

	public void setOriginaccount(String originaccount) {
		this.originaccount = originaccount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(destaccount, originaccount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetReclassifAccountsPK other = (SetReclassifAccountsPK) obj;
		return Objects.equals(destaccount, other.destaccount) && Objects.equals(originaccount, other.originaccount);
	}

	@Override
	public String toString() {
		return "SetReclassifAccountsPK [destaccount=" + destaccount + ", originaccount=" + originaccount + "]";
	}

}