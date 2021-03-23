package com.neoris.tcl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IViewPartnersICPDao;
import com.neoris.tcl.models.ViewPartnersICP;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewPartnersICPService implements IViewPartnersICPService{

	@Autowired
	private IViewPartnersICPDao data;
	
	@Override
	public List<ViewPartnersICP> findAll() {

		return  (List<ViewPartnersICP>) data.findAll();
	}

}
