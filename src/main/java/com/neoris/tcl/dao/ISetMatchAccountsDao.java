package com.neoris.tcl.dao;

import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.models.SetMatchAccounts;
import com.neoris.tcl.models.SetMatchAccountsPK;

public interface ISetMatchAccountsDao extends CrudRepository<SetMatchAccounts, SetMatchAccountsPK> {

}