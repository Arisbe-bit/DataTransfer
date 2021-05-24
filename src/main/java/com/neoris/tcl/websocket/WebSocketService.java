package com.neoris.tcl.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.neoris.tcl.beans.RollUpMessage;

@Service
public class WebSocketService {

	private final static Logger LOG = LoggerFactory.getLogger(WebSocketService.class);
	private final String destination = WebSocketConfig.WS_ROLLUPS_TOPIC + WebSocketConfig.WS_ROLLUPS_MAPPING;

	@Autowired
	private final SimpMessagingTemplate messageTemplate;

	public WebSocketService(SimpMessagingTemplate messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	/**
	 * 
	 * @param message
	 */
	public void notyfyRollUpProcess(RollUpMessage message) {
		LOG.info("About to send message: {}", message);
		try {
			messageTemplate.convertAndSend(destination, message);
		} catch (Exception e) {
			LOG.error("Exception sending message: {}", e.getMessage());
		} finally {
			LOG.info("Message Sent!");
		}		
	}
}
