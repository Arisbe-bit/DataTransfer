package com.neoris.tcl;

import java.time.Month;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContextListener;

import org.apache.myfaces.spi.WebConfigProvider;
import org.apache.myfaces.spi.impl.DefaultWebConfigProvider;
import org.apache.myfaces.webapp.StartupServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.neoris.tcl.utils.ViewScope;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    
    private final static Logger LOG = LoggerFactory.getLogger(AppConfig.class);
    
    @Bean
    public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
        LOG.info("Creating ServletRegistrationBean...");
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean<FacesServlet> servletRegistrationBean = 
                new ServletRegistrationBean<FacesServlet>(servlet,"*.xhtml", "*.jsf", "/faces/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }
    
    @Bean
    public ServletContextListener listener() {
        LOG.info("Creating ServletContextListener...");
        ServletContextListener listener = new StartupServletContextListener();
        return listener;
    }
    
    @Bean
    public WebConfigProvider webConfig() {
        LOG.info("Creating WebConfigProvider...");
        WebConfigProvider provider = new DefaultWebConfigProvider();
        return provider;
    }
    
    @Bean
    public static CustomScopeConfigurer viewScope() {
        LOG.info("Configuring Custom View Scope...");
        CustomScopeConfigurer viewScope = new CustomScopeConfigurer();
        viewScope.addScope("view", new ViewScope());
        return viewScope;
    }
    
    @Bean("mapMonths")
    public Map<String, Integer> mapMonths() {
        LOG.info("Configuring Map For Month Names...");
        Map<String, Integer> months = new LinkedHashMap<>();        
        for (int i = 1; i <= Month.values().length; i++) {
            months.put(Month.of(i).toString(), i);
        }
        return months;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        LOG.info("Configuring addViewControllers...");
        // registry.addViewController("/").setViewName("/faces/index.xhtml");
        registry.addViewController("/").setViewName("/index.xhtml");
    }


}
