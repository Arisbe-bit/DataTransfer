package com.neoris.tcl.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.models.HfmAccEntriesDet;


public interface IHfmAccEntriesDetDao extends CrudRepository<HfmAccEntriesDet,Id>{
	
	 public List<HfmAccEntriesDet> findByItemid(Long itemid);
	 
	// @Procedure("rollup_itemvalidate")
	// BigDecimal rollupitemvalidate(Long p_itemid);
	 
	
	 
	 @Procedure("rollup_itemvalidate")
	 public void rollupitemvalidate(Long p_itemid, BigDecimal p_amount);

}
