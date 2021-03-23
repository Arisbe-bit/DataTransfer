package com.neoris.tcl.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLLUP_VPARTNERS_PAYABLES")
public class ViewPartnersICP implements Serializable {

    private static final long serialVersionUID = -732130139597244868L;

    @Id
    private Long num;

    @Column
    private int organization_id;
    @Column
    private String companynm;
    @Column
    private String supplier_num;
    @Column
    private String vendor_name;
    @Column
    private String icpcode;

    public String getIcpcode() {
        return icpcode;
    }

    public void setIcpcode(String icpcode) {
        this.icpcode = icpcode;
    }

    public ViewPartnersICP() {

    }

    public ViewPartnersICP(Long num, int organization_id, String companynm, String supplier_num, String vendor_name,
            String icpcode) {

        this.num = num;
        this.organization_id = organization_id;
        this.companynm = companynm;
        this.supplier_num = supplier_num;
        this.vendor_name = vendor_name;
        this.icpcode = icpcode;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public int getorganization_id() {
        return organization_id;
    }

    public void setorganization_id(int organization_id) {
        this.organization_id = organization_id;
    }

    public String getCompanynm() {
        return companynm;
    }

    public void setCompanynm(String companynm) {
        this.companynm = companynm;
    }

    public String getSupplier_num() {
        return supplier_num;
    }

    public void setSupplier_num(String supplier_num) {
        this.supplier_num = supplier_num;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    @Override
    public String toString() {
        return "ViewPartnersICP [num=" + num + ", organization_id=" + organization_id + ", companynm=" + companynm
                + ", supplier_num=" + supplier_num + ", vendor_name=" + vendor_name + ", icpcode=" + icpcode + "]";
    }

}
