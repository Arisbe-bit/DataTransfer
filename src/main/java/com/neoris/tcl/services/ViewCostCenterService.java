package com.neoris.tcl.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IViewCostCenterDao;
import com.neoris.tcl.models.ViewCostCenter;


@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewCostCenterService implements IViewCostCenterService{
	private final static Logger LOG = LoggerFactory.getLogger(ViewCostCenterService.class);
	
	@Autowired
	private IViewCostCenterDao data;

	@Override
	public List<ViewCostCenter> findAll() {
		LOG.info("costcenter findall");
		
			return (List<ViewCostCenter>) data.findAll();
		
	}

	
	

}
