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

import com.neoris.tcl.dao.ITradingPartnerTypesDao;
import com.neoris.tcl.model.TradingPartnerTypes;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class TradingPartnerTypesService implements ITradingPartnerTypesService{

    @Autowired
    private ITradingPartnerTypesDao data;

	@Override
	public List<TradingPartnerTypes> listar() {
		 return (List<TradingPartnerTypes>) data.findAll();
	}

	
	@Override
	public Page<TradingPartnerTypes> findPaginated(Pageable pageable, List<TradingPartnerTypes> tradingTypesList) {
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<TradingPartnerTypes> list;

        if (tradingTypesList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, tradingTypesList.size());
            list = tradingTypesList.subList(startItem, toIndex);
        }
        Page<TradingPartnerTypes> TPTypePage = new PageImpl<TradingPartnerTypes>(list, PageRequest.of(currentPage, pageSize),
        		tradingTypesList.size());
        return TPTypePage;
	}

	@Override
	public TradingPartnerTypes save(TradingPartnerTypes tptype) {
		return data.save(tptype);
	}

	@Override
	public void delete(TradingPartnerTypes tptype) {
		data.delete(tptype);		
	}

	@Override
	public void deleteAll(List<TradingPartnerTypes> lstTPtype) {
		data.deleteAll(lstTPtype);    
	}

	@Override
	public Optional<TradingPartnerTypes> listarID(String tptype) {
		 return data.findById(tptype);
	}
    
    
}
