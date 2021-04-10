package com.neoris.tcl.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.neoris.tcl.models.ViewRollupMatchFFSS;

public interface IViewRollupMatchFFSSDao extends JpaRepository<ViewRollupMatchFFSS, Long> {

    List<ViewRollupMatchFFSS> findByCompanyid(Long companyId);
}
