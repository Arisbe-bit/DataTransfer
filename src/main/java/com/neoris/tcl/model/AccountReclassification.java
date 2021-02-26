package com.neoris.tcl.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SET_RECLASSIF_ACCOUNTS")
public class AccountReclassification implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = 4171437692617794932L;
    @Id
    private String originacc; // from SET_HFM_CODES tabla
    private String destacc; // from SET_HFM_CODES tabla
    private String origincc;
    private String destcc;
    
    public AccountReclassification() {

    }
    public AccountReclassification(String originacc, String destacc, String origincc, String destcc) {
        super();
        this.originacc = originacc;
        this.destacc = destacc;
        this.origincc = origincc;
        this.destcc = destcc;
    }

    public String getOriginacc() {
        return originacc;
    }

    public void setOriginacc(String originacc) {
        this.originacc = originacc;
    }

    public String getDestacc() {
        return destacc;
    }

    public void setDestacc(String destacc) {
        this.destacc = destacc;
    }

    public String getOrigincc() {
        return origincc;
    }

    public void setOrigincc(String origincc) {
        this.origincc = origincc;
    }

    public String getDestcc() {
        return destcc;
    }

    public void setDestcc(String destcc) {
        this.destcc = destcc;
    }

    @Override
    public String toString() {
        return String.format("AccountReclassification [originacc=%s, destacc=%s, origincc=%s, destcc=%s]", originacc,
                destacc, origincc, destcc);
    }

}
