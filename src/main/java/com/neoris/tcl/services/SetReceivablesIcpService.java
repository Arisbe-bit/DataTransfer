package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ISetReceivablesIcpDao;
import com.neoris.tcl.models.SetReceivablesIcp;
import com.neoris.tcl.models.SetReceivablesIcpPK;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class SetReceivablesIcpService implements ISetReceivablesIcpService {

	@Autowired
	private ISetReceivablesIcpDao data;

	@Override
	public Optional<SetReceivablesIcp> findById(SetReceivablesIcpPK id) {
		return data.findById(id);
	}

	@Override
	public List<SetReceivablesIcp> findAll() {
		return (List<SetReceivablesIcp>) data.findAll();
	}

	@Override
	public SetReceivablesIcp save(SetReceivablesIcp entity) {
		return data.save(entity);
	}

	@Override
	public List<SetReceivablesIcp> saveAll(List<SetReceivablesIcp> entityList) {
		return (List<SetReceivablesIcp>) data.saveAll(entityList);
	}

	@Override
	public void delete(SetReceivablesIcp entity) {
		data.delete(entity);
	}

}
