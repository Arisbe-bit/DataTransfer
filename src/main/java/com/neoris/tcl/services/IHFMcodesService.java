package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.neoris.tcl.model.HFMcodes;

public interface IHFMcodesService {

    List<HFMcodes> listar();
    Page<HFMcodes> findPaginated(Pageable pageable, List<HFMcodes> hfmCodesList);
    HFMcodes save(HFMcodes hfMcodes);
    void delete(HFMcodes hfmcodes);
    void deleteAll(List<HFMcodes> lstHfmcodes);
    Optional<HFMcodes> listarID(String code);

}