package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.SetAccHfmPartnersCodes;
import com.neoris.tcl.models.SetAccHfmPartnersCodesPK;


public interface ISetAccHfmPartnersCodesService {

	Optional<SetAccHfmPartnersCodes> findById(SetAccHfmPartnersCodesPK id);

	List<SetAccHfmPartnersCodes> findAll();

	SetAccHfmPartnersCodes save(SetAccHfmPartnersCodes entity);

	List<SetAccHfmPartnersCodes> saveAll(List<SetAccHfmPartnersCodes> entityList);

	void delete(SetAccHfmPartnersCodes entity);
	
	void deleteAll(List<SetAccHfmPartnersCodes> entityList);

}