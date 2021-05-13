package com.neoris.tcl.services;

import java.util.List;
import java.util.Map;

import com.neoris.tcl.models.ViewFFSSGrouped;

public interface IViewRollupFFSSGconsService {
	
	
	List<ViewFFSSGrouped> findAll();

	
	
	List<ViewFFSSGrouped> findByCompanyidAndHfmcode(String companyid,String hfmcode);
	
	List<ViewFFSSGrouped> findByCompanyid(Long companyid);
}
