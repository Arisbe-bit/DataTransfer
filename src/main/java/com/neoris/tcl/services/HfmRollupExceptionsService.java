package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;


import com.neoris.tcl.dao.IHfmRollupExceptionsDao;
import com.neoris.tcl.models.HfmRollupExceptions;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmRollupExceptionsService implements IHfmRollupExceptionsService {

	private final static Logger LOG = LoggerFactory.getLogger(HfmRollupExceptionsService.class);
	@Autowired
	private IHfmRollupExceptionsDao data;
	
	@Override
	public List<HfmRollupExceptions> findAll() {
		return (List<HfmRollupExceptions>) data.findAll();
	}

	

	@Override
	public HfmRollupExceptions save(HfmRollupExceptions entity) {
		return data.save(entity);
	}

	@Override
	public List<HfmRollupExceptions> saveAll(List<HfmRollupExceptions> entityList) {
		return (List<HfmRollupExceptions>) data.saveAll(entityList);
	}

	@Override
	public void delete(HfmRollupExceptions entity) {
		data.delete(entity);
	}

	@Override
	public void deleteAll(List<HfmRollupExceptions> entityList) {
		data.deleteAll(entityList);
	}

	@Override
	public Optional<HfmRollupExceptions> findById(Long id) {
		return null;
	}



}
