package com.neoris.tcl.beans;

import java.io.Serializable;

public class RollUpBean implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1525326609613297689L;
    
    private int period;
    private int entity;
    private String application;
    private String scenario;
    private int year;
    private String view;
    private String value;
    private String segment;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getEntity() {
        return entity;
    }

    public void setEntity(int entity) {
        this.entity = entity;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    @Override
    public String toString() {
        return String.format(
                "RollUpBean [period=%s, entity=%s, application=%s, scenario=%s, year=%s, view=%s, value=%s, segment=%s]",
                period, entity, application, scenario, year, view, value, segment);
    }

}
