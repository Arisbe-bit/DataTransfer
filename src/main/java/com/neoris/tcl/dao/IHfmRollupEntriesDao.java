package com.neoris.tcl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.neoris.tcl.models.HfmRollupEntries;

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
	@Procedure("ROLLUP_START")
	void rollUpStart(int P_ORGID, String P_PERIOD, int P_YEAR, String P_SEGMENT, String P_USERID);

	/**
	 * Stored Procedure for getting Header
	 * 
	 * @param P_ORGID
	 * @param P_SEGMENT
	 * @param P_FEC_INI
	 * @param P_FEC_FIN
	 * @param P_CONCEP
	 * @param p_userid
	 */
	@Procedure("ROLLUP_DRILL_DETAIL_GET_HEADERS")
	void getHeaders(int P_ORGID, String P_SEGMENT, String P_PERIOD, int P_YEAR, String P_CONCEP, String P_USERID);

	/**
	 * 
	 * @param P_ORGID
	 * @param P_SEGMENT
	 * @param P_FEC_INI
	 * @param P_FEC_FIN
	 * @param P_CONCEP
	 * @return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET_GET_HEADERS")
	void rollUpDrillCostMngGetHeaders(int P_ORGID, String P_SEGMENT, String P_PERIOD, int P_YEAR, String P_CONCEP);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET_CostManager1_drills")
	void costManager1Drills(String P_PERIOD, int P_YEAR, String P_CONCEPT, String P_USERID);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET_CostManager2_drills")
	void costManager2Drills(String P_PERIOD, int P_YEAR, String P_CONCEPT, String P_USERID);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET_CostManager3_drills")
	void costManager3Drills(String P_PERIOD, int P_YEAR, String P_CONCEPT, String P_USERID);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET_CostManager4_drills")
	void costManager4Drills(String P_PERIOD, int P_YEAR, String P_CONCEPT, String P_USERID);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET_CostManager5_drills")
	void costManager5Drills(String P_PERIOD, int P_YEAR, String P_CONCEPT, String P_USERID);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET_CostManager6_drills")
	void costManager6Drills(String P_PERIOD, int P_YEAR, String P_CONCEPT, String P_USERID);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET_CostManager7_drills")
	void costManager7Drills(String P_PERIOD, int P_YEAR, String P_CONCEPT, String P_USERID);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET_CostManager8_drills")
	void costManager8Drills(String P_PERIOD, int P_YEAR, String P_CONCEPT, String P_USERID);

	/**
	 * 
	 * @param P_ORGID
	 * @param v_return
	 */
	@Procedure("ROLLUP_DRILLCOSTMNG_DET_CostManager9_drills")
	void costManager9Drills(String P_PERIOD, int P_YEAR, String P_CONCEPT, String P_USERID);

	/**
	 * 
	 * @param P_ORGID
	 * @param P_PERIOD
	 * @param P_YEAR
	 * @param P_SEGMENT
	 * @param p_userid
	 */
	@Procedure("ROLLUP_VALIDATIONS")
	void rollUpValidations(int P_ORGID, String P_PERIOD, int P_YEAR, String P_SEGMENT, String P_USERID);

	/**
	 * 
	 * @param P_ORGID
	 * @param P_PERIOD
	 * @param P_YEAR
	 * @param P_USERID
	 */
	@Procedure("ROLLUP_MATCH_ACCOUNTS")
	void rollUpMatchAccounts(int P_ORGID, String P_PERIOD, int P_YEAR, String P_USERID);

}