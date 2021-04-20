package com.neoris.tcl.dao;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.models.HfmLayoutHist;
import com.neoris.tcl.models.HfmLayoutHistPK;

public interface IHfmLayoutHistDao extends CrudRepository<HfmLayoutHist, HfmLayoutHistPK> {
	
	public List<HfmLayoutHist> findByIdCompanyid(int companyid);
	  

}
