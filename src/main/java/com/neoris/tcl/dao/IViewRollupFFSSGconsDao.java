package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.neoris.tcl.models.ViewFFSSGrouped;



public interface IViewRollupFFSSGconsDao extends JpaRepository<ViewFFSSGrouped, String>{	

	 List<ViewFFSSGrouped> findByCompanyidAndHfmcode(String companyid,String hfmcode);
	 List<ViewFFSSGrouped> findByCompanyid(Long companyid);

}
