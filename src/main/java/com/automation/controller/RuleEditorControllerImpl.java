/**
 * 
 */
package com.automation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.automation.domain.RuleEditorError;
import com.automation.domain.RuleUi;
import com.automation.domain.RuleUiRequest;
import com.automation.domain.RulesUiResponse;
import com.automation.domain.WebSocketConnectResponse;
import com.automation.service.RuleEditorService;

/**
 * @author AvinashVaidya
 *
 */
@RestController
@RequestMapping("/ruleEditorService")
public class RuleEditorControllerImpl implements RuleEditorController {
	
	private static Logger RULELOGGER = LoggerFactory.getLogger(RuleEditorControllerImpl.class);

	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	RuleEditorService ruleEditorService;
	
	
	

	
	@Override
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String start() {
		
		WebSocketConnectResponse wsresp = new WebSocketConnectResponse("Test");
		template.convertAndSend("/topic/showAlert",wsresp);
		return "start";
	}
	
	
	@Override
	@MessageMapping(value = "/connect")
	@SendTo("/topic/showAlert")
	public WebSocketConnectResponse connect() {
		
		WebSocketConnectResponse result = new WebSocketConnectResponse("Connected");
		return result;
	}


	@Override
	@RequestMapping(value = "/getRules", method = RequestMethod.GET)
	public @ResponseBody RulesUiResponse getRules() {
		RulesUiResponse uiResponse = new RulesUiResponse();
		try {
			ruleEditorService.getRules(uiResponse);
		} catch (Exception e) {
			RULELOGGER.error("Error while fetching active rules" + e);
			if(e != null && e.getMessage() != null){
				uiResponse.addErrors(new RuleEditorError("1000",e.getMessage()));
			}else{
				uiResponse.addErrors(new RuleEditorError("1111","Error while getting the active rules. Please check logs for more details."));
			}
		}
		
		return uiResponse;
	}

	
	@RequestMapping(value = "/getRuleByName/{ruleName}", method = RequestMethod.GET)
	public @ResponseBody RulesUiResponse getRuleByName(@PathVariable String ruleName){
		
		RulesUiResponse uiResponse = new RulesUiResponse();
		try {
			ruleEditorService.getRuleByName(ruleName,uiResponse);
		} catch (Exception e) {
			RULELOGGER.error("Error while fetching rule" + e);
			if(e != null && e.getMessage() != null){
				uiResponse.addErrors(new RuleEditorError("5000",e.getMessage()));
			}else{
				uiResponse.addErrors(new RuleEditorError("5555","Error while getting the rule. Please check logs for more details."));
			}
		}
		
		return uiResponse;
		
		
	}

	@Override
	@RequestMapping(value = "/createRule", method = RequestMethod.POST)
	public @ResponseBody RulesUiResponse createRule(@RequestBody RuleUiRequest uiRequest) {
		RulesUiResponse uiResponse = new RulesUiResponse();
		try {
			ruleEditorService.createRule(uiRequest, uiResponse);
		} catch (Exception e) {
			RULELOGGER.error("Error while creating rule" + e);
			if(e != null && e.getMessage() != null){
				uiResponse.addErrors(new RuleEditorError("2000",e.getMessage()));
			}else{
				uiResponse.addErrors(new RuleEditorError("2222","Error while creating rule. Please check logs for more details."));
			}
		}
		
		
		return uiResponse;
	}


	@Override
	@RequestMapping(value = "/updateRule", method = RequestMethod.POST)
	public @ResponseBody RulesUiResponse updateRule(@RequestBody RuleUiRequest uiRequest) {
		RulesUiResponse uiResponse = new RulesUiResponse();
		
		try {
			List<RuleUi> ruleUiList = ruleEditorService.updateRule(uiRequest);
			uiResponse.setRuleUiList(ruleUiList);
		} catch (Exception e) {
			RULELOGGER.error("Error while updating rule" + e);
			if(e != null && e.getMessage() != null){
				uiResponse.addErrors(new RuleEditorError("3000",e.getMessage()));
			}else{
				uiResponse.addErrors(new RuleEditorError("3333","Error while updating rule. Please check logs for more details."));
			}
		}
		
		return uiResponse;
	}


	@Override
	@RequestMapping(value = "/deleteRule", method = RequestMethod.POST)
	public @ResponseBody RulesUiResponse deleteRule(@RequestBody RuleUiRequest uiRequest) {
		RulesUiResponse uiResponse = new RulesUiResponse();
		
		try {
			ruleEditorService.deleteRule(uiRequest, uiResponse);
		} catch (Exception e) {
			RULELOGGER.error("Error while deleting rule" + e);
			if(e != null && e.getMessage() != null){
				uiResponse.addErrors(new RuleEditorError("4000",e.getMessage()));
			}else{
				uiResponse.addErrors(new RuleEditorError("4444","Error while deleting rule. Please check logs for more details."));
			}
		}
		
		return uiResponse;
	}

}
