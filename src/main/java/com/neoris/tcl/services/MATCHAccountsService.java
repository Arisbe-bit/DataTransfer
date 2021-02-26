package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IMATCHAccountsDao;
import com.neoris.tcl.model.MATCHAccounts;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class MATCHAccountsService implements IMATCHAccountsService {

    @Autowired
    private IMATCHAccountsDao data;
    
    @Override
    public Optional<MATCHAccounts> find(String id) {
       return data.findById(id);
    }

    @Override
    public MATCHAccounts save(MATCHAccounts entity) {
        MATCHAccounts retval = data.save(entity);
        return retval;
    }

    @Override
    public void delete(MATCHAccounts entity) {
        data.delete(entity);        
    }

    @Override
    public List<MATCHAccounts> findAll() {
        return (List<MATCHAccounts>) data.findAll();
    }

    @Override
    public List<MATCHAccounts> saveAll(List<MATCHAccounts> entityList) {
        return (List<MATCHAccounts>) data.saveAll(entityList);
    }

}
