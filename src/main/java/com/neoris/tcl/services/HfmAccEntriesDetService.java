package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;


import com.neoris.tcl.dao.IHfmAccEntriesDetDao;
import com.neoris.tcl.models.HfmAccEntriesDet;


@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmAccEntriesDetService implements IHfmAccEntriesDetService{

	@Autowired
	private IHfmAccEntriesDetDao data;

	@Override
	public List<HfmAccEntriesDet> findAll() {
		return (List<HfmAccEntriesDet>) data.findAll();
	}

	@Override
	public List<HfmAccEntriesDet> findByItemid(int itemid) {
		return data.findByItemid(itemid);
					
	}
	
	
	@Override
	public HfmAccEntriesDet save(HfmAccEntriesDet entity) {
		return data.save(entity);
	}

	

	@Override
	public void delete(HfmAccEntriesDet entity) {
		data.delete(entity);
	}

	@Override
	public void deleteAll(List<HfmAccEntriesDet> entityList) {
		data.deleteAll(entityList);		
	}

	@Override
	public Optional<HfmAccEntriesDet> findById(Long id) {
		return null;
	}

	@Override
	public List<HfmAccEntriesDet> saveAll(List<HfmAccEntriesDet> entityList) {
		return (List<HfmAccEntriesDet>) data.saveAll(entityList);
	}

	
	

	
}
