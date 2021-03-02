package com.neoris.tcl.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.neoris.tcl.model.TradingPartner;
public interface ITradingPartnerService {

	    List<TradingPartner> listar();
	    Page<TradingPartner> findPaginated(Pageable pageable, List<TradingPartner> ICPcodeList);
	    TradingPartner save(TradingPartner ICPcode);
	    void delete(TradingPartner ICPcode);
	    void deleteAll(List<TradingPartner> lstICPcodes);
	    Optional<TradingPartner> listarID(String ICPcode);

}