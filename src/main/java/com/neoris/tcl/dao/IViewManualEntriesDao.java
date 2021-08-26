package com.neoris.tcl.dao;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neoris.tcl.models.viewmanualentries;

public interface IViewManualEntriesDao extends JpaRepository<viewmanualentries,Long>{

	 public List<viewmanualentries> findByPeriodnameAndPeriodname(Date periodini,Date periodfin);
	 
	 
	/* @Query("select v.num , v.amount , v.applied , v.areaid , v.companyid , v.currencyid , v.description , v.entity , v.hfmcode , v.icpcode , v.itemid , v.movid , v.periodid , v.periodname , v.periodnm , v.status , v.tptype , v.userid " + 
	 		" from rollup_vmanual_entries v where v.periodid Between :periodini and :periodfin " )	*/
	 //public List<viewmanualentries> findByPeriodidBetween(@Param("periodini") int periodini,@Param("periodfin") int periodfin);
	 
	 @Query(value="select v.num , v.amount , v.applied , v.areaid , v.companyid , v.currencyid , v.description , v.entity , v.hfmcode , v.icpcode , v.itemid , v.movid , v.periodid , v.periodname , v.periodnm , v.status , v.tptype , v.userid,v.recurrent " + 
		 		" from rollup_vmanual_entries v where v.periodid >= :periodini and v.periodid <= :periodfin ", nativeQuery = true )
	 public List<viewmanualentries> findByPeriodidBetween(@Param("periodini") int periodini,@Param("periodfin") int periodfin);
	 //public List<viewmanualentries> findByPeriodidreaterthanAndLessthan(@Param("periodini") int periodini,@Param("periodfin") int periodfin);
}
