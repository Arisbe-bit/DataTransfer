package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.HfmFfss;
import com.neoris.tcl.models.HfmFfssPK;

public interface IHfmFfssService {

	Optional<HfmFfss> findById(HfmFfssPK id);

	List<HfmFfss> findAll();

	HfmFfss save(HfmFfss entity);

	List<HfmFfss> saveAll(List<HfmFfss> entityList);

	void delete(HfmFfss entity);
	
	List<HfmFfss> findByCompanyIdAndPeriod(Long companyId, String period);

}