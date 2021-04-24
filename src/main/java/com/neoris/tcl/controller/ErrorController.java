package com.neoris.tcl.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	        	LOG.info("Recibí no encontrado. uri = {}", uri);
	            return "/404.xhtml";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	LOG.info("Recibí un error. uri = {}", uri);
	            return "/500.xhtml";
	        }
	    }
		
		return "/error.xhtml";
	}

	@RequestMapping(value = "/login-error", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
		LOG.info("login error detected.");
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
                LOG.info("errorMessage = {}", errorMessage);
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        String url = "/login.xhtml?error=true&cause="+errorMessage; 
        return url;
    }
}
