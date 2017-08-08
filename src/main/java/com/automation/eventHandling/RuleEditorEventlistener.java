/**
 * 
 */
package com.automation.eventHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author AvinashVaidya
 *
 */
@Component
public class RuleEditorEventlistener implements ApplicationListener<RuleEditorEvent> {

	@Autowired
	SimpMessagingTemplate template;
	
	
	@Override
	public void onApplicationEvent(RuleEditorEvent event) {
		
		template.convertAndSend("/topic/showAlert",event.getRuleUiObj());
		
		
		
	}

}
