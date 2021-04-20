package com.neoris.tcl.services;

import java.util.List;

import com.neoris.tcl.models.HfmFFSSDetailsHist;


public interface IHfmFfssDetailsHistService {
	
	List<HfmFFSSDetailsHist> findAll();
	
	 public List<HfmFFSSDetailsHist> findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartnerid(int companyId, String Hfmcode,String costcenter,String accountid, String partnerid);

}
