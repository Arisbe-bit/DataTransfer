package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmFfssHistDao;
import com.neoris.tcl.models.HfmFfSsHist;
import com.neoris.tcl.models.HfmFfssHistPK;


@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmFfssHistService implements IHfmFfssHistService{

	
	 private final static Logger LOG = LoggerFactory.getLogger(HfmFfssHistService.class);
	    
		@Autowired
		private IHfmFfssHistDao data;

		@Override
		public Optional<HfmFfSsHist> findById(HfmFfssHistPK id) {
			return data.findById(id);
		}

		@Override
		public List<HfmFfSsHist> findAll() {
			return (List<HfmFfSsHist>) data.findAll();
		}

		@Override
		public HfmFfSsHist save(HfmFfSsHist entity) {
			return data.save(entity);
		}

		@Override
		public List<HfmFfSsHist> saveAll(List<HfmFfSsHist> entityList) {
			return (List<HfmFfSsHist>) data.saveAll(entityList);
		}

		@Override
		public void delete(HfmFfSsHist entity) {
			data.delete(entity);
		}

	    @Override
	    public List<HfmFfSsHist> findByCompanyIdAndPeriodid(Long companyId, String period) {
	        LOG.info("Getting HfmFfss list with companyId,period ={} ", companyId, period);
	        return data.findByCompanyIdAndPeriodid(companyId,period);
	    }

}
