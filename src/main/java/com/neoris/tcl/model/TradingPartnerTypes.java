package com.neoris.tcl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SET_TRADING_PARTNERS_TYPES")
public class TradingPartnerTypes {

    @Id
    @Column(name="TPTYPE", nullable=false, length=12)
    private String tptype;

    @Column(name="DESCRIPTION", nullable=false, length=20)
    private String description;

    public TradingPartnerTypes() {
        //
    }

    public TradingPartnerTypes(String tptype, String description) {
        super();
        this.tptype = tptype;
        this.description = description;
    }
    
    public String getTptype() {
        return tptype;
    }

    public void setTptype(String tptype) {
        this.tptype = tptype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("TradingPartnerTypes [tptype=%s, description=%s]", tptype, description);
    }

}
