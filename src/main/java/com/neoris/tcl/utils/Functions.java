package com.neoris.tcl.utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.neoris.tcl.security.models.User;

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
    
    public static String cryptPassword(String password) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String password[] = { "Neoris2021", "Cemex2021", "Mor#7310", "12345" };
		//for (int i = 0; i < password.length; i++)
		//	System.out.println( String.format("%s = %s", password[i], passwordEncoder.encode(password[i])) );
    	return passwordEncoder.encode(password);
    }
    
    public static User getUser() {
    	Authentication authentication;
    	User user = null;
		authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal() instanceof User) {
			user = (User) authentication.getPrincipal();
		}
		return user;
    }
}
