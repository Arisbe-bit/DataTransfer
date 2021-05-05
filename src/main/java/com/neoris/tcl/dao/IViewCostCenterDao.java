package com.neoris.tcl.dao;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.tcl.models.ViewCostCenter;

@Repository
public interface IViewCostCenterDao extends JpaRepository<ViewCostCenter, Id>{

}
