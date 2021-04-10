package com.neoris.tcl.services;

import java.util.List;


import com.neoris.tcl.models.ViewFFSSGroupedHist;



public interface IViewRollupFFSSGconsHistService {

	
	List<ViewFFSSGroupedHist> findAll();

	
	
	List<ViewFFSSGroupedHist> findByCompanyidAndHfmcode(int companyid,String hfmcode);
}
