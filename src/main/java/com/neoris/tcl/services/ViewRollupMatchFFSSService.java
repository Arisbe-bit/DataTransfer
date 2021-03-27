package com.neoris.tcl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.models.ViewRollupMacthFFSS;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewRollupMatchFFSSService implements IViewRollupMatchFFSSService{

	
	@Autowired
	private IViewRollupMatchFFSSService data;

	@Override
	public List<ViewRollupMacthFFSS> findByCompanyid(int companyid) {
		return data.findByCompanyid(companyid);
	}
	

	
}
