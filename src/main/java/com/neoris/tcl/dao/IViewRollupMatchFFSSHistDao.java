package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.neoris.tcl.models.ViewRollupMatchFFSSHist;

public interface IViewRollupMatchFFSSHistDao extends JpaRepository<ViewRollupMatchFFSSHist,Long>{
	
	List<ViewRollupMatchFFSSHist> findByCompanyid(Long companyId);
	

}
