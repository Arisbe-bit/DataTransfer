package com.neoris.tcl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmAllPeriodsDao;
import com.neoris.tcl.models.HfmAllPeriods;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmAllPeriodsService implements IHfmAllPeriodsService{
	@Autowired
	private IHfmAllPeriodsDao data;
	
	@Override
	public List<HfmAllPeriods> findAll() {
		return (List<HfmAllPeriods> ) data.findAll();
	}

}
