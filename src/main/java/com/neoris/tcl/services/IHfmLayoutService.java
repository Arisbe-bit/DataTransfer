package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.HfmLayout;
import com.neoris.tcl.models.HfmLayoutPK;

public interface IHfmLayoutService {

	Optional<HfmLayout> findById(HfmLayoutPK id);

	List<HfmLayout> findAll();

	HfmLayout save(HfmLayout entity);

	List<HfmLayout> saveAll(List<HfmLayout> entityList);

	void delete(HfmLayout entity);
	
	List<HfmLayout> findByIdCompanyid(int companyid);
	
	void rollUpLayout(int p_orgid, String p_period, String p_year, String p_userid);

}