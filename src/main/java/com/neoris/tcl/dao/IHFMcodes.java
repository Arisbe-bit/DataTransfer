/**
 * 
 */
package com.neoris.tcl.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neoris.tcl.model.HFMcodes;

@Repository
public interface IHFMcodes extends CrudRepository<HFMcodes, String> {

}
