package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.neoris.tcl.model.TradingPartnerTypes;

public interface ITradingPartnerTypesService {


	List<TradingPartnerTypes> listar();
    Page<TradingPartnerTypes> findPaginated(Pageable pageable, List<TradingPartnerTypes> tradingTypesList);
    TradingPartnerTypes save(TradingPartnerTypes tptype);
    void delete(TradingPartnerTypes tptype);
    void deleteAll(List<TradingPartnerTypes> lstTPtype);
    Optional<TradingPartnerTypes> listarID(String tptype);

}