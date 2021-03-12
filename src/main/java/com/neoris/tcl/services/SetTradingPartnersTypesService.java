package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ISetTradingPartnersTypesDao;
import com.neoris.tcl.models.SetTradingPartnersTypes;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class SetTradingPartnersTypesService implements ISetTradingPartnersTypesService {

	@Autowired
	private ISetTradingPartnersTypesDao data;

	@Override
	public Optional<SetTradingPartnersTypes> findById(String id) {
		return data.findById(id);
	}

	@Override
	public List<SetTradingPartnersTypes> findAll() {
		return (List<SetTradingPartnersTypes>) data.findAll();
	}

	@Override
	public SetTradingPartnersTypes save(SetTradingPartnersTypes entity) {
		return data.save(entity);
	}

	@Override
	public List<SetTradingPartnersTypes> saveAll(List<SetTradingPartnersTypes> entityList) {
		return (List<SetTradingPartnersTypes>) data.saveAll(entityList);
	}

	@Override
	public void delete(SetTradingPartnersTypes entity) {
		data.delete(entity);
	}

	@Override
	public void deleteAll(List<SetTradingPartnersTypes> entityList) {
		data.deleteAll(entityList);		
	}

}
