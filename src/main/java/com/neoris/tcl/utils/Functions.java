package com.neoris.tcl.utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Functions {

    public static void addInfoMessage(String summary, String detail) {
        addMessage(summary, detail, FacesMessage.SEVERITY_INFO);
    }

    public static void addErrorMessage(String summary, String detail) {
        addMessage(summary, detail, FacesMessage.SEVERITY_ERROR);
    }

    public static void addWarnMessage(String summary, String detail) {
        addMessage(summary, detail, FacesMessage.SEVERITY_WARN);
    }

    private static void addMessage(String summary, String detail, Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
