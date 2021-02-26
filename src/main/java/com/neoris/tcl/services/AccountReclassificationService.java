package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IAccountReclassificationDao;
import com.neoris.tcl.model.AccountReclassification;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class AccountReclassificationService implements IAccountReclassificationService {

    @Autowired
    private IAccountReclassificationDao data;

    @Override
    public List<AccountReclassification> findAll() {
        return (List<AccountReclassification>) data.findAll();
    }

    @Override
    public Optional<AccountReclassification> findById(String id) {
        return data.findById(id);
    }

    @Override
    public AccountReclassification save(AccountReclassification entity) {
        return data.save(entity);
    }

    @Override
    public List<AccountReclassification> savaAll(List<AccountReclassification> entityList) {
        return (List<AccountReclassification>) data.saveAll(entityList);
    }

    @Override
    public void delete(AccountReclassification entity) {
        data.delete(entity);
    }

    @Override
    public void deleteAll(List<AccountReclassification> entityList) {
        data.deleteAll(entityList);
    }

}
