package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.SetReclassifAccounts;
import com.neoris.tcl.models.SetReclassifAccountsPK;


public interface ISetReclassifAccountsService {

	Optional<SetReclassifAccounts> findById(SetReclassifAccountsPK id);

	List<SetReclassifAccounts> findAll();

	SetReclassifAccounts save(SetReclassifAccounts entity);

	List<SetReclassifAccounts> saveAll(List<SetReclassifAccounts> entityList);

	void delete(SetReclassifAccounts entity);
	
	void deleteAll(List<SetReclassifAccounts> entityList);

}