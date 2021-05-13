package com.neoris.tcl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IhfmPeriodFfssDao;
import com.neoris.tcl.models.HfmPeriodFfss;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class HfmPeriodFfssService implements IHfmPeriodFfssService {

	@Autowired
	private IhfmPeriodFfssDao data;

	@Override
	public List<HfmPeriodFfss> findByCompanyid(int companyid) {
		return (List<HfmPeriodFfss>) data.findByCompanyid(companyid);
	}

	@Override
	public List<HfmPeriodFfss> findAll() {
		return (List<HfmPeriodFfss>) data.findAll();
	}

}
