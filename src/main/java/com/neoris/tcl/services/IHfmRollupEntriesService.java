package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.models.SetHfmCodes;

public interface IHfmRollupEntriesService {

	Optional<HfmRollupEntries> findById(Long id);

	List<HfmRollupEntries> findAll();

	HfmRollupEntries save(HfmRollupEntries entity);

	List<HfmRollupEntries> saveAll(List<HfmRollupEntries> entityList);

	void delete(HfmRollupEntries entity);
	
	 void deleteAll(List<HfmRollupEntries> entityList) ;
		
}