package com.neoris.tcl.dao;

import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.models.HfmFfssDetails;
import com.neoris.tcl.models.HfmFfssDetailsPK;

public interface IHfmFfssDetailsDao extends CrudRepository<HfmFfssDetails, HfmFfssDetailsPK> {

}