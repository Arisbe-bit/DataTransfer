package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.tcl.models.HfmFfssDetails;
import com.neoris.tcl.models.HfmFfssDetailsPK;


public interface IHfmFfssDetailsDao extends JpaRepository<HfmFfssDetails, HfmFfssDetailsPK> {
    /**
     * Find all HfmFfssDetails records by company Id, hfmcode and period.
     * NOTE: The fields are all in the Id Class of the entity, thats why we add an "Id" at beggining of
     * each field.
     * @param companyId
     * @param hfmcode
     * @param period
     * @return
     */
	
	
    public List<HfmFfssDetails> findByIdCompanyidAndIdHfmcodeAndIdCostcenterAndIdAccountidAndIdPartnerid(int companyId, String Hfmcode,String costcenter,String accountid, String partnerid);

}