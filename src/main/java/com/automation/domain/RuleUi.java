package com.automation.domain;

import java.util.ArrayList;
import java.util.List;

public class RuleUi {
	
private Integer ruleId;
	
	private String ruleName;
	
	private String status;
	
	private Integer locked;
	
	private boolean isRuleDeleted;
	
	private List<RuleConfigUi> ruleConfigUiList = new ArrayList<RuleConfigUi>(0);

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	
	
	public List<RuleConfigUi> getRuleConfigUiList() {
		return ruleConfigUiList;
	}

	public void setRuleConfigUiList(List<RuleConfigUi> ruleConfigUiList) {
		this.ruleConfigUiList = ruleConfigUiList;
	}

	public void addConfig(RuleConfigUi ruleConfig){
		this.ruleConfigUiList.add(ruleConfig);
	}

	public boolean isRuleDeleted() {
		return isRuleDeleted;
	}

	public void setRuleDeleted(boolean isRuleDeleted) {
		this.isRuleDeleted = isRuleDeleted;
	}
	
	
	

}
