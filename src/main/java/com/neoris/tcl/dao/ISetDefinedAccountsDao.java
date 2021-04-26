package com.neoris.tcl.dao;

import org.springframework.data.repository.CrudRepository;


import com.neoris.tcl.models.SetDefinedAccounts;
import com.neoris.tcl.models.SetDefinedAccountsPK;

public interface ISetDefinedAccountsDao  extends CrudRepository<SetDefinedAccounts,SetDefinedAccountsPK>{

}
