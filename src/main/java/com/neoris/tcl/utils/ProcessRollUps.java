/**
 * 
 */
package com.neoris.tcl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neoris.tcl.models.HfmRollupEntries;

/**
 * @author Marco Vargas
 *
 */
public class ProcessRollUps implements Runnable {

    private final static Logger LOG = LoggerFactory.getLogger(ProcessRollUps.class);
    private HfmRollupEntries rollUp;
    
    @Override
    public void run() {
        String mensaje = String.format("Processing RollUp for company:%s, period: %s, year: %s", rollUp.getCompanyid(), rollUp.getRperiod(), rollUp.getRyear());
        //Functions.addInfoMessage("Info", mensaje);
        try {
            LOG.info(mensaje);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info("Finish!!");

    }

    public HfmRollupEntries getRollUp() {
        return rollUp;
    }

    public void setRollUp(HfmRollupEntries rollUp) {
        this.rollUp = rollUp;
    }

}
