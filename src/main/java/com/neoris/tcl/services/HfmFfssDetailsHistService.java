package com.neoris.tcl.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmFfssDetalsHistDao;
import com.neoris.tcl.models.HfmFFSSDetailsHist;


@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmFfssDetailsHistService implements IHfmFfssDetailsHistService{
	private final static Logger LOG = LoggerFactory.getLogger(HfmFfssDetailsHistService.class);

	@Autowired
	private IHfmFfssDetalsHistDao data;

	@Override
	public List<HfmFFSSDetailsHist> findAll() {
		return (List<HfmFFSSDetailsHist> ) data.findAll();
	}

	@Override
	public List<HfmFFSSDetailsHist> findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndPeriodid(
			int companyId, String Hfmcode, String costcenter, String accountid, String partnerid,String periodnm) {
		LOG.info("finddatadet: companyId = {}, periodnm = {}, Hfmcode = {}, costcenter{} ,accountid {}", companyId, periodnm, Hfmcode,costcenter,accountid);
		
		return (List<HfmFFSSDetailsHist> ) data.findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndPeriodid(companyId, Hfmcode, costcenter,
				accountid, partnerid,periodnm);
	}
	@Override
	public List<HfmFFSSDetailsHist> findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndIdPeriodnm(
			int companyId, String Hfmcode, String costcenter, String accountid, String partnerid,String periodnm) {
		LOG.info("finddatadet: companyId = {}, periodnm = {}, Hfmcode = {}, costcenter{} ,accountid {}", companyId, periodnm, Hfmcode,costcenter,accountid);
		
		return (List<HfmFFSSDetailsHist> ) data.findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartneridAndIdPeriodnm(companyId, Hfmcode, costcenter,
				accountid, partnerid,periodnm);
	}
	
	
	

}
