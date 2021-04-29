package com.neoris.tcl.services;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IViewCostCenterDao;
import com.neoris.tcl.models.ViewCostCenter;


@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewCostCenterService implements IViewCostCenterService{
	
	IViewCostCenterDao data;

	@Override
	public List<ViewCostCenter> findAll() {
		// TODO Auto-generated method stub
		return (List<ViewCostCenter>) data.findAll();
	}

	
	

}
