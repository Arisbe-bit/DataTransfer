package com.neoris.tcl.utils;

import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("request")
public class ErrorHandler implements ErrorController {

	private final static Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);

	// @RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        if (status != null) {
//            Integer statusCode = Integer.valueOf(status.toString());
//            if (statusCode == HttpStatus.NOT_FOUND.value()) {
//                return "error-404";
//            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                LOG.error("Status Code    = {}", this.getStatusCode());

		// request.getServletContext().getAttributeNames()
		LOG.error("Message        = {}", this.getMessage());
		LOG.error("Exception Type = {}", this.getExceptionType());
		LOG.error("Exception      = {}", this.getException());
		LOG.error("Servlet Name   = {}", this.getRequestURI());
		LOG.error("Request URI    = {}", this.getServletName());
		return "500";

	}

	public String getStatusCode() {
		String val = "";
		try {
			val = String.valueOf((Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get("javax.servlet.error.status_code"));
		} catch (Exception e) {
			LOG.error("Error (getStatusCode): {}", e.getMessage());
		}
		return val;
	}

	public String getMessage() {
		String val = "";
		try {
			val = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get("javax.servlet.error.message");
		} catch (Exception e) {
			LOG.error("Error: (getMessage) {}", e.getMessage());
		}
		return val;
	}

	public String getExceptionType() {
		String val = "";
		try {
			val = FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get("javax.servlet.error.exception_type").toString();
		} catch (Exception e) {
			LOG.error("Error (getExceptionType): {}", e.getMessage());
		}
		return val;
	}

	public String getException() {
		String val = "";
		try {
			val = (String) ((Exception) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get("javax.servlet.error.exception")).toString();
		} catch (Exception e) {
			LOG.error("Error (getException): {}", e.getMessage());
		}
		return val;
	}

	public String getRequestURI() {
		String val = "";
		try {
			val = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get("javax.servlet.error.request_uri");
		} catch (Exception e) {
			LOG.error("Error (getException): {}", e.getMessage());
		}
		return val;
	}

	public String getServletName() {
		String val = "";
		try {
			val = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
					.get("javax.servlet.error.servlet_name");
		} catch (Exception e) {
			LOG.error("Error: {}", e.getMessage());
		}
		return val;
	}

	@Override
	public String getErrorPath() {
		return null;
	}

}
