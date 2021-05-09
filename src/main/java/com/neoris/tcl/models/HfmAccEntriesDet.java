package com.neoris.tcl.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "hfm_manual_entries_det")
public class HfmAccEntriesDet implements Serializable {

	private final static Logger LOG = LoggerFactory.getLogger(HfmAccEntriesDet.class);
	private static final long serialVersionUID = 1710075553545677091L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEMD_SEQ")
	@SequenceGenerator(sequenceName = "SEQROLLUPMENTRIESDET", allocationSize = 1, name = "ITEMD_SEQ")
	private Long movid;

	private Long itemid;
	private String hfmcode;
	private BigDecimal debits;
	private BigDecimal credits;
	private BigDecimal amount;
	private String icpcode;
	private String areaid;
	private String description;

	public HfmAccEntriesDet() {

	}

	public HfmAccEntriesDet(Long movid, Long itemid, String hfmcode, BigDecimal debits, BigDecimal credits,
			BigDecimal amount, String icpcode, String areaid, String description) {

		this.movid = movid;
		this.itemid = itemid;
		this.hfmcode = hfmcode;
		this.debits = debits;
		this.credits = credits;
		this.amount = amount;
		this.icpcode = icpcode;
		this.areaid = areaid;
		this.description = description;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTrimedDescription() {
		return description.substring(0, Math.min(description.length(), 20));
	}

	@Override
	public String toString() {
		return "HfmAccEntriesDet [movid=" + movid + ", itemid=" + itemid + ", hfmcode=" + hfmcode + ", debits=" + debits
				+ ", credits=" + credits + ", amount=" + amount + ", icpcode=" + icpcode + ", areaid=" + areaid
				+ ", description=" + description + "]";
	}

}
