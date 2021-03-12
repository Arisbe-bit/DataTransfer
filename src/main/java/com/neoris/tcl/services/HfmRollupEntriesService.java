package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmRollupEntriesDao;
import com.neoris.tcl.models.HfmRollupEntries;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmRollupEntriesService implements IHfmRollupEntriesService {

	@Autowired
	private IHfmRollupEntriesDao data;

	@Override
	public Optional<HfmRollupEntries> findById(Long id) {
		return data.findById(id);
	}

	@Override
	public List<HfmRollupEntries> findAll() {
		return (List<HfmRollupEntries>) data.findAll();
	}

	@Override
	public HfmRollupEntries save(HfmRollupEntries entity) {
		return data.save(entity);
	}

	@Override
	public List<HfmRollupEntries> saveAll(List<HfmRollupEntries> entityList) {
		return (List<HfmRollupEntries>) data.saveAll(entityList);
	}

	@Override
	public void delete(HfmRollupEntries entity) {
		data.delete(entity);
	}

}
