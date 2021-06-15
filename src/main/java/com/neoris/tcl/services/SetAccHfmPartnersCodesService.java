package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ISetAccHfmPartnersCodesDao;
import com.neoris.tcl.models.SetAccHfmPartnersCodes;
import com.neoris.tcl.models.SetAccHfmPartnersCodesPK;


@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class SetAccHfmPartnersCodesService implements ISetAccHfmPartnersCodesService {

	@Autowired
	private ISetAccHfmPartnersCodesDao data;

	@Override
	public Optional<SetAccHfmPartnersCodes> findById(SetAccHfmPartnersCodesPK id) {
		return data.findById(id);
	}

	@Override
	public List<SetAccHfmPartnersCodes> findAll() {
		return (List<SetAccHfmPartnersCodes>) data.findAll();
	}

	@Override
	public SetAccHfmPartnersCodes save(SetAccHfmPartnersCodes entity) {
		return data.save(entity);
	}

	@Override
	public List<SetAccHfmPartnersCodes> saveAll(List<SetAccHfmPartnersCodes> entityList) {
		return (List<SetAccHfmPartnersCodes>) data.saveAll(entityList);
	}

	@Override
	public void delete(SetAccHfmPartnersCodes entity) {
		data.delete(entity);
	}
	
	@Override
	public void deleteAll(List<SetAccHfmPartnersCodes> entityList) {
		data.deleteAll(entityList);		
	}


}
