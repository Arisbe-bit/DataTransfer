package com.neoris.tcl.security.models;

import java.util.HashMap;
import java.util.Map;

public enum Rol {
    ADMIN("Administrator"),
    HFMCODES("HFMCodes"),
    HFMCODESOA("HFMCodes-Oracle Accounts"),
    HFMCODESTYPES("Trading Partner Types"),
    PARTNERS("Trading Partners"),
    PAYABLESACCOUNTS("Payables Accounts"),
    RECEIVABLESACCOUNTS("Receivables Accounts"),
    MATCHACCOUNTS("Match Accounts"),
    DSVSCOMPANY("HFM Data by Company"),
    ROLLUP("RollUp Process"),
    ROLLUPHIST("RollUp History"),
    POLICIES("Manual Entries"),
    DEFINEDACCOUNT("Source Accounts"),
	MANUALENTRIESHIST("Manual Entries History"),
	RECLASSIFICATION("Reclassifications"),
	ROLLEXCEPTIONS("Trading Partner Exceptions");

    public final String label;
    private static final Map<String, Rol> BY_LABEL = new HashMap<>();

    static {
        for (Rol rol : values()) {
            BY_LABEL.put(rol.label, rol);
        }
    }

    Rol(String label) {
        this.label = label;
    }

    public static Rol valueOfLabel(String label) {
        for (Rol rol : values()) {
            if (rol.label.equals(label)) {
                return rol;
            }
        }
        return null;
    }

}
