package com.neoris.tcl.services;

import java.util.List;


import com.neoris.tcl.models.HfmLayoutHist;



public interface IHfmLayoutHistService {
	
	List<HfmLayoutHist> findAll();
	
	List<HfmLayoutHist> findByIdCompanyid(int companyid);
	

}
