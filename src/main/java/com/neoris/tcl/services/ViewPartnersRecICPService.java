package com.neoris.tcl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IViewPartnersRecICPDao;
import com.neoris.tcl.models.ViewPartnersRecICP;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewPartnersRecICPService implements IViewPartnersRecICPService {

	@Autowired
	private IViewPartnersRecICPDao data;

	@Override
	public List<ViewPartnersRecICP> findAll() {
		return (List<ViewPartnersRecICP>) data.findAll();
	}

}
