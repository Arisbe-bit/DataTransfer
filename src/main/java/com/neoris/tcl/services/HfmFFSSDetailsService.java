package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.HfmFFSSDetailsDao;
import com.neoris.tcl.model.HfmFFSSDetails;
import com.neoris.tcl.model.HfmFFSSDetailsId;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmFFSSDetailsService implements HfmFFSSDetailsServiceI {
	
	@Autowired
	private HfmFFSSDetailsDao data;
	
	@Override
	public Optional<HfmFFSSDetails> find(HfmFFSSDetailsId id) {
		return data.findById(id);
	}

	@Override
	public List<HfmFFSSDetails> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HfmFFSSDetails save(HfmFFSSDetails entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HfmFFSSDetails> saveAll(List<HfmFFSSDetails> entityList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(HfmFFSSDetails entity) {
		// TODO Auto-generated method stub
		
	}

}
