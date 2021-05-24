package com.neoris.tcl.beans;

public class RollUpMessage {

	private String id;
	private String iClass;
	private String spanClass;
	private String message;

	public RollUpMessage() {
	}

	public RollUpMessage(String id, String iClass, String spanClass, String message) {
		super();
		this.id = id;
		this.iClass = iClass;
		this.spanClass = spanClass;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getiClass() {
		return iClass;
	}

	public void setiClass(String iClass) {
		this.iClass = iClass;
	}

	public String getSpanClass() {
		return spanClass;
	}

	public void setSpanClass(String spanClass) {
		this.spanClass = spanClass;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("RollUpMessage [id=%s, iClass=%s, spanClass=%s, message=%s]", id, iClass, spanClass,
				message);
	}

}
