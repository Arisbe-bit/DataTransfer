package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.HfmRollupExceptions;

public interface IHfmRollupExceptionsService {


	List<HfmRollupExceptions> findAll();

	Optional<HfmRollupExceptions> findById(Long id);

	HfmRollupExceptions save(HfmRollupExceptions entity);

	List<HfmRollupExceptions> saveAll(List<HfmRollupExceptions> entityList);

	void delete(HfmRollupExceptions entity);

	void deleteAll(List<HfmRollupExceptions> entityList);
}
