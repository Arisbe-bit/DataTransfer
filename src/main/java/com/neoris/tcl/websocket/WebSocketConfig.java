package com.neoris.tcl.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer  {
	
	private static final Logger LOG = LoggerFactory.getLogger(WebSocketConfig.class);
	public static final String WS_ROLLUPS_ENDPOINT = "/wsrollups";
	public static final String WS_ROLLUPS_TOPIC  = "/topic";
	public static final String WS_ROLLUPS_APP 	 = "/app";
	public static final String WS_ROLLUPS_MAPPING = "/rollups";
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		LOG.info("[configureMessageBroker] enableSimpleBroker => {}", WS_ROLLUPS_TOPIC);
		config.enableSimpleBroker(WS_ROLLUPS_TOPIC);
		LOG.info("[configureMessageBroker] setApplicationDestinationPrefixes  => {}", WS_ROLLUPS_APP);
		config.setApplicationDestinationPrefixes(WS_ROLLUPS_APP);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		RequestUpgradeStrategy upgradeStrategy = new TomcatRequestUpgradeStrategy();
		LOG.info("[registerStompEndpoints] Registering Stomp Endpoint => {}", WS_ROLLUPS_ENDPOINT);
		registry.addEndpoint(WS_ROLLUPS_ENDPOINT)
		 		.setHandshakeHandler(new DefaultHandshakeHandler(upgradeStrategy))
				.addInterceptors(new HttpSessionHandshakeInterceptor())
				.withSockJS();
	}

}
