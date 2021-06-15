package com.neoris.tcl.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmOracleAccDao;
import com.neoris.tcl.models.HfmOracleAcc;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmOracleAccService  implements IHfmOracleAccService{

	private final static Logger LOG = LoggerFactory.getLogger(HfmOracleAccService.class);

	
	@Autowired
	private IHfmOracleAccDao data;
	
	@Override
	public List<HfmOracleAcc> findAll() {
		return (List<HfmOracleAcc> ) data.findAll();
	}

	@Override
	public List<HfmOracleAcc> findByOrgidAndCostcenter(int Orgid, String costcenter) {
		return data.findByOrgidAndCostcenter(Orgid, costcenter);
	}
	
	
	@Override
	public List<HfmOracleAcc> findByOrgid(int Orgid) {
		return data.findByOrgid(Orgid);
	}
	
	public void rollUprefresh() {
		try {
			data.rollUprefresh();
		} catch (Exception e) {
			LOG.error("Error al correr HfmOracleAccService.rollUprefresh: => {}", e.getMessage());
		}
	}

}
