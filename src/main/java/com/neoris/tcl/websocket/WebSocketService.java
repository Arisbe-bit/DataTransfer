package com.neoris.tcl.websocket;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.neoris.tcl.beans.RollUpMessage;
import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.services.IHfmRollupEntriesService;

@Service
public class WebSocketService {

	private final static Logger LOG = LoggerFactory.getLogger(WebSocketService.class);
	private final String destination = WebSocketConfig.WS_ROLLUPS_TOPIC + WebSocketConfig.WS_ROLLUPS_MAPPING;

	@Autowired
	private final SimpMessagingTemplate messageTemplate;
	private IHfmRollupEntriesService rollUpService;

	public WebSocketService(SimpMessagingTemplate messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	/**
	 * 
	 * @param message
	 */
	public void notyfyRollUpProcess(RollUpMessage message) {
		if(message == null) {
			return;
		}
		LOG.info("About to send message: {}", message);
		try {
			messageTemplate.convertAndSend(destination, message);
		} catch (Exception e) {
			LOG.error("Exception sending message: {}", e.getMessage());
		} finally {
			LOG.info("Message Sent!");
		}
	}

	/**
	 * Update the status of a given rollUp
	 * 
	 * @param rollup
	 */
	public void sendPushNotification(HfmRollupEntries rollup) {
		sendPushNotification("", "", "", rollup);
	}

	/**
	 * Send a message to the clients subscribed to this topic
	 * 
	 * @param message
	 * @param title
	 * @param severity
	 */
	public void sendPushNotification(String message, String title, String severity) {
		sendPushNotification(message, title, severity, null);
	}

	/**
	 * Sends a Push notification to all clients for update the status of the current
	 * rollup
	 * 
	 * @param message  .- Optional message to show in notification browser
	 * @param title    .- Title of the optional message
	 * @param severity . Severity of message (info, warn, error)
	 * @param rollup   .- Current rollup been processed.
	 */
	public void sendPushNotification(String message, String title, String severity, HfmRollupEntries rollup) {
		RollUpMessage rum = new RollUpMessage(message, title, severity, rollup);
		this.notyfyRollUpProcess(rum);
	}

	/**
	 * Search and update the status of a processed rollup in database with a message
	 * 
	 * @param companyId .- Company ID to search.
	 * @param message   .- Optional message to show in notification browser
	 * @param title     .- Title of the optional message
	 * @param severity  . Severity of message (info, warn, error)
	 */
	public void sendPushNotification(Long companyId, String message, String title, String severity) {
		Optional<HfmRollupEntries> ru = rollUpService.findById(companyId);
		RollUpMessage rum = new RollUpMessage(message, title, severity, ru.orElse(null));
		this.notyfyRollUpProcess(rum);
	}

	/**
	 * Search and update the status of a processed rollup in database
	 * 
	 * @param companyId .- Company ID to search.
	 */
	public void sendPushNotification(Long companyId) {
		sendPushNotification(companyId, "", "", "");
	}

	public void setRollUpService(IHfmRollupEntriesService rollUpService) {
		this.rollUpService = rollUpService;
	}

}
