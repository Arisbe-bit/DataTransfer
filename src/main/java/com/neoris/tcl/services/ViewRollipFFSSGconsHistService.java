package com.neoris.tcl.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IViewRollupFFSSGconsHistDao;
import com.neoris.tcl.models.ViewFFSSGroupedHist;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewRollipFFSSGconsHistService  implements IViewRollupFFSSGconsHistService{
	
	@Autowired
	private IViewRollupFFSSGconsHistDao data;
	
	@Override
	public List<ViewFFSSGroupedHist> findAll() {
		return (List<ViewFFSSGroupedHist>) data.findAll();
	}



	
	@Override
	public List<ViewFFSSGroupedHist> findByCompanyidAndHfmcode(int companyid,  String hfmcode) {
		  return data.findByCompanyidAndHfmcode(companyid,  hfmcode);
	}
	

}
