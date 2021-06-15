package com.neoris.tcl.services;

import java.util.List;

import com.neoris.tcl.models.HfmFFSSDetailsHist;


public interface IHfmFfssDetailsHistService {
	
	List<HfmFFSSDetailsHist> findAll();
	
	 public List<HfmFFSSDetailsHist> findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndPeriodid(int companyId, String Hfmcode,String costcenter,String accountid, String partnerid, String periodnm);
	 public List<HfmFFSSDetailsHist> findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndIdPeriodnm(int companyId, String Hfmcode,String costcenter,String accountid, String partnerid, String periodnm);	 
	 
}
