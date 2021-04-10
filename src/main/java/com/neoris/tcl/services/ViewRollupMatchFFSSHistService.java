package com.neoris.tcl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IViewRollupMatchFFSSHistDao;
import com.neoris.tcl.models.ViewRollupMatchFFSSHist;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class ViewRollupMatchFFSSHistService implements IViewRollupMatchFFSSHistService{
	
	@Autowired
    private IViewRollupMatchFFSSHistDao data;
	
	@Override
    public List<ViewRollupMatchFFSSHist> findByCompanyid(Long companyId) {
        return data.findByCompanyid(companyId);
    }

}
