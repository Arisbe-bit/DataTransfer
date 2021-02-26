package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.model.TradingPartner;

public interface ITradingPartnerService {

    Optional<TradingPartner> find(String id);

    List<TradingPartner> findAll();

    TradingPartner save(TradingPartner entity);

    List<TradingPartner> saveAll(List<TradingPartner> entityList);

    void delete(TradingPartner entity);

}