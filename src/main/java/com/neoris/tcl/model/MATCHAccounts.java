package com.neoris.tcl.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	
@Table(name = "SET_MATCH_ACCOUNTS")
public class MATCHAccounts implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 889742197593152565L;
    @Id
    private String hfmparent; // from SET_HMF_CODES
	private String hfmchild; // from SET_HMF_CODES	
	private String vsign;   //   + or   -
	
	public MATCHAccounts() {
		// TODO Auto-generated constructor stub
	}

	public MATCHAccounts(String hfmparent, String hfmchild, String vsign) {
		super();
		this.hfmparent = hfmparent;
		this.hfmchild = hfmchild;
		this.vsign = vsign;
	}

	public String getHfmparent() {
		return hfmparent;
	}

	public void setHfmparent(String hfmparent) {
		this.hfmparent = hfmparent;
	}

	public String getHfmchild() {
		return hfmchild;
	}

	public void setHfmchild(String hfmchild) {
		this.hfmchild = hfmchild;
	}

	public String getVsign() {
		return vsign;
	}

	public void setVsign(String vsign) {
		this.vsign = vsign;
	}

    @Override
    public String toString() {
        return String.format("MATCHAccounts [hfmparent=%s, hfmchild=%s, vsign=%s]", hfmparent, hfmchild, vsign);
    }	
	
}
