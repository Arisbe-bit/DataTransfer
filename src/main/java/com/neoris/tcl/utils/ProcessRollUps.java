
/**
 * 
 */
package com.neoris.tcl.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.services.IHfmRollupEntriesService;


public class ProcessRollUps implements Runnable {

	private final static Logger LOG = LoggerFactory.getLogger(ProcessRollUps.class);

	private HfmRollupEntries rollUp;
	private IHfmRollupEntriesService service;
	private String process;
	private int numDrill;
	private boolean processValidations;
	private boolean matchAccounts;
	private String processId;
	private FacesContext facesContext;
	private PrimeFaces primefaces;

	public ProcessRollUps(HfmRollupEntries rollUp, IHfmRollupEntriesService service, String process, int numDrill, boolean processValidations, boolean matchAccounts) {
		this.rollUp = rollUp;
		this.service = service;
		this.process = process;
		this.numDrill = numDrill;
		this.processValidations = processValidations;
		this.matchAccounts = matchAccounts;
	}

	public ProcessRollUps() {

	}

	@Override
	public void run() {
		if (this.processValidations) {
			processValidations();
		}
		if (this.matchAccounts) {
			matchAccounts();
		}
		if (numDrill > 0) {
			processDrill(numDrill);
		} else {
			processRollUps();
		}
		LOG.info("Finish!!");
	}

	private void matchAccounts() {
		String mensaje = String.format("Processing Match Account for company:%s, period: %s, year: %s", rollUp.getCompanyid(), rollUp.getRperiod(), rollUp.getRyear());
		LOG.info(mensaje);
		addMessage("Match Accounts", mensaje);
		service.rollUpMatchAccounts(rollUp.getCompanyid().intValue(), rollUp.getRperiod(), rollUp.getRyear(), "admin");
	}

	private void processRollUps() {
		String mensaje = String.format("Processing RollUp process [%s] for company:%s, period: %s, year: %s", process, rollUp.getCompanyid(), rollUp.getRperiod(), rollUp.getRyear());
		LOG.info(mensaje);
		addMessage(">======== Processing " + process, mensaje);
		if (process.equalsIgnoreCase(IHfmRollupEntriesService.P_COSTMANAGER)) {
			LOG.info("VALOR DE PROCESS para costmanager "+process);
			service.rollUpDrillCostMngDetGetHeaders(rollUp.getCompanyid().intValue(), rollUp.getSegment1(),
					rollUp.getRperiod(), rollUp.getRyear(), process);
		} else {
			LOG.info("VALOR DE PROCESS "+process);
			service.getHeaders(rollUp.getCompanyid().intValue(), rollUp.getSegment1(), rollUp.getRperiod(),
					rollUp.getRyear(), process, "adminXX");
		}
	}

	private void processValidations() {
		String mensaje = String.format("Processing Validations for company:%s, period: %s, year: %s", rollUp.getCompanyid(), rollUp.getRperiod(), rollUp.getRyear());
		LOG.info(mensaje);
		addMessage("Validations", mensaje);
		service.rollUpValidations(rollUp.getCompanyid().intValue(), rollUp.getRperiod(), rollUp.getRyear(), rollUp.getSegment1(), "admin");
	}

	/**
	 * 
	 * @param num
	 */
	private void processDrill(int num) {
		String mensaje = String.format("Processing Drills %s for company:%s, period: %s, year: %s", num, rollUp.getCompanyid(), rollUp.getRperiod(), rollUp.getRyear());
		LOG.info(mensaje);
		addMessage(String.format("Drilss[%s]", num), mensaje);		
		switch (num) {
		case 1:
			service.costManager1Drills(rollUp.getCompanyid().intValue(),rollUp.getRperiod(), rollUp.getRyear(), "admin");
			break;
		case 2:
			service.costManager2Drills(rollUp.getCompanyid().intValue(),rollUp.getRperiod(), rollUp.getRyear(), "admin");
			break;
		case 3:
			service.costManager3Drills(rollUp.getCompanyid().intValue(),rollUp.getRperiod(), rollUp.getRyear(), "admin");
			break;
		case 4:
			service.costManager4Drills(rollUp.getCompanyid().intValue(),rollUp.getRperiod(), rollUp.getRyear(), "admin");
			break;
		case 5:
			service.costManager5Drills(rollUp.getCompanyid().intValue(),rollUp.getRperiod(), rollUp.getRyear(), "admin");
			break;
		case 6:
			service.costManager6Drills(rollUp.getCompanyid().intValue(),rollUp.getRperiod(), rollUp.getRyear(), "admin");
			break;
		case 7:
			service.costManager7Drills(rollUp.getCompanyid().intValue(),rollUp.getRperiod(), rollUp.getRyear(), "admin");
			break;
		case 8:
			service.costManager8Drills(rollUp.getCompanyid().intValue(),rollUp.getRperiod(), rollUp.getRyear(), "admin");
			break;
		case 9:
			service.costManager9Drills(rollUp.getCompanyid().intValue(),rollUp.getRperiod(), rollUp.getRyear(), "admin");
			break;
		default:
			LOG.warn("The Drill Number: {} doesn´t exists!!!", num);
			break;
		}
	}

	public HfmRollupEntries getRollUp() {
		return rollUp;
	}

	public void setRollUp(HfmRollupEntries rollUp) {
		this.rollUp = rollUp;
	}

	public IHfmRollupEntriesService getService() {
		return service;
	}

	public void setService(IHfmRollupEntriesService service) {
		this.service = service;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public int getNumDrill() {
		return numDrill;
	}

	public void setNumDrill(int numDrill) {
		this.numDrill = numDrill;
	}

	public boolean isProcessValidations() {
		return processValidations;
	}

	public void setProcessValidations(boolean processValidations) {
		this.processValidations = processValidations;
	}

	public boolean isMatchAccounts() {
		return matchAccounts;
	}

	public void setMatchAccounts(boolean matchAccounts) {
		this.matchAccounts = matchAccounts;
	}

	public String getProcessId() {
		this.processId = "thead-rollup-process";
		if (this.getNumDrill() > 0) {
			this.processId = String.format("Th-drill-%s-%s", rollUp.getCompanyid(), this.getNumDrill());
		} else if (!this.getProcess().isEmpty()) {
			this.processId = String.format("Th-prcss-%s-%s", this.getProcess(), rollUp.getCompanyid());
		} else if (this.isMatchAccounts()) {
			this.processId = String.format("Th-match-%s", rollUp.getCompanyid());
		} else if (this.isProcessValidations()) {
			this.processId = String.format("Th-vldtn-%s", rollUp.getCompanyid());
		}
		return processId;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}
	
    public PrimeFaces getPrimefaces() {
		return primefaces;
	}

	public void setPrimefaces(PrimeFaces primefaces) {
		this.primefaces = primefaces;
	}

	private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        this.facesContext.addMessage(null, message);
    }
    

	@Override
	public String toString() {
		return String.format(
				"ProcessRollUps [rollUp=%s, service=%s, process=%s, numDrill=%s, processValidations=%s, matchAccounts=%s, processId=%s]",
				rollUp, service, process, numDrill, processValidations, matchAccounts, processId);
	}

}
