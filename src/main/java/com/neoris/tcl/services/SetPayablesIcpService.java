package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ISetPayablesIcpDao;
import com.neoris.tcl.models.SetPayablesIcp;
import com.neoris.tcl.models.SetPayablesIcpPK;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class SetPayablesIcpService implements ISetPayablesIcpService {

	@Autowired
	private ISetPayablesIcpDao data;

	@Override
	public Optional<SetPayablesIcp> findById(SetPayablesIcpPK id) {
		return data.findById(id);
	}

	@Override
	public List<SetPayablesIcp> findAll() {
		return (List<SetPayablesIcp>) data.findAll();
	}

	@Override
	public SetPayablesIcp save(SetPayablesIcp entity) {
		return data.save(entity);
	}

	@Override
	public List<SetPayablesIcp> saveAll(List<SetPayablesIcp> entityList) {
		return (List<SetPayablesIcp>) data.saveAll(entityList);
	}

	@Override
	public void delete(SetPayablesIcp entity) {
		data.delete(entity);
	}

}
