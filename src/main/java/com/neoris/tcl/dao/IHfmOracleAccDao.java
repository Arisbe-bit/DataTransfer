package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neoris.tcl.models.HfmOracleAcc;

@Repository
public interface IHfmOracleAccDao  extends JpaRepository<HfmOracleAcc,Long>{

	List<HfmOracleAcc> findByOrgidAndCostcenter(int Orgid,String costcenter);
	
	
	List<HfmOracleAcc> findByOrgid(@Param("p_orgid") int Orgid);
	
	@Procedure("rollup_refresh")
	 void rollUprefresh();
	
	

}
