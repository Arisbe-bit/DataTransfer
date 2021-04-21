package com.neoris.tcl.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {
	
	private final static Logger LOG = LoggerFactory.getLogger(ErrorController.class);
	
	public ErrorController() {}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String handleError(HttpServletRequest request) {
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		String uri = request.getScheme() + "://" +   // "http" + "://
	             request.getServerName() +       // "myhost"
	             ":" +                           // ":"
	             request.getServerPort() +       // "8080"
	             request.getRequestURI() +       // "/people"
	             "?" +                           // "?"
	             request.getQueryString();       // "lastname=Fox&age=30"
		
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	LOG.debug("Recibí no encontrado. uri = {}", uri);
	            return "/404.xhtml";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	LOG.debug("Recibí un error. uri = {}", uri);
	            return "/500.xhtml";
	        }
	    }
		
		return "/error.xhtml";
	}

}
