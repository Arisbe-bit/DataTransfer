package com.neoris.tcl.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SET_MASTER_ACCOUNTS")
public class MasterAccount implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = -1743557855959002740L;

    @Id
    private String hfmparent; // from SET_HMF_CODES
	private String naturalsign; //  + or -
	
	public MasterAccount() {
		// TODO Auto-generated constructor stub
	}

	public MasterAccount(String hfmparent, String naturalsign) {
		super();
		this.hfmparent = hfmparent;
		this.naturalsign = naturalsign;
	}

	public String getHfmparent() {
		return hfmparent;
	}

	public void setHfmparent(String hfmparent) {
		this.hfmparent = hfmparent;
	}

	public String getNaturalsign() {
		return naturalsign;
	}

	public void setNaturalsign(String naturalsign) {
		this.naturalsign = naturalsign;
	}

    @Override
    public String toString() {
        return String.format("MasterAccount [hfmparent=%s, naturalsign=%s]", hfmparent, naturalsign);
    }
}

