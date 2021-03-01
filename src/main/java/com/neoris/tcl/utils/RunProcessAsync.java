package com.neoris.tcl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.neoris.tcl.services.HFMcodesService;

public class RunProcessAsync implements Runnable {

	private final static Logger LOG = LoggerFactory.getLogger(RunProcessAsync.class);

	@Autowired
	private HFMcodesService service;

	@Override
	public void run() {
		LOG.info("service listar= {}", service.listar());
	}

}
