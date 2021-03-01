package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.model.ReceivablesICP;
import com.neoris.tcl.model.ReceivablesICPId;

public interface IReceivablesICPService {

	Optional<ReceivablesICP> find(ReceivablesICPId id);

	List<ReceivablesICP> findAll();

	ReceivablesICP save(ReceivablesICP entity);

	List<ReceivablesICP> saveAll(List<ReceivablesICP> entityList);

	void delete(ReceivablesICP entity);

}