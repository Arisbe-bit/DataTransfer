package com.neoris.tcl.dao;

import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.model.PayablesICP;
import com.neoris.tcl.model.PayablesICPId;

public interface IPayablesICPDao extends CrudRepository<PayablesICP, PayablesICPId> {

}
