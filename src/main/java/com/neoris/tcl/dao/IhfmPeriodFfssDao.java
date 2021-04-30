package com.neoris.tcl.dao;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.models.HfmPeriodFfss;

public interface IhfmPeriodFfssDao extends CrudRepository<HfmPeriodFfss,Long>{

	 public List<HfmPeriodFfss> findByCompanyid(int companyId);
}
