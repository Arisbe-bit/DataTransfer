package com.neoris.tcl.services;

import java.util.List;

import com.neoris.tcl.models.ViewRollupMatchFFSS;

public interface IViewRollupMatchFFSSService {

    List<ViewRollupMatchFFSS> findByCompanyid(Long companyId);

}
