package com.neoris.tcl.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SET_HFM_CODES")
public class HFMcodes implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8884601734193607573L;
    @Id
    private String hfmcode;
    private String tptype;  

    public HFMcodes() {

    }
    
    public HFMcodes(String hfmcode, String tptype) {
        this.hfmcode = hfmcode;
        this.tptype = tptype;
    }

    public String getHfmcode() {
        return hfmcode;
    }

    public void setHfmcode(String hfmcode) {
        this.hfmcode = hfmcode;
    }

    public String getTptype() {
        return tptype;
    }

    public void setTptype(String tptype) {
        this.tptype = tptype;
    }

    @Override
    public String toString() {
        return String.format("HFMcodes [hfmcode=%s, tptype=%s]", hfmcode, tptype);
    }
}
