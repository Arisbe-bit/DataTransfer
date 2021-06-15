package com.neoris.tcl.services;

import java.util.List;

import com.neoris.tcl.models.HfmOracleAcc;



public interface IHfmOracleAccService {
	
	List <HfmOracleAcc> findAll();
	List<HfmOracleAcc> findByOrgidAndCostcenter(int Orgid, String costcenter);
	List<HfmOracleAcc> findByOrgid(int Orgid);
	
	void rollUprefresh();


}
