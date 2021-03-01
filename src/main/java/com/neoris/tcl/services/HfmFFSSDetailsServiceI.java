package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.model.HfmFFSSDetails;
import com.neoris.tcl.model.HfmFFSSDetailsId;

public interface HfmFFSSDetailsServiceI {

	Optional<HfmFFSSDetails> find(HfmFFSSDetailsId id);

	List<HfmFFSSDetails> findAll();

	HfmFFSSDetails save(HfmFFSSDetails entity);

	List<HfmFFSSDetails> saveAll(List<HfmFFSSDetails> entityList);

	void delete(HfmFFSSDetails entity);

}