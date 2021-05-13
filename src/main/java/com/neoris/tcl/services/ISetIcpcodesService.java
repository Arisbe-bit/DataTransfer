package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.SetIcpcodes;


public interface ISetIcpcodesService {

	Optional<SetIcpcodes> findById(String id);

	List<SetIcpcodes> findAll();

	SetIcpcodes save(SetIcpcodes entity);

	List<SetIcpcodes> saveAll(List<SetIcpcodes> entityList);

	void delete(SetIcpcodes entity);

	void deleteAll(List<SetIcpcodes> entityList);
	
	List<SetIcpcodes> findByTptype(String tptype);

}