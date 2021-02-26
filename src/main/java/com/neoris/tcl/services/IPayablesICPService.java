package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.model.PayablesICP;

public interface IPayablesICPService {

    Optional<PayablesICP> find(Long id);

    List<PayablesICP> findAll();

    PayablesICP save(PayablesICP entity);

    List<PayablesICP> saveAll(List<PayablesICP> entityList);

    void delete(PayablesICP entity);

}