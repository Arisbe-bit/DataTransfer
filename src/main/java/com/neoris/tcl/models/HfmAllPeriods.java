package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Subselect;

@Entity
@Table(name = "ROLLUPALLPERIODS")
public class HfmAllPeriods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5362221129863237217L;

	@Id
	private String periodid;
	private String periodnm;

	public HfmAllPeriods() {

	}
	
	

	public HfmAllPeriods(String periodid, String periodnm) {
	
		this.periodid = periodid;
		this.periodnm = periodnm;
	}



	public String getPeriodid() {
		return periodid;
	}

	public void setPeriodid(String periodid) {
		this.periodid = periodid;
	}

	public String getPeriodnm() {
		return periodnm;
	}

	public void setPeriodnm(String periodnm) {
		this.periodnm = periodnm;
	}

	@Override
	public String toString() {
		return "HfmAllPeriods [periodid=" + periodid + ", periodnm=" + periodnm + "]";
	}

	
}
