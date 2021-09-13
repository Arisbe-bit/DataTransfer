package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.neoris.tcl.models.ViewSegmentCompany;



public interface IViewSegmentCompanyDao extends JpaRepository<ViewSegmentCompany,Long>{

	
}
