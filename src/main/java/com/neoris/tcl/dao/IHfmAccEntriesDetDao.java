package com.neoris.tcl.dao;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.models.HfmAccEntriesDet;


public interface IHfmAccEntriesDetDao extends CrudRepository<HfmAccEntriesDet,Id>{
	
	 public List<HfmAccEntriesDet> findByItemid(Long itemid);

}
