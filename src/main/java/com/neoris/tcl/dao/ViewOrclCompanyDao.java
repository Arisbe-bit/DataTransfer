package com.neoris.tcl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.models.ViewOrclCompany;



public interface ViewOrclCompanyDao extends JpaRepository<ViewOrclCompany,Long> {

}
