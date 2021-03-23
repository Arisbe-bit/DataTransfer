package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neoris.tcl.models.HfmFfssDetails;
import com.neoris.tcl.models.HfmFfssDetailsPK;

@Repository
public interface IHfmFfssDetailsDao extends CrudRepository<HfmFfssDetails, HfmFfssDetailsPK> {
    /**
     * Find all HfmFfssDetails records by company Id, hfmcode and period.
     * NOTE: The fields are all in the Id Class of the entity, thats why we add an "Id" at beggining of
     * each field.
     * @param companyId
     * @param hfmcode
     * @param period
     * @return
     */
    public List<HfmFfssDetails> findByIdCompanyidAndIdHfmcodeAndIdPeriod(Long companyId, String hfmcode, String period);

}