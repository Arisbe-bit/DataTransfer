package com.neoris.tcl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.models.SetHfmCodes;

@Repository
public interface IHfmRollupEntriesDao extends JpaRepository<HfmRollupEntries, Long> {

	/**
	 * Stored Procedure for processing RollUps
	 * 
	 * @param P_ORGID.-   Company ID
	 * @param P_PERIOD.-  Perdiod: JAN - DEC
	 * @param P_YEAR.-    Year
	 * @param P_SEGMENT.- Segment
	 * @param p_userid.-  User ID
	 */
	@Procedure("rollup_start")
	void rollUpStart(int P_ORGID, String P_PERIOD, String P_YEAR, String P_SEGMENT, String P_USERID);

	/**
	 * Stored Procedure for processing RollUps
	 * 
	 * @param P_ORGID.-   Company ID
	 * @param P_PERIOD.-  Perdiod: JAN - DEC
	 * @param P_YEAR.-    Year
	 * @param P_SEGMENT.- Segment
	 * @param p_userid.-  User ID
	 */
	@Procedure("rollup_deldata")
	void rollDelData(int p_orgid, String p_segment, String p_period, String p_year, String p_userid);

	/**
	 * Stored Procedure for getting Header
	 * 
	 * @param P_ORGID
	 * @param P_SEGMENT
	 * @param P_PERIOD
	 * @param P_YEAR
	 * @param P_CONCEP
	 * @param p_userid
	 */
	@Procedure("ROLLUP_DRILL_DETAIL.get_headers")
	void getHeaders(int p_orgid, String P_segment, String p_period, String p_year, String p_concept, String p_userid);

	/**
	 * 
	 * @param P_ORGID
	 * @param P_SEGMENT
	 * @param P_FEC_INI
	 * @param P_FEC_FIN
	 * @param P_CONCEP
	 * @return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET.get_headers")
	void rollUpDrillCostMngGetHeaders(int p_orgid, String P_segment, String p_period, String p_year, String p_concept);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET.CostManager1_drills")
	void costManager1Drills(int p_orgid,String p_period, String p_year, String p_concept,String p_userid);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET.CostManager2_drills")
	void costManager2Drills(int p_orgid,String p_period, String p_year, String p_concept,String p_userid);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET.CostManager3_drills")
	void costManager3Drills(int p_orgid,String p_period, String p_year, String p_concept,String p_userid);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET.CostManager4_drills")
	void costManager4Drills(int p_orgid,String p_period, String p_year, String p_concept,String p_userid);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET.CostManager5_drills")
	void costManager5Drills(int p_orgid,String p_period, String p_year, String p_concept,String p_userid);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET.CostManager6_drills")
	void costManager6Drills(int p_orgid,String p_period, String p_year, String p_concept, String p_userid);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET.CostManager7_drills")
	void costManager7Drills(int p_orgid,String p_period, String p_year, String p_concept,String p_userid);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET.CostManager8_drills")
	void costManager8Drills(int p_orgid,String p_period, String p_year, String p_concept, String p_userid);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET.CostManager9_drills")
	void costManager9Drills(int p_orgid,String p_period, String p_year, String p_concept, String p_userid);

	/**
	 * 
	 * @param P_ORGID
	 * @param P_PERIOD
	 * @param P_YEAR
	 * @param P_SEGMENT
	 * @param p_userid
	 */
	@Procedure("rollup_validations_hfm")
	void rollUpValidations(int p_orgid, String p_period, String p_year, String p_segment, String p_userid);

	/**
	 * 
	 * @param P_ORGID
	 * @param P_PERIOD
	 * @param P_YEAR
	 * @param P_USERID
	 */
	@Procedure("rollup_match_accounts2")
	void rollUpMatchAccounts(int p_orgid, String p_period, String p_year, String p_userid);
	
	/**
	 * 
	 * @param P_ORGID
	 * @param P_PERIOD
	 * @param P_YEAR
	 * @param P_USERID
	 */
	@Procedure("rollup_reclassification")
	void rollupreclassification(int p_orgid, String p_period, String p_year, String p_userid);

	/**
	 * 
	 * @param P_ORGID
	 * @param P_PERIOD
	 * @param P_SOURCE
	 */
	@Procedure("rollup_reversals")
	void rollupreversals(int p_orgid, String p_period_name, String p_source);

	
	List<HfmRollupEntries> findByCompanyid(int companyid);
	
}