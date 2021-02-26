package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ITradingPartnerTypesDao;
import com.neoris.tcl.model.TradingPartnerTypes;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class TradingPartnerTypesService implements ITradingPartnerTypesService{

    @Autowired
    private ITradingPartnerTypesDao data;
    
    @Override
    public Optional<TradingPartnerTypes> find(String id) {
        return data.findById(id);
    }

    @Override
    public List<TradingPartnerTypes> findAll() {
        return (List<TradingPartnerTypes>) data.findAll();
    }

    @Override
    public TradingPartnerTypes save(TradingPartnerTypes entity) {
          return data.save(entity);
    }

    @Override
    public List<TradingPartnerTypes> saveAll(List<TradingPartnerTypes> entityList) {
        return (List<TradingPartnerTypes>) data.saveAll(entityList);
    }

    @Override
    public void delete(TradingPartnerTypes entity) {
        data.delete(entity);        
    }

}
