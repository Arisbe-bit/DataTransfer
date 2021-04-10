package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;


import com.neoris.tcl.models.HfmLayout;
import com.neoris.tcl.models.HfmLayoutPK;

public interface IHfmLayoutDao extends CrudRepository<HfmLayout, HfmLayoutPK> {
	
	  public List<HfmLayout> findByIdCompanyid(int companyid);
	  
	  @Procedure("rollup_genlayout")
		void rollUpLayout (int p_orgid, String p_period, String p_year, String p_userid);

}