package com.neoris.tcl.services;


import java.sql.Date;
import java.util.List;

import com.neoris.tcl.models.viewmanualentries;

public interface IViewmanualentriesService {

	List<viewmanualentries> findAll();
	
	
	
	List<viewmanualentries> findByPeriodnameAndPeriodname(Date periodini,Date periodfin);
	//List<viewmanualentries> findByPeriodidreaterthanAndLessthan(int periodini,int periodfin);
	List<viewmanualentries> findByPeriodidBetween(int periodini,int periodfin);

}
