package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.SetPayablesIcp;
import com.neoris.tcl.models.SetPayablesIcpPK;

public interface ISetPayablesIcpService {

	Optional<SetPayablesIcp> findById(SetPayablesIcpPK id);

	List<SetPayablesIcp> findAll();

	SetPayablesIcp save(SetPayablesIcp entity);

	List<SetPayablesIcp> saveAll(List<SetPayablesIcp> entityList);

	void delete(SetPayablesIcp entity);

}