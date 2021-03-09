package com.neoris.tcl.dao;

import org.springframework.data.repository.CrudRepository;

import com.neoris.tcl.models.SetIcpcodes;
import com.neoris.tcl.models.SetIcpcodesPK;

public interface ISetIcpcodesDao extends CrudRepository<SetIcpcodes, SetIcpcodesPK> {

}