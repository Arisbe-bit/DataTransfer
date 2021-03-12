package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.SetMasterAccounts;

public interface ISetMasterAccountsService {

	Optional<SetMasterAccounts> findById(String id);

	List<SetMasterAccounts> findAll();

	SetMasterAccounts save(SetMasterAccounts entity);

	List<SetMasterAccounts> saveAll(List<SetMasterAccounts> entityList);

	void delete(SetMasterAccounts entity);

}