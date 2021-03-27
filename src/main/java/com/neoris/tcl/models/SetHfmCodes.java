package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Column;
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
	
	@Column(name="global_val")
	private String globalval;
	
	private int ordernum;
	
	private int naturalsign;

	private String description;

	public SetHfmCodes() {
	}


	
	public SetHfmCodes(String hfmcode, String tptype, String classification, String naturalb, String globalval,
			int ordernum, int naturalsign, String description) {
		this.hfmcode = hfmcode;
		this.tptype = tptype;
		this.classification = classification;
		this.naturalb = naturalb;
		this.globalval = globalval;
		this.ordernum = ordernum;
		this.naturalsign = naturalsign;
		this.description = description;
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

	public int getNaturalsign() {
		return naturalsign;
	}


	public void setNaturalsign(int naturalsign) {
		this.naturalsign = naturalsign;
	}

	public String getNaturalb() {
		return naturalb;
	}


	public void setNaturalb(String naturalb) {
		this.naturalb = naturalb;
	}


	public String getglobalval() {
		return globalval;
	}


	public void setglobalval(String globalval) {
		this.globalval = globalval;
	}


	public int getOrdernum() {
		return ordernum;
	}


	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	@Override
	public String toString() {
		return "SetHfmCodes [hfmcode=" + hfmcode + ", tptype=" + tptype + ", classification=" + classification
				+ ", naturalb=" + naturalb + ", globalval=" + globalval + ", ordernum=" + ordernum + ", naturalsign="
				+ naturalsign + ", description=" + description + "]";
	}


}

