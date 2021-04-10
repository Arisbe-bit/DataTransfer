package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.neoris.tcl.models.ViewFFSSGroupedHist;

@Repository
public interface IViewRollupFFSSGconsHistDao extends JpaRepository<ViewFFSSGroupedHist, Long>{
	
	 List<ViewFFSSGroupedHist> findByCompanyidAndHfmcode(int companyid,String hfmcode);

}
