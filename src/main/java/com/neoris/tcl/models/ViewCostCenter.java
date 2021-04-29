package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select costcenter,ccname ,opexarea from ROLLUP_VOPEXAREA")
public class ViewCostCenter implements Serializable{
	
	private static final long serialVersionUID = 5449771232153970467L;
	
	@Id
	@Column(name = "COSTCENTER")
	private String costcenter;
	
	@Column(name = "CCNAME")
	private String ccname;
	
	@Column(name = "OPEXAREA")
	private String opexarea;
	
	public ViewCostCenter() {
		
	}
	
	public ViewCostCenter(String costcenter, String ccname, String opexarea) {
		
		this.costcenter = costcenter;
		this.ccname = ccname;
		this.opexarea = opexarea;
	}
	public String getCostcenter() {
		return costcenter;
	}
	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}
	public String getCcname() {
		return ccname;
	}
	public void setCcname(String ccname) {
		this.ccname = ccname;
	}
	public String getOpexarea() {
		return opexarea;
	}
	public void setOpexarea(String opexarea) {
		this.opexarea = opexarea;
	}
	@Override
	public String toString() {
		return "ViewCostCenter [costcenter=" + costcenter + ", ccname=" + ccname + ", opexarea=" + opexarea + "]";
	}
	
	

}
