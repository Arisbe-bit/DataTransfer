package com.neoris.tcl.admin.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.utils.ViewScope;

@Controller(value = "admincontroller")
@Scope(ViewScope.VIEW)
public class AdminController {
	private final static Logger LOG = LoggerFactory.getLogger(AdminController.class);
	private final static String REDIRECT = "%s?faces-redirect=true";
	private Authentication authentication;

	@PostConstruct
	public void init() {
		authentication = SecurityContextHolder.getContext().getAuthentication();		
//		LOG.info("Initializing Admin Controller...");
//		authentication.getAuthorities().forEach(ga -> LOG.info("Authority = {}", ga.getAuthority()));
	}
	
	public String users() {
		return String.format(REDIRECT, "/admin/users");		
	}
	
	/*public String roles() {
		return String.format(REDIRECT, "/admin/roles");		
	}	*/

	public String getName() {
		return authentication.getName();
	}
}
