package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "hfm_rollup_exceptions")
public class HfmRollupExceptions implements Serializable {

	private static final long serialVersionUID = 7111822966653268829L;
	
	@EmbeddedId
	private HfmRollupExceptionsPK id;
	private String userid;
	private String segment1;
	@Column(name = "modified",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false)
	@Temporal(TemporalType.DATE)
	private Date modified;
	@Transient
	private String uuid;
	
	

	public HfmRollupExceptions(HfmRollupExceptionsPK id, String userid, String segment1) {

		this.id = id;
		this.userid = userid;
		this.segment1 = segment1;
	}
	

	public HfmRollupExceptions() {
		this.id = new HfmRollupExceptionsPK();
		this.uuid = UUID.randomUUID().toString();
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
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
	
	public Date getModified() {

		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	public String getUuid() {
		return uuid;
	}
	
	
	@Override
	public String toString() {
		return "HfmRollupExceptions [id=" + id + ", userid=" + userid + ", segment1=" + segment1 + ", modified="
				+ modified + ", uuid=" + uuid + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, uuid);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HfmRollupExceptions other = (HfmRollupExceptions) obj;
		return Objects.equals(id, other.id) && Objects.equals(uuid, other.uuid);
	}


	
	
	
}
