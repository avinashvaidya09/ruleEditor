/**
 * 
 */
package com.automation.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AvinashVaidya
 *
 */
public class RulesUiResponse extends RuleEditorErrorResponse {
	
	private static final long serialVersionUID = 1L;
	
	List<RuleUi> ruleUiList = new ArrayList<RuleUi>(0);
	
	boolean deleteStatus;

	public List<RuleUi> getRuleUiList() {
		return ruleUiList;
	}

	public void setRuleUiList(List<RuleUi> ruleUiList) {
		this.ruleUiList = ruleUiList;
	}

	public void addRule(RuleUi ruleUiObj){
		this.ruleUiList.add(ruleUiObj);
	}

	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	
	

}
