package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmAccEntriesDao;
import com.neoris.tcl.models.HfmAccEntries;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmAccEntriesService implements IHfmAccEntriesService{
	
	private final static Logger LOG = LoggerFactory.getLogger(HfmOracleAccService.class);
	@Autowired
	private IHfmAccEntriesDao data;

	@Override
	public List<HfmAccEntries> findAll() {
		return (List<HfmAccEntries>) data.findAll();
	}

	@Override
	public List<HfmAccEntries> findByCompanyid(int companyid) {
		return data.findByCompanyid(companyid);
					
	}
	
	
	
	@Override
	public HfmAccEntries save(HfmAccEntries entity) {
		return data.save(entity);
	}

	@Override
	public List<HfmAccEntries> saveAll(List<HfmAccEntries> entityList) {
		return (List<HfmAccEntries>) data.saveAll(entityList);
	}

	@Override
	public void delete(HfmAccEntries entity) {
		data.delete(entity);
	}

	@Override
	public void deleteAll(List<HfmAccEntries> entityList) {
		data.deleteAll(entityList);		
	}

	@Override
	public Optional<HfmAccEntries> findById(Long id) {
		return null;
	}

	@Override
	public void rollUpApplyEntries(int p_orgid, String p_periodnm, String p_userid, int p_itemid) {
		LOG.info("Entering to  run apply entries: p_orgid = {}, p_periodnm = {}, p_itemid = {} ", p_orgid, p_itemid, p_itemid);
		try {
			data.rollUpApplyEntries(p_orgid, p_periodnm, p_userid, p_itemid); 
			
			LOG.info("********************rollUpApplyEntries Starting:***************************");
		} catch (Exception e) {
			LOG.error("Error al correr IHfmAccEntriesDao.rollUpApplyEntries: => {}", e.getMessage());
		}
		
	}

	
	

}
