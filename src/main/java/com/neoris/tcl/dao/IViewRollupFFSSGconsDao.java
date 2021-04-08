package com.neoris.tcl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neoris.tcl.models.ViewFFSSGrouped;

@Repository
public interface IViewRollupFFSSGconsDao extends JpaRepository<ViewFFSSGrouped, Long>{

	
	@Query("select num,companyid,periodname,costcenter,hfmcode,accountid,icp,partnerid,tpname,omit,amount,hfmparent " + 
			" from ViewFFSSGrouped " + 
			" where " + 
			" companyid = ?1 " + 
			" and (hfmparent = ?2 or hfmcode = ?3 ) ")
	 // List<ViewFFSSGrouped> findByCompanyidAndhfmparentAndhfmcode(int companyId, String hfmparent,String hfmcode);
	List<Map<String,Object>> findByCompanyidAndhfmparentAndhfmcode(int companyId, String hfmparent,String hfmcode);

}
