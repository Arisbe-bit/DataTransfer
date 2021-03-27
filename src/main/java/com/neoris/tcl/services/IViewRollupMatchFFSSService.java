package com.neoris.tcl.services;

import java.util.List;

import com.neoris.tcl.models.ViewRollupMacthFFSS;

public interface IViewRollupMatchFFSSService {
	
	
	List<ViewRollupMacthFFSS> findByCompanyid(int companyid);

}
