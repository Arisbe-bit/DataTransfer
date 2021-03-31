package com.neoris.tcl.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.neoris.tcl.models.ViewRollupMacthFFSS;

public interface IViewRollupMatchFFSSDao extends JpaRepository<ViewRollupMacthFFSS, Long> {

    List<ViewRollupMacthFFSS> findByCompanyid(Long companyId);
}
