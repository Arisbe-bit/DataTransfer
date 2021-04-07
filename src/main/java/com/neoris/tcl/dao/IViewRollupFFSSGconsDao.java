package com.neoris.tcl.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neoris.tcl.models.ViewFFSSGrouped;

@Repository
public interface IViewRollupFFSSGconsDao extends CrudRepository<ViewFFSSGrouped, Long>{

	@Transactional
	@Modifying
	@Query("select periodname,costcenter,hfmparent,hfmcode,accountid,icp,partnerid,tpname,partnerid,tpname,omit,amount " + 
			" from ViewFFSSGrouped " + 
			" where " + 
			" companyid = ?1 " + 
			" and (hfmparent = ?2 or hfmcode = ?3 ) ")
	  List<ViewFFSSGrouped> findByCompanyidAndhfmparentAndhfmcode(Long companyId, String hfmparent,String hfmcode);

}
