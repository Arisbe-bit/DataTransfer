package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SET_CURRENCIES")
public class SetCurrecyCode implements Serializable{

	private static final long serialVersionUID = 5432175553545677091L;
	
	@Id
	private String currencycode;
	private String description;
	
	public SetCurrecyCode() {
		
	}
	
	public SetCurrecyCode(String currencycode, String description) {		
		this.currencycode = currencycode;
		this.description = description;
	}
	
	public String getcurrencycode() {
		return currencycode;
	}

	public void setcurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SetCurrecyCode [currencycode=" + currencycode + ", description=" + description + "]";
	}	
	
}
