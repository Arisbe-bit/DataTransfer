package com.neoris.tcl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.beans.RollUpMessage;
//import com.neoris.tcl.utils.Functions;
import com.neoris.tcl.websocket.WebSocketConfig;


@Controller
public class PushController {
	
	private final static Logger LOG = LoggerFactory.getLogger(PushController.class);

	@MessageMapping(WebSocketConfig.WS_ROLLUPS_MAPPING)
	@SendTo(WebSocketConfig.WS_ROLLUPS_TOPIC + WebSocketConfig.WS_ROLLUPS_MAPPING)
	public RollUpMessage pushStatus(RollUpMessage message) {
		LOG.info("[pushStatus] recibo mensage = {}", message);
		message.setiClass("class-from-server");
		message.setMessage(message.getMessage() + ", y esto es desde el server!!!");
		return message;
	}

}
