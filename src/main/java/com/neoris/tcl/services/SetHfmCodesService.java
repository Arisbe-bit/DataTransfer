package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ISetHfmCodesDao;
import com.neoris.tcl.models.SetHfmCodes;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class SetHfmCodesService implements ISetHfmCodesService {

	@Autowired
	private ISetHfmCodesDao data;

	@Override
	public Optional<SetHfmCodes> findById(String id) {
		return data.findById(id);
	}

	@Override
	public List<SetHfmCodes> findAll() {
		return (List<SetHfmCodes>) data.findAll();
	}

	@Override
	public SetHfmCodes save(SetHfmCodes entity) {
		return data.save(entity);
	}

	@Override
	public List<SetHfmCodes> saveAll(List<SetHfmCodes> entityList) {
		return (List<SetHfmCodes>) data.saveAll(entityList);
	}

	@Override
	public void delete(SetHfmCodes entity) {
		data.delete(entity);
	}

	@Override
	public void deleteAll(List<SetHfmCodes> entityList) {
		data.deleteAll(entityList);		
	}

	

}
