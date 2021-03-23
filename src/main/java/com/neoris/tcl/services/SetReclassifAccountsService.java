package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ISetReclassifAccountsDao;
import com.neoris.tcl.models.SetReclassifAccounts;
import com.neoris.tcl.models.SetReclassifAccountsPK;


@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class SetReclassifAccountsService implements ISetReclassifAccountsService {

	@Autowired
	private ISetReclassifAccountsDao data;

	@Override
	public Optional<SetReclassifAccounts> findById(SetReclassifAccountsPK id) {
		return data.findById(id);
	}

	@Override
	public List<SetReclassifAccounts> findAll() {
		return (List<SetReclassifAccounts>) data.findAll();
	}

	@Override
	public SetReclassifAccounts save(SetReclassifAccounts entity) {
		return data.save(entity);
	}

	@Override
	public List<SetReclassifAccounts> saveAll(List<SetReclassifAccounts> entityList) {
		return (List<SetReclassifAccounts>) data.saveAll(entityList);
	}

	@Override
	public void delete(SetReclassifAccounts entity) {
		data.delete(entity);
	}

	@Override
	public void deleteAll(List<SetReclassifAccounts> entityList) {
		data.deleteAll(entityList);		
	}

}
