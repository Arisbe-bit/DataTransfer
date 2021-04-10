package com.neoris.tcl.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmLayoutHistDao;
import com.neoris.tcl.models.HfmLayoutHist;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmLayoutHistService implements IHfmLayoutHistService{

	
	

	@Autowired
	private IHfmLayoutHistDao data;
	
	@Override
	public List<HfmLayoutHist> findAll() {
		return (List<HfmLayoutHist>) data.findAll();
	}

	@Override
	public List<HfmLayoutHist> findByIdCompanyid(int companyid) {
		
		return data.findByIdCompanyid(companyid);
	}

	
}
