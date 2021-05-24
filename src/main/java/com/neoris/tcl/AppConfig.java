package com.neoris.tcl;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContextListener;

import org.apache.commons.codec.binary.Base64;
import org.apache.myfaces.spi.WebConfigProvider;
import org.apache.myfaces.spi.impl.DefaultWebConfigProvider;
import org.apache.myfaces.webapp.StartupServletContextListener;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.neoris.tcl.utils.ViewScope;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	private final static Logger LOG = LoggerFactory.getLogger(AppConfig.class);

	@Bean(name = "jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
//		String password = String.valueOf(Base64.decodeBase64("TjMwcjFzdGxj"));
//		LOG.info("password = {}", password);
		LOG.info("Configuring StringEncryptor...");
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("N30r1stlc");
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		return encryptor;
	}

	@Bean
	public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
		LOG.info("Creating ServletRegistrationBean...");
		FacesServlet servlet = new FacesServlet();
		ServletRegistrationBean<FacesServlet> servletRegistrationBean;
		servletRegistrationBean = new ServletRegistrationBean<FacesServlet>(servlet, "*.xhtml", "*.jsf", "/faces/*");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

	@Bean
	public ServletContextListener getContextListener() {
		LOG.info("Configuring ServletContextListener...");
		StartupServletContextListener listener = new StartupServletContextListener();
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
		viewScope.addScope(ViewScope.VIEW, new ViewScope());
		return viewScope;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		LOG.info("Configuring passwordEncoder...");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		LOG.info("Configuring addViewControllers...");
		// registry.addViewController("/").setViewName("/faces/index.xhtml");
		registry.addViewController("/").setViewName("/index.xhtml");
	}

}
