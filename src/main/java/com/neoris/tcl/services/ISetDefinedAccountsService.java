package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.SetDefinedAccounts;
import com.neoris.tcl.models.SetDefinedAccountsPK;

public interface ISetDefinedAccountsService {
	
	
	Optional<SetDefinedAccounts> findById(SetDefinedAccountsPK id);

	List<SetDefinedAccounts> findAll();

	SetDefinedAccounts save(SetDefinedAccounts entity);

	List<SetDefinedAccounts> saveAll(List<SetDefinedAccounts> entityList);

	void delete(SetDefinedAccounts entity);

	void deleteAll(List<SetDefinedAccounts> entityList);

}
