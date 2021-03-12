package com.neoris.tcl.dao;

import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.models.HfmLayout;
import com.neoris.tcl.models.HfmLayoutPK;

public interface IHfmLayoutDao extends CrudRepository<HfmLayout, HfmLayoutPK> {

}