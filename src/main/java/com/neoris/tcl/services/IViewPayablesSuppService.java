package com.neoris.tcl.services;

import java.util.List;


import com.neoris.tcl.models.ViewPayablesSupp;

public interface IViewPayablesSuppService {
	
	List<ViewPayablesSupp> findAll();
	
	List<ViewPayablesSupp> findByOrganizationid(int organizationid);
	
}
