package com.neoris.tcl.services;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IViewManualEntriesDao;
import com.neoris.tcl.models.viewmanualentries;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewmanualentriesService implements IViewmanualentriesService {

	
	@Autowired
	private IViewManualEntriesDao data;
	
	@Override
	public List<viewmanualentries> findAll() {
		return (List<viewmanualentries>) data.findAll();
	}

	
	@Override
	public List<viewmanualentries> findByPeriodnameAndPeriodname(Date periodini, Date periodfin) {
		
		return data.findByPeriodnameAndPeriodname(periodini,periodfin);
	}


	@Override
	public List<viewmanualentries> findByPeriodidAndPeriodid(int periodini, int periodfin) {
		
		return data.findByPeriodidAndPeriodid(periodini, periodfin);
	}

}
