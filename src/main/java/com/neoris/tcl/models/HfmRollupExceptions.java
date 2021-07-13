package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hfm_rollup_exceptions")
public class HfmRollupExceptions implements Serializable {

	private static final long serialVersionUID = 7111822966653268829L;
	
	@EmbeddedId
	private HfmRollupExceptionsPK id;
	private String userid;
	private String segment1;
	public HfmRollupExceptionsPK getId() {
		return id;
	}
	public void setId(HfmRollupExceptionsPK id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public HfmRollupExceptions() {
		this.id = new HfmRollupExceptionsPK();
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public HfmRollupExceptions(HfmRollupExceptionsPK id, String userid, String segment1) {

		this.id = id;
		this.userid = userid;
		this.segment1 = segment1;
	}
	@Override
	public String toString() {
		return "HfmRollupExceptions [id=" + id + ", userid=" + userid + ", segment1=" + segment1 + "]";
	}

	
	
	
}
