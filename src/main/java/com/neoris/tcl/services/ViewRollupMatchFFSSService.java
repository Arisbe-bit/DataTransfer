package com.neoris.tcl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IViewRollupMatchFFSSDao;
import com.neoris.tcl.models.ViewRollupMacthFFSS;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewRollupMatchFFSSService implements IViewRollupMatchFFSSService {

    @Autowired
    private IViewRollupMatchFFSSDao data;

    @Override
    public List<ViewRollupMacthFFSS> findByCompanyid(Long companyId) {
        return data.findByCompanyid(companyId);
    }

}
