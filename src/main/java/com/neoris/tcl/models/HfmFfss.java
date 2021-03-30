package com.neoris.tcl.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * The persistent class for the hfm_ffss database table.
 * 
 */
@Entity
@Table(name = "hfm_ffss")
public class HfmFfss implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5362381129863237217L;

	@EmbeddedId
	private HfmFfssPK id;

	private BigDecimal balance;

	@Column(name = "hfmcode_old")
	private String hfmcodeOld;

	@Column(name = "UPD")
	private Timestamp updated;

	private String userid;

	public HfmFfss() {
	    this.setId(new HfmFfssPK());
	    this.balance = new BigDecimal(0);
	}

	public HfmFfssPK getId() {
		return this.id;
	}

	public void setId(HfmFfssPK id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getHfmcodeOld() {
		return this.hfmcodeOld;
	}

	public void setHfmcodeOld(String hfmcodeOld) {
		this.hfmcodeOld = hfmcodeOld;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getFormatedBalance() {
	    String retval;
	    // NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
	    NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
	    retval = nf.format(this.balance); // + " " + this.getId().getCurrencyCode();
	    return retval;
	}

	@Override
	public String toString() {
		return "HfmFfss [id=" + id + ", balance=" + balance + ", hfmcodeOld=" + hfmcodeOld + ", updated=" + updated
				+ ", userid=" + userid + "]";
	}

}