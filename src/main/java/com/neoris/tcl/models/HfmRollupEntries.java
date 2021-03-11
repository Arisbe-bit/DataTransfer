package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the hfm_rollup_entries database table.
 * 
 */
@Entity
@Table(name = "hfm_rollup_entries")
public class HfmRollupEntries implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4557949662258406721L;

	@Id
	private Long companyid;
	
	private String entity;

	private String attribute1;

	private String attribute2;

	private String attribute3;

	private String attribute4;

	private String attribute5;

	private String attribute6;

	private String rapplication;

	private String reclassifications;

	private String rperiod;

	private String rvalue;

	private String rview;

	private int ryear;

	private String scenario;

	private String segment;

	private String validations;

	public HfmRollupEntries() {
	}

	public HfmRollupEntries(Long companyid, String attribute1, String attribute2, String attribute3,
			String attribute4, String attribute5, String attribute6, String entity, String rapplication,
			String reclassifications, String rperiod, String rvalue, String rview, int ryear, String scenario,
			String segment, String validations) {
		this.companyid = companyid;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.attribute5 = attribute5;
		this.attribute6 = attribute6;
		this.entity = entity;
		this.rapplication = rapplication;
		this.reclassifications = reclassifications;
		this.rperiod = rperiod;
		this.rvalue = rvalue;
		this.rview = rview;
		this.ryear = ryear;
		this.scenario = scenario;
		this.segment = segment;
		this.validations = validations;
	}

	public Long getCompanyid() {
		return this.companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public String getAttribute1() {
		return this.attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return this.attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return this.attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return this.attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return this.attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return this.attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getRapplication() {
		return this.rapplication;
	}

	public void setRapplication(String rapplication) {
		this.rapplication = rapplication;
	}

	public String getReclassifications() {
		return this.reclassifications;
	}

	public void setReclassifications(String reclassifications) {
		this.reclassifications = reclassifications;
	}

	public String getRperiod() {
		return this.rperiod;
	}

	public void setRperiod(String rperiod) {
		this.rperiod = rperiod;
	}

	public String getRvalue() {
		return this.rvalue;
	}

	public void setRvalue(String rvalue) {
		this.rvalue = rvalue;
	}

	public String getRview() {
		return this.rview;
	}

	public void setRview(String rview) {
		this.rview = rview;
	}

	public int getRyear() {
		return this.ryear;
	}

	public void setRyear(int ryear) {
		this.ryear = ryear;
	}

	public String getScenario() {
		return this.scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getSegment() {
		return this.segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getValidations() {
		return this.validations;
	}

	public void setValidations(String validations) {
		this.validations = validations;
	}

	@Override
	public String toString() {
		return "HfmRollupEntries [companyid=" + companyid + ", attribute1=" + attribute1 + ", attribute2=" + attribute2
				+ ", attribute3=" + attribute3 + ", attribute4=" + attribute4 + ", attribute5=" + attribute5
				+ ", attribute6=" + attribute6 + ", entity=" + entity + ", rapplication=" + rapplication
				+ ", reclassifications=" + reclassifications + ", rperiod=" + rperiod + ", rvalue=" + rvalue
				+ ", rview=" + rview + ", ryear=" + ryear + ", scenario=" + scenario + ", segment=" + segment
				+ ", validations=" + validations + "]";
	}

}