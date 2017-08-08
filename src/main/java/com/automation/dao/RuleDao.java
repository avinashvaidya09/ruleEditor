/**
 * 
 */
package com.automation.dao;

import java.util.List;

import com.automation.dto.Rule;

/**
 * @author AvinashVaidya
 *
 */
public interface RuleDao {
	
	/**
	 * This method fetches all the rules in the rule table
	 * 
	 * @return List of rules
	 * @throws Exception
	 */
	public List<Rule> fetchRules() throws Exception;
	
	/**
	 * This method fetches rule by id
	 * 
	 * @return Rule entity
	 * @throws Exception
	 */
	public Rule findRuleById(Integer ruleId) throws Exception;
	
	
	/**
	 * This method fetches rule by name
	 * 
	 * @return Rule entity
	 * @throws Exception
	 */
	public Rule findRuleByName(String ruleName) throws Exception;
	
	/**
	 * This method creates a new rule
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public Rule createRule(Rule ruleEntity) throws Exception;
	
	/**
	 * This method updates the existing rule
	 * 
	 * @return Rule entity
	 * @throws Exception
	 */
	public Rule updateRule(Rule ruleEntity) throws Exception;
	
	/**
	 * This method deletes the rule
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean deleteRule(Integer ruleId) throws Exception;

}
