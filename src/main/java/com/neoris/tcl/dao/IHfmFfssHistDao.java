package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.neoris.tcl.models.HfmFfSsHist;
import com.neoris.tcl.models.HfmFfssHistPK;



public interface IHfmFfssHistDao  extends JpaRepository<HfmFfSsHist, HfmFfssHistPK>{

	public List<HfmFfSsHist> findByCompanyidAndPeriodid(Long companyid,String period);
}
