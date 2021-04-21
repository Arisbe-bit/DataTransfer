package com.neoris.tcl.dao;

import java.util.List;import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neoris.tcl.models.HfmOracleAcc;

public interface IHfmOracleAccDao  extends JpaRepository<HfmOracleAcc,Id>{

	
	
}
