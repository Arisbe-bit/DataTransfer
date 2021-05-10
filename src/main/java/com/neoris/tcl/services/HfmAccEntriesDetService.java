package com.neoris.tcl.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;


import com.neoris.tcl.dao.IHfmAccEntriesDetDao;
import com.neoris.tcl.models.HfmAccEntriesDet;


@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmAccEntriesDetService implements IHfmAccEntriesDetService{

	private final static Logger LOG = LoggerFactory.getLogger(HfmAccEntriesDetService.class);
	@Autowired
	private IHfmAccEntriesDetDao data;

	@Override
	public List<HfmAccEntriesDet> findAll() {
		return (List<HfmAccEntriesDet>) data.findAll();
	}

	@Override
	public List<HfmAccEntriesDet> findByItemid(Long itemid) {
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

	@Override
	public void rollupitemvalidate(Long itemid, BigDecimal p_amount) {
		try {
		   data.rollupitemvalidate(itemid,p_amount); 
			} catch (Exception e) {
				LOG.error("Error p_amount: => {}", e.getMessage());
			}
	}

	/*@Override
	public BigDecimal rollupitemvalidate(Long itemid) {
	try {
		return   data.rollupitemvalidate(itemid); 
		} catch (Exception e) {
			LOG.error("Error al correr IHfmAccEntriesDao.rollUpApplyEntries: => {}", e.getMessage());
		}
	return new BigDecimal(0);
	}
*/
	
	

	
}
