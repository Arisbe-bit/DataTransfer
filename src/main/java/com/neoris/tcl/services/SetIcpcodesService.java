package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ISetIcpcodesDao;
import com.neoris.tcl.models.SetIcpcodes;
import com.neoris.tcl.models.SetIcpcodesPK;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class SetIcpcodesService implements ISetIcpcodesService {

	@Autowired
	private ISetIcpcodesDao data;

	@Override
	public Optional<SetIcpcodes> findById(SetIcpcodesPK id) {
		return data.findById(id);
	}

	@Override
	public List<SetIcpcodes> findAll() {
		return (List<SetIcpcodes>) data.findAll();
	}

	@Override
	public SetIcpcodes save(SetIcpcodes entity) {
		return data.save(entity);
	}

	@Override
	public List<SetIcpcodes> saveAll(List<SetIcpcodes> entityList) {
		return (List<SetIcpcodes>) data.saveAll(entityList);
	}

	@Override
	public void delete(SetIcpcodes entity) {
		data.delete(entity);
	}

	@Override
	public void deleteAll(List<SetIcpcodes> entityList) {
		data.deleteAll(entityList);		
	}

}
