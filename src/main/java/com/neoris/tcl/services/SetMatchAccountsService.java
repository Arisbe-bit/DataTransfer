package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ISetMatchAccountsDao;
import com.neoris.tcl.models.SetMatchAccounts;
import com.neoris.tcl.models.SetMatchAccountsPK;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class SetMatchAccountsService implements ISetMatchAccountsService {

	@Autowired
	private ISetMatchAccountsDao data;

	@Override
	public Optional<SetMatchAccounts> findById(SetMatchAccountsPK id) {
		return data.findById(id);
	}

	@Override
	public List<SetMatchAccounts> findAll() {
		return (List<SetMatchAccounts>) data.findAll();
	}

	@Override
	public SetMatchAccounts save(SetMatchAccounts entity) {
		return data.save(entity);
	}

	@Override
	public List<SetMatchAccounts> saveAll(List<SetMatchAccounts> entityList) {
		return (List<SetMatchAccounts>) data.saveAll(entityList);
	}

	@Override
	public void delete(SetMatchAccounts entity) {
		data.delete(entity);
	}

}
