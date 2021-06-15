package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.models.HfmRollupEntries;

public interface IHfmRollupEntriesService {

    public final static String P_CONCEPT_PAYABLES = "Payables";
    public final static String P_CONCEPT_PAYABLES1 = "Payables1";
    public final static String P_CONCEPT_PAYABLES2 = "Payables2";
    public final static String P_CONCEPT_PAYABLES3 = "Payables3";
    public final static String P_CONCEPT_PAYABLES4 = "Payables4";
    public final static String P_CONCEPT_PAYABLES5 = "Payables5";
    public final static String P_CONCEPT_RECEIVABLES = "Receivables";
    public final static String P_CONCEPT_RECEIVABLES1 = "Receivables1";
    public final static String P_CONCEPT_RECEIVABLES2 = "Receivables2";
    public final static String P_CONCEPT_RECEIVABLES3 = "Receivables3";
    public final static String P_CONCEPT_RECEIVABLES4 = "Receivables4";
    public final static String P_CONCEPT_PAYROLL = "Payroll";
    public final static String P_CONCEPT_ASSET = "Assets";
    public final static String P_CONCEPT_OTHER = "Others";
    public final static String P_COSTMANAGER = "COSTMANAGER";

    Optional<HfmRollupEntries> findById(Long id);

    List<HfmRollupEntries> findAll();

    HfmRollupEntries save(HfmRollupEntries entity);

    List<HfmRollupEntries> saveAll(List<HfmRollupEntries> entityList);

    void delete(HfmRollupEntries entity);

    void deleteAll(List<HfmRollupEntries> entityList);

    void rollUpStart(int P_ORGID, String P_PERIOD, String P_YEAR, String P_SEGMENT, String P_USERID);
    
    void rollDelData(int P_ORGID, String P_PERIOD, String P_YEAR, String P_SEGMENT, String P_USERID);

    void getHeaders(int P_ORGID, String P_SEGMENT, String P_PERIOD, String P_YEAR, String P_CONCEP, String P_USERID);

    void rollUpDrillCostMngDetGetHeaders(int p_orgid, String P_segment, String p_period, String p_year, String p_concept);

    void costManager1Drills(int p_orgid,String p_period, String p_year, String p_userid);

    void costManager2Drills(int p_orgid,String p_period, String p_year, String p_userid);

    void costManager3Drills(int p_orgid,String p_period, String p_year, String p_userid);

    void costManager4Drills(int p_orgid,String p_period, String p_year, String p_userid);

    void costManager5Drills(int p_orgid,String p_period, String p_year, String p_userid);

    void costManager6Drills(int p_orgid,String p_period, String p_year, String p_userid);

    void costManager7Drills(int p_orgid,String p_period, String p_year, String p_userid);

    void costManager8Drills(int p_orgid,String p_period, String p_year, String p_userid);

    void costManager9Drills(int p_orgid,String p_period, String p_year, String p_userid);

    void rollUpValidations(int P_ORGID, String P_PERIOD, String P_YEAR, String P_SEGMENT, String P_USERID);

    void rollUpMatchAccounts(int P_ORGID, String P_PERIOD, String P_YEAR, String P_USERID);
}
