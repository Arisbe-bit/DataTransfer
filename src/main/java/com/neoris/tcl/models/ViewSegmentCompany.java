package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Subselect;

@Entity
@Subselect(value = "select num,segment1 from viewcompanyacronym")
public class ViewSegmentCompany implements Serializable {
	
	private static final long serialVersionUID = 170999999999999991L;

	@Id
	private Long num;
	private String segment1;

	public ViewSegmentCompany() {
		
	}
	
	
	
	
	public ViewSegmentCompany(Long num, String segment1) {
		
		this.num = num;
		this.segment1 = segment1;
	}




	public Long getNum() {
		return num;
	}




	public void setNum(Long num) {
		this.num = num;
	}




	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}




	@Override
	public String toString() {
		return "ViewSegmentCompany [num=" + num + ", segment1=" + segment1 + "]";
	}
	
	
	
}
