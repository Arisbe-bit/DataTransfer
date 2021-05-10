package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.HfmAccEntries;

public interface IHfmAccEntriesService {

	List<HfmAccEntries> findAll();
	
	List<HfmAccEntries> findByCompanyid(int companyid);
	
	Optional<HfmAccEntries> findById(Long id);

	HfmAccEntries save(HfmAccEntries entity);

	List<HfmAccEntries> saveAll(List<HfmAccEntries> entityList);

	void delete(HfmAccEntries entity);

	void deleteAll(List<HfmAccEntries> entityList);
	
	void rollUpApplyEntries(int p_orgid, String p_periodnm, String p_userid, int p_itemid);
	
	
}
