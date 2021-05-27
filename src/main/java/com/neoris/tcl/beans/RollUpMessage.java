package com.neoris.tcl.beans;

import com.neoris.tcl.models.HfmRollupEntries;

public class RollUpMessage {

	private String message;
	private String title;
	private String severity;
	private HfmRollupEntries rollup;

	public RollUpMessage() {
		this.rollup = new HfmRollupEntries();
	}

	public RollUpMessage(String message, String title, String severity, HfmRollupEntries rollup) {
		this.message = message;
		this.title = title;
		this.rollup = rollup;
		this.severity = severity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public HfmRollupEntries getRollup() {
		return rollup;
	}

	public void setRollup(HfmRollupEntries rollup) {
		this.rollup = rollup;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	@Override
	public String toString() {
		return String.format("RollUpMessage [message=%s, title=%s, severity=%s, rollup=%s]", message, title, severity,
				rollup);
	}


}
