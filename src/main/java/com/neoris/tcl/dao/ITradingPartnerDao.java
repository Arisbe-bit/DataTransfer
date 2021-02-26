package com.neoris.tcl.dao;

import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.model.TradingPartner;

public interface ITradingPartnerDao extends CrudRepository<TradingPartner, String> {

}
