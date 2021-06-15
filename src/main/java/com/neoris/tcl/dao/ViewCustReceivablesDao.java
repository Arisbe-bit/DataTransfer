package com.neoris.tcl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neoris.tcl.models.ViewCustReceivables;
import java.util.List;

public interface ViewCustReceivablesDao extends JpaRepository<ViewCustReceivables, String> {

	List<ViewCustReceivables> findByOrganizationid(int organizationid);
}
