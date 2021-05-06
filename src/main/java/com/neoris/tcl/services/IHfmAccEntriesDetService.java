package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;


import com.neoris.tcl.models.HfmAccEntriesDet;


public interface IHfmAccEntriesDetService {

   List<HfmAccEntriesDet> findAll();
	
	List<HfmAccEntriesDet> findByItemid(Long itemid);
	
	Optional<HfmAccEntriesDet> findById(Long id);

	HfmAccEntriesDet save(HfmAccEntriesDet entity);

	List<HfmAccEntriesDet> saveAll(List<HfmAccEntriesDet> entityList);

	void delete(HfmAccEntriesDet entity);

	void deleteAll(List<HfmAccEntriesDet> entityList);
	
}
