package com.neoris.tcl.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.neoris.tcl.dao.ITradingPartnerDao;
import com.neoris.tcl.model.TradingPartner;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class TradingPartnerService implements ITradingPartnerService {

	 @Autowired
	    private ITradingPartnerDao data;
	 
	@Override
	public List<TradingPartner> listar() {
		return (List<TradingPartner>) data.findAll();
	}

	@Override
	public Page<TradingPartner> findPaginated(Pageable pageable, List<TradingPartner> ICPcodeList) {
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TradingPartner> list;

        if (ICPcodeList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, ICPcodeList.size());
            list = ICPcodeList.subList(startItem, toIndex);
        }
        Page<TradingPartner> IPCcodesPage = new PageImpl<TradingPartner>(list, PageRequest.of(currentPage, pageSize),
        		ICPcodeList.size());
        return IPCcodesPage;
	}

	@Override
	public TradingPartner save(TradingPartner ICPcode) {
		return data.save(ICPcode);
	}

	@Override
	public void delete(TradingPartner ICPcode) {
		data.delete(ICPcode);	
		
	}

	@Override
	public void deleteAll(List<TradingPartner> lstICPcodes) {
		data.deleteAll(lstICPcodes);  
		
	}

	@Override
	public Optional<TradingPartner> listarID(String ICPcode) {
		return data.findById(ICPcode);
	}

   
}
