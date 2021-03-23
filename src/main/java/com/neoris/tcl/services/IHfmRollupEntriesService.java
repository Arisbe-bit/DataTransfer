package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.models.SetHfmCodes;

public interface IHfmRollupEntriesService {

	public final static String P_CONCEPT_PAYABLES = "Payables";
	public final static String P_CONCEPT_RECEIVABLES = "Receivables";
	public final static String P_CONCEPT_PAYROLL = "Payroll";
	public final static String P_CONCEPT_ASSET = "Assets";
	public final static String P_CONCEPT_OTHER = "Others";
	public final static String P_COSTMANAGER = "COSTMANAGER";
	
	Optional<HfmRollupEntries> findById(Long id);

	List<HfmRollupEntries> findAll();

	HfmRollupEntries save(HfmRollupEntries entity);

	List<HfmRollupEntries> saveAll(List<HfmRollupEntries> entityList);

	void delete(HfmRollupEntries entity);

	void rollUpStart(int P_ORGID, String P_PERIOD, int P_YEAR, String P_SEGMENT, String P_USERID);

	void getHeaders(int P_ORGID, String P_SEGMENT, String P_PERIOD, int P_YEAR, String P_CONCEP, String P_USERID);

	void rollUpDrillCostMngDetGetHeaders(int P_ORGID, String P_SEGMENT, String P_PERIOD, int P_YEAR, String P_CONCEP);

	void costManager1Drills(String P_PERIOD, int P_YEAR, String P_USERID);

	void costManager2Drills(String P_PERIOD, int P_YEAR, String P_USERID);

	void costManager3Drills(String P_PERIOD, int P_YEAR, String P_USERID);

	void costManager4Drills(String P_PERIOD, int P_YEAR, String P_USERID);

	void costManager5Drills(String P_PERIOD, int P_YEAR, String P_USERID);

	void costManager6Drills(String P_PERIOD, int P_YEAR, String P_USERID);

	void costManager7Drills(String P_PERIOD, int P_YEAR, String P_USERID);

	void costManager8Drills(String P_PERIOD, int P_YEAR, String P_USERID);

	void costManager9Drills(String P_PERIOD, int P_YEAR, String P_USERID);

	void rollUpValidations(int P_ORGID, String P_PERIOD, int P_YEAR, String P_SEGMENT, String P_USERID);

	void rollUpMatchAccounts(int P_ORGID, String P_PERIOD, int P_YEAR, String P_USERID);
}
