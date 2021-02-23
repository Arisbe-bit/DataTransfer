package com.neoris.tcl.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.neoris.tcl.model.HFMcodes;
import com.neoris.tcl.services.IHFMcodesService;

@Controller(value = "hfmControllerBean")
@Scope(value = "session")
public class HfmController {
    private final static Logger LOG = LoggerFactory.getLogger(HfmController.class);
    
    @Autowired
    private IHFMcodesService service;
    
    @Autowired
    private HttpSession httpSession;
    
    public String index(Model model) {       
        model.addAttribute("title", "Main Page");
        cleanSession();
        return "index";
    }
    
    /**
     * 
     * @param model
     * @param page
     * @param size
     * @return
     */
    public String hfmCodesPage(Model model, 
            @RequestParam("page") Optional<Integer> page, 
            @RequestParam("size") Optional<Integer> size) {
        
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        List<HFMcodes> hfmcodes = getHFMcodesList(page.isPresent());
        Page<HFMcodes> hfmCodesPage = listToPage(hfmcodes, currentPage, pageSize);

        model.addAttribute("HFMcodesPage", hfmCodesPage);
        model.addAttribute("title", "HFM Codes");
        
        int totalPages = hfmCodesPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    
        return "hfmcodes";
    }

    /**
     * 
     * @param model
     * @return
     */
    public String Listar(Model model) {
        cleanSession();
        model.addAttribute("title", "HFM Codes Accounts");
        model.addAttribute("message", "This is hfmcodes-accounts");
        return "hfmcodes-accounts";
    }
 
    public String hfmcodesEdit(@RequestParam Optional<String> code, Model model) {
        if(code.isPresent()) {
            LOG.info("hfmcodesEdit:Id = {}", code.get());
            model.addAttribute("hfmcodes", service.listarID(code.get()));
        } else {
            LOG.info("hfmcodesEdit:Id no presente. Creando nuevo.");
            model.addAttribute("hfmcodes", new HFMcodes());
        }
        return "hfmcodes-edit";
    }
    
    public String hfmcodesDeletePost(HFMcodes hfmcodes, Model model) {
        LOG.info("Entering to delete in POST code = {}", hfmcodes.getHfmcode());
        service.delete(hfmcodes);
        model.addAttribute("message", String.format("HFM Code [%s] deleted", hfmcodes.getHfmcode()));
        return this.hfmCodesPage(model, Optional.empty(), Optional.empty());
    }
    
    public String hfmcodesDelete(@RequestParam String code, Model model) {
        LOG.info("Entering to delete in GET code = {}", code);
        Optional<HFMcodes> hfmcodes = service.listarID(code);
        if(hfmcodes.isPresent()) {
            service.delete(hfmcodes.get());
            model.addAttribute("message", String.format("HFM Code [%s] deleted", code));
        } else {
            model.addAttribute("message", String.format("HFM Code [%s] NOT found. Not Deleted!!", code));
        }        
        return this.hfmCodesPage(model, Optional.empty(), Optional.empty());
    }
    
    @PostMapping("/hfmcodes-save")
    public String hfmcodesSave(HFMcodes hfmcodes, Model model) {
        cleanSession();
        LOG.info("hfmcodesSave: hfmcodes = {}", hfmcodes);
        hfmcodes = service.save(hfmcodes);
        LOG.info("hfmcodesSave: hfmcodes saved = {}", hfmcodes);
        return this.hfmCodesPage(model, Optional.empty(), Optional.empty());
    }
    
    /**
     * 
     * @param hfmcodes
     * @param currentPage
     * @param pageSize
     * @return
     */
    private Page<HFMcodes> listToPage(List<HFMcodes> hfmcodes, int currentPage, int pageSize) {
        Page<HFMcodes> page = service.findPaginated(PageRequest.of(currentPage - 1, pageSize), hfmcodes);
        return page;
    }

    /**
     * 
     * @param isNew
     * @return
     */
    private List<HFMcodes> getHFMcodesList(boolean isPresent) {
        List<HFMcodes> hfmcodes = null;
        if(!isPresent) {
            LOG.info("getting List from Service");
            hfmcodes = service.listar();
            httpSession.setAttribute("HFMcodes", hfmcodes);
        } else {
            LOG.info("getting List from User Session...");
            hfmcodes = (List<HFMcodes>) httpSession.getAttribute("HFMcodes");
        }
        return hfmcodes;
    }
    
    private void cleanSession() {
        httpSession.removeAttribute("HFMcodes");
    }
}
