package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.HfmFfssDetails;
import com.neoris.tcl.models.HfmFfssDetailsPK;

public interface IHfmFfssDetailsService {

	Optional<HfmFfssDetails> findById(HfmFfssDetailsPK id);

	List<HfmFfssDetails> findAll();

	HfmFfssDetails save(HfmFfssDetails entity);

	List<HfmFfssDetails> saveAll(List<HfmFfssDetails> entityList);

	void delete(HfmFfssDetails entity);
	
	void deleteAll(List<HfmFfssDetails> entityList);
	
	List<HfmFfssDetails> findByCompanyIdAndHfmcodeAndPeriod(Long companyId, String hfmcode, String period);

}