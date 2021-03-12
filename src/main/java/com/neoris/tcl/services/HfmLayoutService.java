package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmLayoutDao;
import com.neoris.tcl.models.HfmLayout;
import com.neoris.tcl.models.HfmLayoutPK;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmLayoutService implements IHfmLayoutService {

	@Autowired
	private IHfmLayoutDao data;

	@Override
	public Optional<HfmLayout> findById(HfmLayoutPK id) {
		return data.findById(id);
	}

	@Override
	public List<HfmLayout> findAll() {
		return (List<HfmLayout>) data.findAll();
	}

	@Override
	public HfmLayout save(HfmLayout entity) {
		return data.save(entity);
	}

	@Override
	public List<HfmLayout> saveAll(List<HfmLayout> entityList) {
		return (List<HfmLayout>) data.saveAll(entityList);
	}

	@Override
	public void delete(HfmLayout entity) {
		data.delete(entity);
	}

}
