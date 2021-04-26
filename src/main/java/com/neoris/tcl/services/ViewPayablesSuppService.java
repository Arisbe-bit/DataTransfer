package com.neoris.tcl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ViewPayablesSupliersDao;
import com.neoris.tcl.models.ViewPayablesSupp;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewPayablesSuppService implements IViewPayablesSuppService{

	
	@Autowired
	private ViewPayablesSupliersDao data;
	
	@Override
	public List<ViewPayablesSupp> findAll() {
		return (List<ViewPayablesSupp>) data.findAll();
	}

	@Override
	public List<ViewPayablesSupp> findByOrganizationid(int organizationid) {
		return data.findByOrganizationid(organizationid);
	}
	

}
