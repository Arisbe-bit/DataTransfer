package com.neoris.tcl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmOracleAccDao;
import com.neoris.tcl.models.HfmOracleAcc;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmOracleAccService  implements IHfmOracleAccService{

	@Autowired
	private IHfmOracleAccDao data;
	
	@Override
	public List<HfmOracleAcc> findAll() {
		return (List<HfmOracleAcc> ) data.findAll();
	}
	
	

}
