package com.neoris.tcl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neoris.tcl.models.HfmFFSSDetailsHist;
import com.neoris.tcl.models.HfmFfssDetailsHistPK;


public interface IViewFfssDetHistDao extends JpaRepository<HfmFFSSDetailsHist,HfmFfssDetailsHistPK> {

}
