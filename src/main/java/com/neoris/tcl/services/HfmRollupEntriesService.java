package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.IHfmRollupEntriesDao;
import com.neoris.tcl.models.HfmRollupEntries;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HfmRollupEntriesService implements IHfmRollupEntriesService {

	private final static Logger LOG = LoggerFactory.getLogger(HfmRollupEntriesService.class);

	@Autowired
	private IHfmRollupEntriesDao data;

	@Override
	public Optional<HfmRollupEntries> findById(Long id) {
		return data.findById(id);
	}

	@Override
	public List<HfmRollupEntries> findAll() {
		return (List<HfmRollupEntries>) data.findAll();
	}

	@Override
	public HfmRollupEntries save(HfmRollupEntries entity) {
		return data.save(entity);
	}

	@Override
	public List<HfmRollupEntries> saveAll(List<HfmRollupEntries> entityList) {
		return (List<HfmRollupEntries>) data.saveAll(entityList);
	}

	@Override
	public void delete(HfmRollupEntries entity) {
		data.delete(entity);
	}

	@Override
	public void rollUpStart(int P_ORGID, String P_PERIOD, String P_YEAR, String P_SEGMENT, String P_USERID) {
		LOG.info("Entering to  run rollUpStart: P_ORGID = {}, P_PERIOD = {}, P_YEAR = {}, P_SEGMENT = {}", P_ORGID, P_PERIOD, P_YEAR, P_SEGMENT);
		try {
			data.rollUpStart(P_ORGID, P_PERIOD, P_YEAR, P_SEGMENT, P_USERID);
			LOG.error("********************IHfmRollupEntriesDao.rollUpStart  Finished:***************************");
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.rollUpStart: => {}", e.getMessage());
		}

	}

	@Override
	public void getHeaders(int P_ORGID, String P_SEGMENT, String P_PERIOD, String P_YEAR, String P_CONCEPT, String P_USERID) {
		LOG.info("Entering to  run getHeaders: P_ORGID = {}, P_SEGMENT = {}, P_PERIOD = {}, P_YEAR = {},P_CONCEPT = {},P_USERID = {}", P_ORGID, P_SEGMENT, P_PERIOD, P_YEAR,P_CONCEPT,P_USERID);
		try {
			//String comp= String.valueOf(P_ORGID);
			data.getHeaders( P_ORGID, P_SEGMENT, P_PERIOD, P_YEAR, P_CONCEPT, P_USERID);
			//data.getHeaders( "93", "accl", "DEC", "2020", "Payables", "ususariox");
			
			LOG.info("************getHeaders Finished - "+P_CONCEPT+" *************************");
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.getHeaders: => {}", e.getMessage());
		}
	}

	@Override
	public void rollUpDrillCostMngDetGetHeaders(int P_ORGID, String P_SEGMENT, String P_PERIOD, String P_YEAR, String P_CONCEP) {
		LOG.info("Entering to  run rollUpDrillCostMngDetGetHeaders: P_ORGID = {}, P_PERIOD = {}, P_YEAR = {}, P_SEGMENT = {}", P_ORGID, P_PERIOD, P_YEAR, P_SEGMENT);
		try {
			LOG.error("*****************rollUpDrillCostMngDetGetHeaders  Starts:************");
			data.rollUpDrillCostMngGetHeaders(P_ORGID, P_SEGMENT, P_PERIOD, P_YEAR, P_CONCEP);
			LOG.error("*****************rollUpDrillCostMngDetGetHeaders  Finisehd:************");
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.rollUpDrillCostMngDetGetHeaders: => {}", e.getMessage());
		}
	}

	@Override
	public void costManager1Drills(int P_ORGID,String P_PERIOD, String P_YEAR, String P_USERID) {
		LOG.info("Entering to  run costManager1Drills: P_PERIOD = {}, P_YEAR = {}, P_USERID = {}", P_PERIOD, P_YEAR, P_USERID);
		try {
			data.costManager1Drills(P_ORGID,P_PERIOD, P_YEAR, P_COSTMANAGER, P_USERID);
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.costManager1Drills: => {}", e.getMessage());
		}
	}

	@Override
	public void costManager2Drills(int P_ORGID,String P_PERIOD, String P_YEAR, String P_USERID) {
		LOG.info("Entering to  run costManager2Drills: P_PERIOD = {}, P_YEAR = {}, P_USERID = {}", P_PERIOD, P_YEAR, P_USERID);
		try {
			data.costManager2Drills(P_ORGID,P_PERIOD, P_YEAR, P_COSTMANAGER, P_USERID);
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.costManager2Drills: => {}", e.getMessage());
		}
	}

	@Override
	public void costManager3Drills(int P_ORGID,String P_PERIOD, String P_YEAR, String P_USERID) {
		LOG.info("Entering to  run costManager3Drills: P_PERIOD = {}, P_YEAR = {}, P_USERID = {}", P_PERIOD, P_YEAR, P_USERID);
		try {
			data.costManager3Drills(P_ORGID,P_PERIOD, P_YEAR, P_COSTMANAGER, P_USERID);
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.costManager3Drills: => {}", e.getMessage());
		}
	}

	@Override
	public void costManager4Drills(int P_ORGID,String P_PERIOD, String P_YEAR, String P_USERID) {
		LOG.info("Entering to  run costManager4Drills: P_PERIOD = {}, P_YEAR = {}, P_USERID = {}", P_PERIOD, P_YEAR, P_USERID);
		try {
			data.costManager4Drills(P_ORGID,P_PERIOD, P_YEAR, P_COSTMANAGER, P_USERID);
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.costManager4Drills: => {}", e.getMessage());
		}
	}

	@Override
	public void costManager5Drills(int P_ORGID,String P_PERIOD, String P_YEAR, String P_USERID) {
		LOG.info("Entering to  run costManager5Drills: P_PERIOD = {}, P_YEAR = {}, P_USERID = {}", P_PERIOD, P_YEAR, P_USERID);
		try {
			data.costManager5Drills(P_ORGID,P_PERIOD, P_YEAR, P_COSTMANAGER, P_USERID);
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.costManager5Drills: => {}", e.getMessage());
		}
	}

	@Override
	public void costManager6Drills(int P_ORGID,String P_PERIOD, String P_YEAR, String P_USERID) {
		LOG.info("Entering to  run costManager6Drills: P_PERIOD = {}, P_YEAR = {}, P_USERID = {}", P_PERIOD, P_YEAR, P_USERID);
		try {
			data.costManager6Drills(P_ORGID,P_PERIOD, P_YEAR, P_COSTMANAGER, P_USERID);
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.costManager6Drills: => {}", e.getMessage());
		}
	}

	@Override
	public void costManager7Drills(int P_ORGID,String P_PERIOD, String P_YEAR, String P_USERID) {
		LOG.info("Entering to  run costManager7Drills: P_PERIOD = {}, P_YEAR = {}, P_USERID = {}", P_PERIOD, P_YEAR, P_USERID);
		try {
			data.costManager7Drills(P_ORGID,P_PERIOD, P_YEAR, P_COSTMANAGER, P_USERID);
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.costManager7Drills: => {}", e.getMessage());
		}
	}

	@Override
	public void costManager8Drills(int P_ORGID,String P_PERIOD, String P_YEAR, String P_USERID) {
		LOG.info("Entering to  run costManager8Drills: P_PERIOD = {}, P_YEAR = {}, P_USERID = {}", P_PERIOD, P_YEAR, P_USERID);
		try {
			data.costManager8Drills(P_ORGID,P_PERIOD, P_YEAR, P_COSTMANAGER, P_USERID);
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.costManager8Drills: => {}", e.getMessage());
		}
	}

	@Override
	public void costManager9Drills(int P_ORGID,String P_PERIOD, String P_YEAR, String P_USERID) {
		LOG.info("Entering to  run costManager9Drills: P_PERIOD = {}, P_YEAR = {}, P_USERID = {}", P_PERIOD, P_YEAR, P_USERID);
		try {
			data.costManager9Drills(P_ORGID,P_PERIOD, P_YEAR, P_COSTMANAGER, P_USERID);
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.costManager9Drills: => {}", e.getMessage());
		}
	}

	@Override
	public void rollUpValidations(int P_ORGID, String P_PERIOD, String P_YEAR, String P_SEGMENT, String P_USERID) {
		LOG.info("Entering to  run rollUpValidations: P_PERIOD = {}, P_YEAR = {}, P_USERID = {}", P_PERIOD, P_YEAR, P_USERID);
		try {
			data.rollUpValidations(P_ORGID, P_PERIOD, P_YEAR, P_SEGMENT, P_USERID);
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.rollUpValidations: => {}", e.getMessage());
		}
	}

	@Override
	public void rollUpMatchAccounts(int P_ORGID, String P_PERIOD, String P_YEAR, String P_USERID) {
		LOG.info("Entering to  run rollUpMatchAccounts: P_PERIOD = {}, P_YEAR = {}, P_USERID = {}", P_PERIOD, P_YEAR, P_USERID);
		try {
			data.rollUpMatchAccounts(P_ORGID, P_PERIOD, P_YEAR, P_USERID);
		} catch (Exception e) {
			LOG.error("Error al correr IHfmRollupEntriesDao.rollUpMatchAccounts: => {}", e.getMessage());
		}
	}

    @Override
    public void deleteAll(List<HfmRollupEntries> entityList) {
        data.deleteAll(entityList);        
    }

}
