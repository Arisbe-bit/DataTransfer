package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neoris.tcl.models.viewmanualentries;

public interface IViewManualEntriesDao extends JpaRepository<viewmanualentries,Long>{

	 public List<viewmanualentries> findByPeriodnm(String periodnm);
}
