package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the set_hfm_codes database table.
 * 
 */
@Entity
@Table(name = "set_hfm_codes")
public class SetHfmCodes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -732130139597244868L;

	@Id
	private String hfmcode;

	private String tptype;
	
	private String classification;
	
	private String naturalb;
	
	private String global_val;
	
	private String ordernum;

	public SetHfmCodes() {
	}


	public SetHfmCodes(String hfmcode, String tptype, String classification, String naturalb, String global_val,
			String ordernum) {

		this.hfmcode = hfmcode;
		this.tptype = tptype;
		this.classification = classification;
		this.naturalb = naturalb;
		this.global_val = global_val;
		this.ordernum = ordernum;
	}

	public String getHfmcode() {
		return this.hfmcode;
	}

	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}

	public String getTptype() {
		return this.tptype;
	}

	public void setTptype(String tptype) {
		this.tptype = tptype;
	}


	public String getClassification() {
		return classification;
	}


	public void setClassification(String classification) {
		this.classification = classification;
	}


	public String getNaturalb() {
		return naturalb;
	}


	public void setNaturalb(String naturalb) {
		this.naturalb = naturalb;
	}


	public String getGlobal_val() {
		return global_val;
	}


	public void setGlobal_val(String global_val) {
		this.global_val = global_val;
	}


	public String getOrdernum() {
		return ordernum;
	}


	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}


	@Override
	public String toString() {
		return "SetHfmCodes [hfmcode=" + hfmcode + ", tptype=" + tptype + ", classification=" + classification
				+ ", naturalb=" + naturalb + ", global_val=" + global_val + ", ordernum=" + ordernum + "]";
	}

	
}

