package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.model.TradingPartnerTypes;

public interface ITradingPartnerTypesService {

    Optional<TradingPartnerTypes> find(String id);

    List<TradingPartnerTypes> findAll();

    TradingPartnerTypes save(TradingPartnerTypes entity);

    List<TradingPartnerTypes> saveAll(List<TradingPartnerTypes> entityList);

    void delete(TradingPartnerTypes entity);

}