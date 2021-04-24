package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.HfmFfSsHist;
import com.neoris.tcl.models.HfmFfssHistPK;


public interface IHfmFfssHistService {

	Optional<HfmFfSsHist> findById(HfmFfssHistPK id);
	
	List<HfmFfSsHist> findAll();

	HfmFfSsHist save(HfmFfSsHist entity);

	List<HfmFfSsHist> saveAll(List<HfmFfSsHist> entityList);

	void delete(HfmFfSsHist entity);
	
	List<HfmFfSsHist> findByCompanyIdAndPeriodid(Long companyId, String period);

}
