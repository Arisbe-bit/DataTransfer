package com.neoris.tcl.services;

import java.util.List;

import com.neoris.tcl.models.HfmPeriodFfss;

public interface IHfmPeriodFfssService {
	List<HfmPeriodFfss> findByCompanyid(int companyid) ;
	List<HfmPeriodFfss> findAll();

}
