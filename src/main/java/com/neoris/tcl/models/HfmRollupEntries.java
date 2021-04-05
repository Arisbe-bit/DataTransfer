package com.neoris.tcl.models;

import java.io.Serializable;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The persistent class for the hfm_rollup_entries database table.
 * 
 */
@Entity
@Table(name = "hfm_rollup_entries")
public class HfmRollupEntries implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4557949662258406721L;
    private final static Logger LOG = LoggerFactory.getLogger(HfmRollupEntries.class);
    private final static String PROCESS_ICON = "fa fa-refresh fa-spin fa-3x";
    private static final String PROCESS_ICON_OK = "fa fa-check-square-o fa-3x";
    private static final String PROCESS_ICON_ERROR = "fa fa-window-close-o fa-3x";

    public final static String STATUS_PROCESSING = "PROCESSING";
    public final static String STATUS_PENDING = "PENDING";
    public final static String STATUS_OK = "OK";
    public final static String STATUS_ERROR = "ERROR";

    @Id
    private Long companyid;

    private String entity;

    private String attribute1;

    private String attribute2;

    private String attribute3;

    private String attribute4;

    private String attribute5;

    private String attribute6;

    private String rapplication;

    private String reclassifications;

    private String rperiod;

    private String rvalue;

    private String rview;

    private String ryear;

    private String scenario;

    private String segment;
    
    private String segment1;

   

	private String validations;
 
    public HfmRollupEntries() {
    }

    public HfmRollupEntries(Long companyid, String attribute1, String attribute2, String attribute3, String attribute4,
            String attribute5, String attribute6, String entity, String rapplication, String reclassifications,
            String rperiod, String rvalue, String rview, String ryear, String scenario, String segment,
            String validations, String segment1) {
        this.companyid = companyid;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
        this.attribute6 = attribute6;
        this.entity = entity;
        this.rapplication = rapplication;
        this.reclassifications = reclassifications;
        this.rperiod = rperiod;
        this.rvalue = rvalue;
        this.rview = rview;
        this.ryear = ryear;
        this.scenario = scenario;
        this.segment = segment;
        this.validations = validations;
        this.segment1 = segment1;
    }

    public Long getCompanyid() {
        return this.companyid;
    }

    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }

    public String getAttribute1() {
        return this.attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return this.attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return this.attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return this.attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return this.attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return this.attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getEntity() {
        return this.entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getRapplication() {
        return this.rapplication;
    }

    public void setRapplication(String rapplication) {
        this.rapplication = rapplication;
    }

    public String getReclassifications() {
        return this.reclassifications;
    }

    public void setReclassifications(String reclassifications) {
        this.reclassifications = reclassifications;
    }

    public String getRperiod() {
        return this.rperiod;
    }

    public void setRperiod(String rperiod) {
        this.rperiod = rperiod;
    }

    public String getRvalue() {
        return this.rvalue;
    }

    public void setRvalue(String rvalue) {
        this.rvalue = rvalue;
    }

    public String getRview() {
        return this.rview;
    }

    public void setRview(String rview) {
        this.rview = rview;
    }

    public String getRyear() {
        return this.ryear;
    }

    public void setRyear(String ryear) {
        this.ryear = ryear;
    }

    public String getScenario() {
        return this.scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getSegment() {
        return this.segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getValidations() {
        return this.validations;
    }

    public void setValidations(String validations) {
        this.validations = validations;
    }

    public String getFullPeriod() {
        return this.getRperiod() + "-" + this.getRyear();
    }

    public String getSegment1() {
		return segment1;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
    /**
     * This method is needed for the rollup.xhtml form when changing the year or
     * period in the page. This does nothing, but is required with ajax callback.
     * 
     * @param ev
     */
    public void changeItemValue(AjaxBehaviorEvent ev) {
        if (ev.getSource() instanceof HtmlSelectOneMenu) {
            HtmlSelectOneMenu selectOne = (HtmlSelectOneMenu) ev.getSource();
            LOG.info("Receive HtmlSelectOneMenu Componet. Id = {}, value = {}", selectOne.getId(),
                    selectOne.getValue());
        }

        if (ev.getSource() instanceof HtmlInputText) {
            HtmlInputText inputText = (HtmlInputText) ev.getSource();
            LOG.info("Receive HtmlInputText Componet. Id = {}, value = {}", inputText.getId(), inputText.getValue());
        }
    }

    public void clean() {
        clean("");
    }

    public void pending() {
        clean(STATUS_PENDING);
    }

    public void clean(String status) {
        this.attribute1 = status;
        this.attribute2 = status;
        this.attribute3 = status;
        this.attribute4 = status;
        this.attribute5 = status;
        this.attribute6 = status;
    }

    public String getTrialBalanceIcon() {
        return getProcessIcon(this.attribute1);
    }

    public String getPayablesIcon() {
        return getProcessIcon(this.attribute2);
    }

    public String getReceivablesIcon() {
        return getProcessIcon(this.attribute3);
    }

    public String getGainsLoosesIcon() {
        return getProcessIcon(this.attribute4);
    }

    public String getExportSalesIcon() {
        return getProcessIcon(this.attribute5);
    }

    private String getProcessIcon(String status) {
        String icon = "";
        if (status.equalsIgnoreCase(STATUS_PROCESSING)) {
            icon = PROCESS_ICON;
        }
        if (status.equalsIgnoreCase(STATUS_OK)) {
            icon = PROCESS_ICON_OK;
        }
        if (status.equalsIgnoreCase(STATUS_ERROR)) {
            icon = PROCESS_ICON_ERROR;
        }
        return icon;
    }

    @Override
    public String toString() {
        return String.format(
                "HfmRollupEntries [companyid=%s, entity=%s, attribute1=%s, attribute2=%s, attribute3=%s, attribute4=%s, attribute5=%s, attribute6=%s, rapplication=%s, reclassifications=%s, rperiod=%s, rvalue=%s, rview=%s, ryear=%s, scenario=%s, segment=%s, validations=%s, segment1=%s]",
                companyid, entity, attribute1, attribute2, attribute3, attribute4, attribute5, attribute6, rapplication,
                reclassifications, rperiod, rvalue, rview, ryear, scenario, segment, validations, segment1);
    }

}