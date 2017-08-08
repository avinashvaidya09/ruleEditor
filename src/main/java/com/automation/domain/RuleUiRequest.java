/**
 * 
 */
package com.automation.domain;

import java.util.List;

/**
 * @author AvinashVaidya
 *
 */
public class RuleUiRequest {
	
	private Integer ruleId;
	
	private String ruleName;
	
	private Integer locked;
	
	private String status;
	
	private  List<RuleConfigUi> ruleConfigUiList;

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public List<RuleConfigUi> getRuleConfigUiList() {
		return ruleConfigUiList;
	}

	public void setRuleConfigUiList(List<RuleConfigUi> ruleConfigUiList) {
		this.ruleConfigUiList = ruleConfigUiList;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
