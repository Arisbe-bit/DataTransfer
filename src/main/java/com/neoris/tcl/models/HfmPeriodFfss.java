package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Subselect;


@Entity
@Table(name ="ROLLUPHFMFFSSPERIOD")
public class HfmPeriodFfss implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5362221129863237217L;
	@Id
	private Long num;
	
	private String periodnm;
	
	private int companyid;

	public HfmPeriodFfss() {
		
	}
	





	public HfmPeriodFfss(Long num, String periodnm, int companyid) {
		
		this.num = num;
		this.periodnm = periodnm;
		this.companyid = companyid;
	}






	public Long getNum() {
		return num;
	}






	public void setNum(Long num) {
		this.num = num;
	}






	public String getPeriodnm() {
		return periodnm;
	}

	public void setPeriodnm(String periodnm) {
		this.periodnm = periodnm;
	}

	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}






	@Override
	public String toString() {
		return "HfmPeriodFfss [num=" + num + ", periodnm=" + periodnm + ", companyid=" + companyid + "]";
	}

	
	
	
}
