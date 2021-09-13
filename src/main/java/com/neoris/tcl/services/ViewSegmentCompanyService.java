package com.neoris.tcl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;


import com.neoris.tcl.dao.IViewSegmentCompanyDao;
import com.neoris.tcl.models.ViewSegmentCompany;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewSegmentCompanyService implements IViewSegmentCompanyService{

	@Autowired
	private IViewSegmentCompanyDao data;
	
	@Override
	public List<ViewSegmentCompany> findAll() {		
		return (List<ViewSegmentCompany>) data.findAll();
	}

}
