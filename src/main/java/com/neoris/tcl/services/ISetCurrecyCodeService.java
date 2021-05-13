package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.SetCurrecyCode;


public interface ISetCurrecyCodeService {

	Optional<SetCurrecyCode> findById(String id);

	List<SetCurrecyCode> findAll();

	SetCurrecyCode save(SetCurrecyCode entity);
	
	List<SetCurrecyCode> saveAll(List<SetCurrecyCode> entityList);

	void delete(SetCurrecyCode entity);
	
	void deleteAll(List<SetCurrecyCode> entityList);
}
