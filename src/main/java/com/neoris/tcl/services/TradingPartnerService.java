package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ITradingPartnerDao;
import com.neoris.tcl.model.TradingPartner;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class TradingPartnerService implements ITradingPartnerService {

    @Autowired
    private ITradingPartnerDao data;
    
    @Override
    public Optional<TradingPartner> find(String id) {
        return data.findById(id);
    }

    @Override
    public List<TradingPartner> findAll() {
        return (List<TradingPartner>) data.findAll();
    }

    @Override
    public TradingPartner save(TradingPartner entity) {
        return data.save(entity);
    }

    @Override
    public List<TradingPartner> saveAll(List<TradingPartner> entityList) {
        return (List<TradingPartner>) data.saveAll(entityList);
    }

    @Override
    public void delete(TradingPartner entity) {
        data.delete(entity);
    }

}
