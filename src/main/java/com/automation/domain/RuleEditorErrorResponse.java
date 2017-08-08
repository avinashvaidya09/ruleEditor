package com.automation.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RuleEditorErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<RuleEditorError> ruleInfoErrors;

	public List<RuleEditorError> getRuleInfoErrors() {
		return ruleInfoErrors;
	}

	public void setRuleInfoErrors(List<RuleEditorError> ruleInfoErrors) {
		this.ruleInfoErrors = ruleInfoErrors;
	}
	
	public void addErrors(RuleEditorError ruleInfoError) {
		if (this.ruleInfoErrors == null) {
			this.ruleInfoErrors = new ArrayList<RuleEditorError>();
			this.ruleInfoErrors.add(ruleInfoError);
		} else {
			this.ruleInfoErrors.add(ruleInfoError);
		}

	}

	public boolean hasErrors() {
		boolean errorFlag = false;
		if (this.ruleInfoErrors != null && this.ruleInfoErrors.size() > 0) {
			errorFlag = true;
		}
		return errorFlag;
	}
	
	



}
