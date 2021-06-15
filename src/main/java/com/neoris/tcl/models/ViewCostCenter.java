package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// import org.hibernate.annotations.Subselect;

@Entity
@Table(name = "ROLLUP_VOPEXAREA")
public class ViewCostCenter implements Serializable {
	// @Subselect("select NUM,COSTCENTER,ccname ,opexarea from ROLLUP_VOPEXAREA")
	private static final long serialVersionUID = 5449771232100970467L;

	@Id
	private Long num;

	@Column
	private String costcenter;
	@Column
	private String ccname;
	@Column
	private String opexarea;

	public ViewCostCenter() {

	}

	public ViewCostCenter(Long num, String costcenter, String ccname, String opexarea) {
		this.num = num;
		this.costcenter = costcenter;
		this.ccname = ccname;
		this.opexarea = opexarea;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
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
		return String.format("ViewCostCenter [num=%s, costcenter=%s, ccname=%s, opexarea=%s]", num, costcenter, ccname,
				opexarea);
	}

}
