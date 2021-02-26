package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IMasterAccountDao;
import com.neoris.tcl.model.MasterAccount;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service
public class MasterAccountService implements IMasterAccountService {
    
    @Autowired
    IMasterAccountDao data;
    
    @Override
    public List<MasterAccount> findAll() {        
        List<MasterAccount> list = (List<MasterAccount>) data.findAll();        
        return list;        
    }
    
    @Override
    public Optional<MasterAccount> findById(String id) {
        return data.findById(id);
    }
    
    @Override
    public void save(MasterAccount masterAccount) {
        data.save(masterAccount);
    }
    @Override
    public void savaAll(List<MasterAccount> list) {
        data.saveAll(list);
    }
    @Override
    public void delete(MasterAccount masterAccount) {
        data.delete(masterAccount);
    }

}
