package com.neoris.tcl.services;

import java.util.List;

import com.neoris.tcl.dao.IhfmPeriodFfssDao;
import com.neoris.tcl.models.HfmPeriodFfss;


public class HfmPeriodFfssService implements IHfmPeriodFfssService{

private IhfmPeriodFfssDao data;
	
	@Override
	public List<HfmPeriodFfss> findByCompanyid(int companyid) {
		return (List<HfmPeriodFfss>)  data.findByCompanyid(companyid);
	}

	@Override
	public List<HfmPeriodFfss> findAll() {
		// 
		return (List<HfmPeriodFfss>) data.findAll();
	}


}
