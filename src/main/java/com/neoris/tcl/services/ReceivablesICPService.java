package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IReceivablesICPDao;
import com.neoris.tcl.model.ReceivablesICP;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ReceivablesICPService implements IReceivablesICPService {

    @Autowired
    private IReceivablesICPDao data;

    @Override
    public Optional<ReceivablesICP> find(Long id) {
        return data.findById(id);
    }

    @Override
    public List<ReceivablesICP> findAll() {
        return (List<ReceivablesICP>) data.findAll();
    }

    @Override
    public ReceivablesICP save(ReceivablesICP entity) {
        return data.save(entity);
    }

    @Override
    public List<ReceivablesICP> saveAll(List<ReceivablesICP> entityList) {
        return (List<ReceivablesICP>) data.saveAll(entityList);
    }

    @Override
    public void delete(ReceivablesICP entity) {
        data.delete(entity);
    }

}
