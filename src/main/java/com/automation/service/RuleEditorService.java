/**
 * 
 */
package com.automation.service;

import java.util.List;

import com.automation.domain.RuleUi;
import com.automation.domain.RuleUiRequest;
import com.automation.domain.RulesUiResponse;
import com.automation.dto.Rule;

/**
 * @author AvinashVaidya
 *
 */
public interface RuleEditorService {

	void getRules(RulesUiResponse uiResponse) throws Exception;

	void createRule(RuleUiRequest uiRequest, RulesUiResponse uiResponse) throws Exception;

	List<RuleUi> updateRule(RuleUiRequest uiRequest) throws Exception;

	List<RuleUi> deleteRule(RuleUiRequest uiRequest, RulesUiResponse uiResponse) throws Exception;

	void getRuleByName(String ruleName, RulesUiResponse uiResponse) throws Exception;

}
