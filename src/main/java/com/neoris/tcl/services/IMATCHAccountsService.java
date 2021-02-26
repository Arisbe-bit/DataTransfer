package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.model.MATCHAccounts;

public interface IMATCHAccountsService {

    Optional<MATCHAccounts> find(String id);
    List<MATCHAccounts> findAll();
    MATCHAccounts save(MATCHAccounts entity);
    List<MATCHAccounts> saveAll(List<MATCHAccounts> entityList);
    void delete(MATCHAccounts entity);

}