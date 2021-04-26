package com.neoris.tcl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmFfssDetalsHistDao;
import com.neoris.tcl.models.HfmFFSSDetailsHist;


@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmFfssDetailsHistService implements IHfmFfssDetailsHistService{
	

	@Autowired
	private IHfmFfssDetalsHistDao data;

	@Override
	public List<HfmFFSSDetailsHist> findAll() {
		return (List<HfmFFSSDetailsHist> ) data.findAll();
	}

	@Override
	public List<HfmFFSSDetailsHist> findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndPeriodid(
			int companyId, String Hfmcode, String costcenter, String accountid, String partnerid,String periodnm) {
		return (List<HfmFFSSDetailsHist> ) data.findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndPeriodid(companyId, Hfmcode, costcenter,
				accountid, partnerid,periodnm);
	}
	@Override
	public List<HfmFFSSDetailsHist> findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndIdPeriodnm(
			int companyId, String Hfmcode, String costcenter, String accountid, String partnerid,String periodnm) {
		return (List<HfmFFSSDetailsHist> ) data.findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndIdPeriodnm(companyId, Hfmcode, costcenter,
				accountid, partnerid,periodnm);
	}
	
	
	

}
