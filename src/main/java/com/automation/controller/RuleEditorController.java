/**
 * 
 */
package com.automation.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.automation.domain.RuleUiRequest;
import com.automation.domain.RulesUiResponse;
import com.automation.domain.WebSocketConnectResponse;

/**
 * @author AvinashVaidya
 *
 */

public interface RuleEditorController {
	
	
	
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String start();
	
	@MessageMapping(value = "/connect")
	@SendTo("/topic/showAlert")
	public WebSocketConnectResponse connect();
	
	
	/**
	 * This service is used to get all the active rules in the system.
	 * 
	 * @return RulesUiResponse
	 */
	@RequestMapping(value = "/getRules", method = RequestMethod.GET)
	public @ResponseBody RulesUiResponse getRules();
	
	/**
	 * This service is used to get all the active rules in the system.
	 * 
	 * @return RulesUiResponse
	 */
	@RequestMapping(value = "/getRuleByName/{ruleName}", method = RequestMethod.GET)
	public @ResponseBody RulesUiResponse getRuleByName(@PathVariable String ruleName);
	
	
	/**
	 * This service is used to create rule
	 * 
	 * @return RulesUiResponse
	 */
	@RequestMapping(value = "/createRule", method = RequestMethod.POST)
	public @ResponseBody RulesUiResponse createRule(@RequestBody RuleUiRequest uiRequest);
	
	
	/**
	 * This service is used to update rule
	 * 
	 * @return RulesUiResponse
	 */
	@RequestMapping(value = "/updateRule", method = RequestMethod.POST)
	public @ResponseBody RulesUiResponse updateRule(@RequestBody RuleUiRequest uiRequest);
	
	
	/**
	 * This service is used to update rule
	 * 
	 * @return RulesUiResponse
	 */
	@RequestMapping(value = "/deleteRule", method = RequestMethod.POST)
	public @ResponseBody RulesUiResponse deleteRule(@RequestBody RuleUiRequest uiRequest);

	
	
	
	
	

}
