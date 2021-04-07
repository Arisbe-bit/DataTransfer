package com.neoris.tcl.services;

import java.util.List;

import javax.swing.text.ViewFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;


import com.neoris.tcl.dao.IViewRollupFFSSGconsDao;
import com.neoris.tcl.models.ViewFFSSGrouped;


@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewRollipFFSSGconsService implements IViewRollupFFSSGconsService{


	@Autowired
	private IViewRollupFFSSGconsDao data;
	
	@Override
	public List<ViewFFSSGrouped> findAll() {
		return (List<ViewFFSSGrouped>) data.findAll();
	}

	@Override
	public List<ViewFFSSGrouped> findByCompanyidAndhfmparentAndhfmcode(Long companyId, String hfmparent, String hfmcode) {
		  return data.findByCompanyidAndhfmparentAndhfmcode(companyId, hfmparent,  hfmcode);
	}
	
	

}
