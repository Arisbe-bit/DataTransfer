package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.models.HfmFFSSDetailsHist;
import com.neoris.tcl.models.HfmFfssDetailsHistPK;



public interface IHfmFfssDetalsHistDao extends CrudRepository<HfmFFSSDetailsHist, HfmFfssDetailsHistPK> {
	
	   public List<HfmFFSSDetailsHist> findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndPeriodid(int companyId, String Hfmcode,String costcenter,String accountid, String partnerid, String periodnm);
	   public List<HfmFFSSDetailsHist> findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndIdPeriodnm(int companyId, String Hfmcode,String costcenter,String accountid, String partnerid, String periodnm);
	   

}
