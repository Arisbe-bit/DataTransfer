package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ISetMasterAccountsDao;
import com.neoris.tcl.models.SetMasterAccounts;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class SetMasterAccountsService implements ISetMasterAccountsService {

	@Autowired
	private ISetMasterAccountsDao data;

	@Override
	public Optional<SetMasterAccounts> findById(String id) {
		return data.findById(id);
	}

	@Override
	public List<SetMasterAccounts> findAll() {
		return (List<SetMasterAccounts>) data.findAll();
	}

	@Override
	public SetMasterAccounts save(SetMasterAccounts entity) {
		return data.save(entity);
	}

	@Override
	public List<SetMasterAccounts> saveAll(List<SetMasterAccounts> entityList) {
		return (List<SetMasterAccounts>) data.saveAll(entityList);
	}

	@Override
	public void delete(SetMasterAccounts entity) {
		data.delete(entity);
	}

}
