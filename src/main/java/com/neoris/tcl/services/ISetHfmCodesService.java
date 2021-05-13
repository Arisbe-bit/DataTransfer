package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.SetHfmCodes;

public interface ISetHfmCodesService {

	Optional<SetHfmCodes> findById(String id);

	List<SetHfmCodes> findAll();

	SetHfmCodes save(SetHfmCodes entity);

	List<SetHfmCodes> saveAll(List<SetHfmCodes> entityList);

	void delete(SetHfmCodes entity);

	void deleteAll(List<SetHfmCodes> entityList);
	
	List<SetHfmCodes> findByTptype(String tptype);

}