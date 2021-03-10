package com.neoris.tcl.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.beans.RollUpBean;
import com.neoris.tcl.utils.ViewScope;

@Controller
@Scope(ViewScope.VIEW)
public class RollupController {

	private final static Logger LOG = LoggerFactory.getLogger(RollupController.class);

	private RollUpBean rollUpBean;

	@Autowired
	@Qualifier("mapMonths")
	private Map<String, Integer> months;

	private Map<String, Integer> mapEntities;

	@PostConstruct
	public void init() {

		rollUpBean = new RollUpBean();

		LOG.info("months = {}", months);

		mapEntities = new HashMap<String, Integer>();
		for (int i = 1; i <= 10; i++) {
			mapEntities.put(String.format("Entity %s", i), i);
		}

	}

	public String getPending() {
		return "Pending";
	}

	public String getImageCemex() {
		return "/resources/img/loading.gif";
	}

	public void run(ActionEvent event) {
		LOG.info("Running process with rollUpBean = {}", rollUpBean);
	}

	public Map<String, Integer> getMonths() {
		return months;
	}

	public void setMonths(Map<String, Integer> months) {
		this.months = months;
	}

	public Map<String, Integer> getMapEntities() {
		return mapEntities;
	}

	public void setMapEntities(Map<String, Integer> mapEntities) {
		this.mapEntities = mapEntities;
	}

	public RollUpBean getRollUpBean() {
		return rollUpBean;
	}

	public void setRollUpBean(RollUpBean rollUpBean) {
		this.rollUpBean = rollUpBean;
	}

}
