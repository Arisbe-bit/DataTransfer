package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.SetMatchAccounts;
import com.neoris.tcl.models.SetMatchAccountsPK;


public interface ISetMatchAccountsService {

	Optional<SetMatchAccounts> findById(SetMatchAccountsPK id);

	List<SetMatchAccounts> findAll();

	SetMatchAccounts save(SetMatchAccounts entity);

	List<SetMatchAccounts> saveAll(List<SetMatchAccounts> entityList);

	void delete(SetMatchAccounts entity);
	
	void deleteAll(List<SetMatchAccounts> entityList);
}