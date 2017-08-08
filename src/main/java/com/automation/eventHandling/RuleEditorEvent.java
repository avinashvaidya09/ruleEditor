/**
 * 
 */
package com.automation.eventHandling;

import org.springframework.context.ApplicationEvent;

import com.automation.domain.RuleUi;

/**
 * @author AvinashVaidya
 *
 */
public class RuleEditorEvent extends ApplicationEvent{
	
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	private RuleUi ruleUiObj;

	public RuleEditorEvent(Object source, String message, RuleUi ruleUiObj) {
		super(source);
		this.message = message;
		this.ruleUiObj = ruleUiObj;
	}
	
	public String getMessage() {
        return message;
    }

	public RuleUi getRuleUiObj() {
		return ruleUiObj;
	}

	public void setRuleUiObj(RuleUi ruleUiObj) {
		this.ruleUiObj = ruleUiObj;
	}
	
	

}
