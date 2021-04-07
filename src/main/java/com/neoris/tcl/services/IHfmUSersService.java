package com.neoris.tcl.services;
import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.HfmUsers;


public interface IHfmUSersService {

	Optional<HfmUsers> findById(String id);

	List<HfmUsers> findAll();

	HfmUsers save(HfmUsers entity);

	List<HfmUsers> saveAll(List<HfmUsers> entityList);

	void delete(HfmUsers entity);

	void deleteAll(List<HfmUsers> entityList);
	
}
