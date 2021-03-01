/**
 * 
 */
package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neoris.tcl.model.HFMcodes;

@Repository
public interface IHFMcodesDao extends CrudRepository<HFMcodes, String> {

	@Procedure("SP_HFMCODES")
	/**
	 * Call a Stored Procedure
	 * @param id
	 * @return
	 */
	public List<HFMcodes> getCodesByID(String id);
}
