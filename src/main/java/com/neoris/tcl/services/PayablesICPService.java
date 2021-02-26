package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IPayablesICPDao;
import com.neoris.tcl.model.PayablesICP;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class PayablesICPService implements IPayablesICPService{

    @Autowired
    private IPayablesICPDao data; 
    
    @Override
    public Optional<PayablesICP> find(Long id) {
        return data.findById(id);
    }

    @Override
    public List<PayablesICP> findAll() {
        return (List<PayablesICP>) data.findAll();
    }

    @Override
    public PayablesICP save(PayablesICP entity) {
        return data.save(entity);
    }

    @Override
    public List<PayablesICP> saveAll(List<PayablesICP> entityList) {
        return (List<PayablesICP>) data.saveAll(entityList);
    }

    @Override
    public void delete(PayablesICP entity) {
        data.delete(entity);        
    }

}
