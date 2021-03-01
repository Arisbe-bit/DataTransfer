package com.neoris.tcl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HFM_ROLLUP_ENTRIES")
public class RollUpEntries {

	@Id
	@Column(name = "COMPANYID", nullable = false )
	private Long companyId;

	@Column(name = "RAPPLICATION", length = 20, nullable = false)
	private String rapplication;

	@Column(name = "SCENARIO", length = 20, nullable = false)
	private String scenario;

	@Column(name = "RYEAR", length = 4, nullable = false)
	private String ryear;

	@Column(name = "RPERIOD", length = 3, nullable = false)
	private String rperiod;
	
	@Column(name = "RVIEW", length = 4, nullable = false)
	private String rview;
	
	@Column(name = "RVALUE", length = 20, nullable = false)
	private String rvalue;
	
	@Column(name = "ENTITY", length = 20, nullable = false)
	private String entity;
	
	@Column(name = "SEGMENT", length = 20)
	private String segment;
	
	@Column(name = "ATTRIBUTE1", length = 20)
	private String attribute1;
	
	@Column(name = "ATTRIBUTE2", length = 20)
	private String attribute2;
	
	@Column(name = "ATTRIBUTE3", length = 20)
	private String attribute3;
	
	@Column(name = "ATTRIBUTE4", length = 20)
	private String attribute4;
	
	@Column(name = "ATTRIBUTE5", length = 20)
	private String attribute5;
	
	@Column(name = "ATTRIBUTE6", length = 20)
	private String attribute6;
	
	@Column(name = "VALIDATIONS", length = 20)
	private String validations;
	
	@Column(name = "RECLASSIFICATIONS", length = 20)
	private String reclassifications;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getRapplication() {
		return rapplication;
	}

	public void setRapplication(String rapplication) {
		this.rapplication = rapplication;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getRyear() {
		return ryear;
	}

	public void setRyear(String ryear) {
		this.ryear = ryear;
	}

	public String getRperiod() {
		return rperiod;
	}

	public void setRperiod(String rperiod) {
		this.rperiod = rperiod;
	}

	public String getRview() {
		return rview;
	}

	public void setRview(String rview) {
		this.rview = rview;
	}

	public String getRvalue() {
		return rvalue;
	}

	public void setRvalue(String rvalue) {
		this.rvalue = rvalue;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getValidations() {
		return validations;
	}

	public void setValidations(String validations) {
		this.validations = validations;
	}

	public String getReclassifications() {
		return reclassifications;
	}

	public void setReclassifications(String reclassifications) {
		this.reclassifications = reclassifications;
	}

	@Override
	public String toString() {
		return "RollUpEntries [companyId=" + companyId + ", rapplication=" + rapplication + ", scenario=" + scenario
				+ ", ryear=" + ryear + ", rperiod=" + rperiod + ", rview=" + rview + ", rvalue=" + rvalue + ", entity="
				+ entity + ", segment=" + segment + ", attribute1=" + attribute1 + ", attribute2=" + attribute2
				+ ", attribute3=" + attribute3 + ", attribute4=" + attribute4 + ", attribute5=" + attribute5
				+ ", attribute6=" + attribute6 + ", validations=" + validations + ", reclassifications="
				+ reclassifications + "]";
	}

}
