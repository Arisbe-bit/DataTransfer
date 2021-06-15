package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.neoris.tcl.models.ViewPayablesSupp;



public interface ViewPayablesSupliersDao extends JpaRepository<ViewPayablesSupp, String>{
	
	List<ViewPayablesSupp> findByOrganizationid(int organizationid);

}
