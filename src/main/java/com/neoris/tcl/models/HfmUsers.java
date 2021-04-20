package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="hfm_users")
public class HfmUsers implements Serializable{

	
	private static final long serialVersionUID = 5676213260556508605L;
	
	@Id
	private String userid;
	
	private String password;
	private String setnm;
	private String rollup;
	private String username;
	
	
	public HfmUsers() {
	
	}
	
	
	public HfmUsers(String userid, String password, String setnm, String rollup, String username) {
		
		this.userid = userid;
		this.password = password;
		this.setnm = setnm;
		this.rollup = rollup;
		this.username = username;
	}

	public String getUserid() {
		return userid;
	}

	public void setnmUserid(String userid) {
		this.userid = userid;
	}

	public String getpassword() {
		return password;
	}

	public void setnmpassword(String password) {
		this.password = password;
	}

	public String getsetnm() {
		return setnm;
	}

	public void setnmsetnm(String setnm) {
		this.setnm = setnm;
	}

	public String getRollup() {
		return rollup;
	}

	public void setnmRollup(String rollup) {
		this.rollup = rollup;
	}

	public String getUsername() {
		return username;
	}

	public void setnmUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "HfmUsers [userid=" + userid + ", password=" + password + ", setnm=" + setnm + ", rollup=" + rollup + ", username="
				+ username + "]";
	}

	
}
