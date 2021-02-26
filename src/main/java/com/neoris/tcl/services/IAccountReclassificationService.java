package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.model.AccountReclassification;

public interface IAccountReclassificationService {
    
    public List<AccountReclassification> findAll();
    public Optional<AccountReclassification> findById(String id);
    public AccountReclassification save(AccountReclassification entity);
    public List<AccountReclassification> savaAll(List<AccountReclassification> entityList);    
    public void delete(AccountReclassification entity);
    public void deleteAll(List<AccountReclassification> entityList);
    
}
