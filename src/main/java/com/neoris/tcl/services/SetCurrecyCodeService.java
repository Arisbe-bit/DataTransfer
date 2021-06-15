package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ISetCurrecyCodeDao;
import com.neoris.tcl.models.SetCurrecyCode;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class SetCurrecyCodeService implements ISetCurrecyCodeService{
	
	@Autowired
	private ISetCurrecyCodeDao data;

	@Override
	public Optional<SetCurrecyCode> findById(String id) {
		return data.findById(id);
	}

	@Override
	public List<SetCurrecyCode> findAll() {
		return (List<SetCurrecyCode>) data.findAll();
	}

	@Override
	public SetCurrecyCode save(SetCurrecyCode entity) {
		return data.save(entity);
	}

	@Override
	public List<SetCurrecyCode> saveAll(List<SetCurrecyCode> entityList) {
		return (List<SetCurrecyCode>) data.saveAll(entityList);
	}

	@Override
	public void delete(SetCurrecyCode entity) {
		data.delete(entity);
	}

	@Override
	public void deleteAll(List<SetCurrecyCode> entityList) {
		data.deleteAll(entityList);
		
	}
	
	

}
