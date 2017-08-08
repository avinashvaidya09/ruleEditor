package com.automation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author AvinashVaidya
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class AppWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/connect").withSockJS();
		
	}
	
	public void configureMessageBroker(MessageBrokerRegistry config){
		// use topic when you want to use pub sub or use queue for peer to peer
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/ruleApp");
	}

}