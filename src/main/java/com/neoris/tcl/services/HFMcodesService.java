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

import com.neoris.tcl.dao.IHFMcodes;
import com.neoris.tcl.model.HFMcodes;

@Scope(value = WebApplicationContext.SCOPE_SESSION)
@Service()
public class HFMcodesService implements IHFMcodesService {

    @Autowired
    private IHFMcodes data;

    @Override
    public List<HFMcodes> listar() {
        return (List<HFMcodes>) data.findAll();
    }

    @Override
    public Page<HFMcodes> findPaginated(Pageable pageable, List<HFMcodes> hfmCodesList) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<HFMcodes> list;

        if (hfmCodesList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, hfmCodesList.size());
            list = hfmCodesList.subList(startItem, toIndex);
        }
        Page<HFMcodes> hfmCodesPage = new PageImpl<HFMcodes>(list, PageRequest.of(currentPage, pageSize),
                hfmCodesList.size());
        return hfmCodesPage;
    }

    @Override
    public HFMcodes save(HFMcodes hfMcodes) {
        return data.save(hfMcodes);
    }

    @Override
    public void delete(HFMcodes hfmcodes) {
        data.delete(hfmcodes);
    }

    @Override
    public Optional<HFMcodes> listarID(String code) {
        return data.findById(code);
    }

    @Override
    public void deleteAll(List<HFMcodes> lstHfmcodes) {
        data.deleteAll(lstHfmcodes);        
    }
}
