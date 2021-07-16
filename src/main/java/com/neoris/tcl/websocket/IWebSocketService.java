package com.neoris.tcl.websocket;

import com.neoris.tcl.beans.RollUpMessage;
import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.services.IHfmRollupEntriesService;

public interface IWebSocketService {

	/**
	 * 
	 * @param message
	 */
	void notyfyRollUpProcess(RollUpMessage message);

	/**
	 * Update the status of a given rollUp
	 * 
	 * @param rollup
	 */
	void sendPushNotification(HfmRollupEntries rollup);

	/**
	 * Send a message to the clients subscribed to this topic
	 * 
	 * @param message
	 * @param title
	 * @param severity
	 */
	void sendPushNotification(String message, String title, String severity);

	/**
	 * Sends a Push notification to all clients for update the status of the current
	 * rollup
	 * 
	 * @param message  .- Optional message to show in notification browser
	 * @param title    .- Title of the optional message
	 * @param severity . Severity of message (info, warn, error)
	 * @param rollup   .- Current rollup been processed.
	 * @param processFinished .- Flag to indicate process is running or finished
	 */
	void sendPushNotification(String message, String title, String severity, HfmRollupEntries rollup);

	/**
	 * Search and update the status of a processed rollup in database with a message
	 * 
	 * @param companyId .- Company ID to search.
	 * @param message   .- Optional message to show in notification browser
	 * @param title     .- Title of the optional message
	 * @param severity  . Severity of message (info, warn, error)
	 */
	void sendPushNotification(Long companyId, String message, String title, String severity);

	/**
	 * Search and update the status of a processed rollup in database
	 * 
	 * @param companyId .- Company ID to search.
	 */
	void sendPushNotification(Long companyId);
	
	/**
	 * Send notification on process ending.
	 */
	void sendProcessFinished();

	/**
	 * Setter method for rollUp service.
	 * @param rollUpService
	 */
	void setRollUpService(IHfmRollupEntriesService rollUpService);

}