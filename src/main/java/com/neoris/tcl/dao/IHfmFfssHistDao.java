package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.models.HfmFfSsHist;
import com.neoris.tcl.models.HfmFfss;
import com.neoris.tcl.models.HfmFfssHistPK;



public interface IHfmFfssHistDao  extends CrudRepository<HfmFfSsHist, HfmFfssHistPK>{

	public List<HfmFfSsHist> findByCompanyIdAndPeriodid(Long companyId,String period);
}
