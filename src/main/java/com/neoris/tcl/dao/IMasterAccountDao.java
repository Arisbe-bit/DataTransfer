package com.neoris.tcl.dao;

import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.model.MasterAccount;

public interface IMasterAccountDao extends CrudRepository<MasterAccount, String> {

}
