package com.neoris.tcl.controller;

import javax.annotation.PostConstruct;

import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller(value = "menuController")
@RequestScope
public class MenuController {
    
    private final static Logger LOG = LoggerFactory.getLogger(MenuController.class);
    private final static String REDIRECT = "%s?faces-redirect=true";
    private MenuModel model;

    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
//        LOG.info("Building model for menu");
//
//        //First submenu
//        DefaultSubMenu firstSubmenu = DefaultSubMenu.builder()
//                .label("Dynamic Submenu")
//                .build();
//
//        DefaultMenuItem item = DefaultMenuItem.builder()
//                .value("External")
//                .url("http://www.primefaces.org")
//                .icon("pi pi-home")
//                .build();
//        firstSubmenu.getElements().add(item);
//
//        model.getElements().add(firstSubmenu);
//
//        //Second submenu
//        DefaultSubMenu secondSubmenu = DefaultSubMenu.builder()
//                .label("Dynamic Actions")
//                .build();
//
//        item = DefaultMenuItem.builder()
//                .value("Save")
//                .icon("pi pi-save")
//                .command("#{menuView.save}")
//                .update("messages")
//                .build();
//        secondSubmenu.getElements().add(item);
//
//        item = DefaultMenuItem.builder()
//                .value("Delete")
//                .icon("pi pi-times")
//                .command("#{menuView.delete}")
//                .ajax(false)
//                .build();
//        secondSubmenu.getElements().add(item);
//
//        item = DefaultMenuItem.builder()
//                .value("Redirect")
//                .icon("pi pi-search")
//                .command("#{menuView.redirect}")
//                .build();
//        secondSubmenu.getElements().add(item);
//
//        model.getElements().add(secondSubmenu);
    }
    
    public String hfmcodes() {
        return String.format(REDIRECT, "hfmcodes");
    }
    
    public String hfmcodesOA() {
        return String.format(REDIRECT, "hfmcodesOA");
    }
    
    public String partners() {
        return String.format(REDIRECT, "partners");
    }
    
    public String payablesAccounts() {
        return String.format(REDIRECT, "payablesAccounts");
    }
    
    public String receivablesAccounts() {
        return String.format(REDIRECT, "receivablesAccounts");
    }
    
    public String reclassification() {
        return String.format(REDIRECT, "reclassification");
    }
    
    public String matchAccounts() {
        return String.format(REDIRECT, "matchAccounts");
    }

    public String dsvscompany() {
        return String.format(REDIRECT, "dsvscompany");
    }
    
    public String rollup() {
        return String.format(REDIRECT, "rollup");
    }

    public MenuModel getModel() {
        return model;
    }
    
    public String getDataSourceVsCompany() {
        return "DataSource Vs Company";
    }
    
    public String getRollUpText() {
        return "RollUp";
    }
    
    public String getReclasificationText() {
        return "Reclasification";
    }
    
    public String getHfmCodesOAText() {
        return "Hfm Codes OA";
    }
    
}
