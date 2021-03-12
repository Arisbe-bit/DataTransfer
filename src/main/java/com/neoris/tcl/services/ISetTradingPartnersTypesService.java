package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.SetTradingPartnersTypes;

public interface ISetTradingPartnersTypesService {

	Optional<SetTradingPartnersTypes> findById(String id);

	List<SetTradingPartnersTypes> findAll();

	SetTradingPartnersTypes save(SetTradingPartnersTypes entity);

	List<SetTradingPartnersTypes> saveAll(List<SetTradingPartnersTypes> entityList);

	void delete(SetTradingPartnersTypes entity);

	void deleteAll(List<SetTradingPartnersTypes> entityList);

}