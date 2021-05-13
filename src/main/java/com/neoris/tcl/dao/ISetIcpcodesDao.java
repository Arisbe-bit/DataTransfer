package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.neoris.tcl.models.SetIcpcodes;


public interface ISetIcpcodesDao extends CrudRepository<SetIcpcodes, String> {
	

	List<SetIcpcodes> findByTptype(String tptype);
	 

}