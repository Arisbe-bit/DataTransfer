package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmFfssDetailsDao;
import com.neoris.tcl.models.HfmFfssDetails;
import com.neoris.tcl.models.HfmFfssDetailsPK;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmFfssDetailsService implements IHfmFfssDetailsService {

	@Autowired
	private IHfmFfssDetailsDao data;

	@Override
	public Optional<HfmFfssDetails> findById(HfmFfssDetailsPK id) {
		return data.findById(id);
	}

	@Override
	public List<HfmFfssDetails> findAll() {
		return (List<HfmFfssDetails>) data.findAll();
	}

	@Override
	public HfmFfssDetails save(HfmFfssDetails entity) {
		return data.save(entity);
	}

	@Override
	public List<HfmFfssDetails> saveAll(List<HfmFfssDetails> entityList) {
		return (List<HfmFfssDetails>) data.saveAll(entityList);
	}

	@Override
	public void delete(HfmFfssDetails entity) {
		data.delete(entity);
	}

}
