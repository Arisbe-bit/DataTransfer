package com.neoris.tcl.services;

import java.util.List;

import com.neoris.tcl.models.viewmanualentries;

public interface IViewmanualentriesService {

	List<viewmanualentries> findAll();
	
	List<viewmanualentries> findByPeriodnm(String periodnm);
	

}
