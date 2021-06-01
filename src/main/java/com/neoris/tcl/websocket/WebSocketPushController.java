package com.neoris.tcl.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.neoris.tcl.beans.RollUpMessage;

@Controller
public class WebSocketPushController {

	private final static Logger LOG = LoggerFactory.getLogger(WebSocketPushController.class);

	@MessageMapping(WebSocketConfig.WS_ROLLUPS_MAPPING)
	@SendTo(WebSocketConfig.WS_ROLLUPS_TOPIC + WebSocketConfig.WS_ROLLUPS_MAPPING)
	public RollUpMessage pushStatus(RollUpMessage message) {
		LOG.info("[pushStatus] recibo mensage = {}", message);
		return message;
	}

}
