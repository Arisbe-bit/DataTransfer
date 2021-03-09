package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.SetIcpcodes;
import com.neoris.tcl.models.SetIcpcodesPK;

public interface ISetIcpcodesService {

	Optional<SetIcpcodes> findById(SetIcpcodesPK id);

	List<SetIcpcodes> findAll();

	SetIcpcodes save(SetIcpcodes entity);

	List<SetIcpcodes> saveAll(List<SetIcpcodes> entityList);

	void delete(SetIcpcodes entity);

	void deleteAll(List<SetIcpcodes> entityList);

}