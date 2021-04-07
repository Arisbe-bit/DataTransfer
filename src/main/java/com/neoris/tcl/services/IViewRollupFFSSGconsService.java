package com.neoris.tcl.services;

import java.util.List;

import com.neoris.tcl.models.ViewFFSSGrouped;

public interface IViewRollupFFSSGconsService {
	
	
	List<ViewFFSSGrouped> findAll();

	 List<ViewFFSSGrouped> findByCompanyidAndhfmparentAndhfmcode(Long companyId, String hfmparent,String hfmcode);
}
