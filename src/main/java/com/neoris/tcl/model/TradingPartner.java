package com.neoris.tcl.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SET_ICPCODES")
public class TradingPartner {

	@Id
    private String ICPcode;
    private String ICPid;  
    private String ICPdesc;
    
    public TradingPartner() {
        
    }
  
    public TradingPartner(String icpcode, String icpid,  String icpdesc) {
        super();
        ICPcode = ICPcode;
        ICPid = ICPcode;
        ICPdesc = ICPcode;
    }
    
    public String getICPcode() {
		return ICPcode;
	}

	public void setICPcode(String iCPcode) {
		ICPcode = iCPcode;
	}

	public String getICPid() {
		return ICPid;
	}

	public void setICPid(String iCPid) {
		ICPid = iCPid;
	}

	

	public String getICPdesc() {
		return ICPdesc;
	}

	public void setICPdesc(String iCPdesc) {
		ICPdesc = iCPdesc;
	}

    @Override
    public String toString() {
        return String.format("TradingPartner [ICPcode=%s, ICPid=%s,  ICPdesc=%s]", ICPcode, ICPid,    ICPdesc);
    }


}
