package com.neoris.tcl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ViewCustReceivablesDao;
import com.neoris.tcl.models.ViewCustReceivables;



@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewCustReceivablesService implements IViewCustReceivablesService{
	
	@Autowired
	private ViewCustReceivablesDao data;

	@Override
	public List<ViewCustReceivables> findAll() {
		return  (List<ViewCustReceivables>) data.findAll();
	};

}
