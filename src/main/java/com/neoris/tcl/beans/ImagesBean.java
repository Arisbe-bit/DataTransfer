package com.neoris.tcl.beans;

public class ImagesBean {

	private String pending = "/resources/img/pending.svg";
	private String processing = "/resources/img/processing.svg";
	private String error = "/resources/img/error.svg";
	private String success = "/resources/img/success.svg";

	public String getPending() {
		return pending;
	}

	public void setPending(String pending) {
		this.pending = pending;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "ImagesBean [pending=" + pending + ", processing=" + processing + ", error=" + error + ", success="
				+ success + "]";
	}

}
