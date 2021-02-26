package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.model.MasterAccount;

public interface IMasterAccountService {

    public List<MasterAccount> findAll();

    public Optional<MasterAccount> findById(String id);

    public void save(MasterAccount masterAccount);

    public void savaAll(List<MasterAccount> list);
    
    public void delete(MasterAccount masterAccount);

}