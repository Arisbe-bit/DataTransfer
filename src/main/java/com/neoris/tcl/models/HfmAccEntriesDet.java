package com.neoris.tcl.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "hfm_manual_entries_det")
public class HfmAccEntriesDet implements Serializable {

	private static final long serialVersionUID = 1710075553545677091L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEMD_SEQ")
	@SequenceGenerator(sequenceName = "SEQROLLUPMENTRIESDET", allocationSize = 1, name = "ITEMD_SEQ")
	private Long movid;

	private Long itemid;
	private String hfmcode;
	private BigDecimal debits;
	private BigDecimal credits;
	private String icpcode;
	
//	@ManyToOne
//    @JoinColumn(name="itemid")
//	private HfmAccEntries entries;

	public HfmAccEntriesDet() {

	}

	public HfmAccEntriesDet(Long itemid, Long movid, String hfmcode, BigDecimal debits, BigDecimal credits,
			String icpcode) {

		this.itemid = itemid;
		this.movid = movid;
		this.hfmcode = hfmcode;
		this.debits = debits;
		this.credits = credits;
		this.icpcode = icpcode;
	}

	public Long getItemid() {
		return itemid;
	}

	public void setItemid(Long long1) {
		this.itemid = long1;
	}

	public Long getMovid() {
		return movid;
	}

	public void setMovid(Long movid) {
		this.movid = movid;
	}

	public String getHfmcode() {
		return hfmcode;
	}

	public void setHfmcode(String hfmcode) {
		this.hfmcode = hfmcode;
	}

	public BigDecimal getDebits() {
		return debits;
	}

	public void setDebits(BigDecimal debits) {
		this.debits = debits;
	}

	public BigDecimal getCredits() {
		return credits;
	}

	public void setCredits(BigDecimal credits) {
		this.credits = credits;
	}

	public String getIcpcode() {
		return icpcode;
	}

	public void setIcpcode(String icpcode) {
		this.icpcode = icpcode;
	}

	@Override
	public String toString() {
		return "HfmAccEntriesDet [movid=" + movid + ", itemid=" + itemid + ", hfmcode=" + hfmcode + ", debits=" + debits
				+ ", credits=" + credits + ", icpcode=" + icpcode + "]";
	}

}
