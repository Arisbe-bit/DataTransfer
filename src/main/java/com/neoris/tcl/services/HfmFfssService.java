package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmFfssDao;
import com.neoris.tcl.models.HfmFfss;
import com.neoris.tcl.models.HfmFfssPK;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmFfssService implements IHfmFfssService {

    private final static Logger LOG = LoggerFactory.getLogger(HfmFfssService.class);
    
	@Autowired
	private IHfmFfssDao data;

	@Override
	public Optional<HfmFfss> findById(HfmFfssPK id) {
		return data.findById(id);
	}

	@Override
	public List<HfmFfss> findAll() {
		return (List<HfmFfss>) data.findAll();
	}

	@Override
	public HfmFfss save(HfmFfss entity) {
		return data.save(entity);
	}

	@Override
	public List<HfmFfss> saveAll(List<HfmFfss> entityList) {
		return (List<HfmFfss>) data.saveAll(entityList);
	}

	@Override
	public void delete(HfmFfss entity) {
		data.delete(entity);
	}

    @Override
    public List<HfmFfss> findByCompanyId(Long companyId) {
        LOG.info("Getting HfmFfss list with companyId ={} ", companyId);
        return data.findByCompanyId(companyId);
    }

}
