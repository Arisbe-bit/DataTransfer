package com.neoris.tcl.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * The primary key class for the set_payables_icp database table.
 * 
 */
@Embeddable
public class SetPayablesIcpPK implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4625003557488333132L;

    private Long companyid;
    private int supplierno;

    public SetPayablesIcpPK() {
    }

    public SetPayablesIcpPK(Long companyid, int supplierno) {
        this.companyid = companyid;
        this.supplierno = supplierno;
    }

    public Long getCompanyid() {
        return this.companyid;
    }

    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }

    public int getSupplierno() {
        return this.supplierno;
    }

    public void setSupplierno(int supplierno) {
        this.supplierno = supplierno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyid, supplierno);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SetPayablesIcpPK other = (SetPayablesIcpPK) obj;
        return Objects.equals(companyid, other.companyid) && Objects.equals(supplierno, other.supplierno);
    }

    @Override
    public String toString() {
        return "SetPayablesIcpPK [companyid=" + companyid + ",  supplierno=" + supplierno + "]";
    }

}