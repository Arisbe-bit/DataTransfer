package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.SetReceivablesIcp;
import com.neoris.tcl.models.SetReceivablesIcpPK;

public interface ISetReceivablesIcpService {

	Optional<SetReceivablesIcp> findById(SetReceivablesIcpPK id);

	List<SetReceivablesIcp> findAll();

	SetReceivablesIcp save(SetReceivablesIcp entity);

	List<SetReceivablesIcp> saveAll(List<SetReceivablesIcp> entityList);
	
	void deleteAll(List<SetReceivablesIcp> entityList);

	void delete(SetReceivablesIcp entity);

}