/**
 * 
 */
package com.automation.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.automation.domain.RuleConfigUi;
import com.automation.domain.RuleUi;
import com.automation.domain.RuleUiRequest;
import com.automation.dto.Rule;
import com.automation.dto.RuleConfig;

/**
 * @author AvinashVaidya
 *
 */
public class RuleMapper {

	/**
	 * This method maps rule entity object to rule ui object.
	 * 
	 * @param ruleList
	 * @param uiResponse
	 */
	public static List<RuleUi> mapRulesToUiResponse(List<Rule> ruleList) {
		
		List<RuleUi> ruleUiList = new ArrayList<RuleUi>(0);
		
		for(Rule ruleEntity : ruleList){
			
			RuleUi ruleUiObj = new RuleUi();
			ruleUiObj.setRuleId(ruleEntity.getRuleId());
			ruleUiObj.setRuleName(ruleEntity.getRuleName());
			ruleUiObj.setStatus(ruleEntity.getStatus());
			
			
				for(RuleConfig ruleConfigEntity : ruleEntity.getRuleConfigurations()){
					
					RuleConfigUi ruleObj = new RuleConfigUi();
					ruleObj.setConfigId(ruleConfigEntity.getConfigId());
					ruleObj.setConfigName(ruleConfigEntity.getConfigName());
					ruleObj.setConfigValue(ruleConfigEntity.getConfigValue());
					ruleUiObj.addConfig(ruleObj);
					
				}
				
				ruleUiList.add(ruleUiObj);
				
		}
		
		return ruleUiList;
	}

	/**
	 * This method maps rule ui object to rule entity obj
	 * 
	 * @param uiRequest
	 * @return Rule
	 */
	public static Rule createRuleEntityObj(RuleUiRequest uiRequest) {
		
		Rule ruleEntity = new Rule();
		
		ruleEntity.setRuleName(uiRequest.getRuleName());
		
		if(StringUtils.isNotEmpty(uiRequest.getStatus())){
			ruleEntity.setStatus(uiRequest.getStatus());
		}
		
		
		for(RuleConfigUi ruleConfigUiObj : uiRequest.getRuleConfigUiList()){
			
			RuleConfig ruleConfigEnity = new RuleConfig();
			
			ruleConfigEnity.setConfigName(ruleConfigUiObj.getConfigName());
			ruleConfigEnity.setConfigValue(ruleConfigUiObj.getConfigValue());
			ruleConfigEnity.setRule(ruleEntity);
			ruleEntity.addRuleConfig(ruleConfigEnity);
			
		}
		
		return ruleEntity;
	}
	
	
	/**
	 * This method updates the current entity
	 * @param uiRequest
	 * @param ruleEntity
	 */
	public static void updateRuleEnitity(RuleUiRequest uiRequest, Rule ruleEntity){

		ruleEntity.setRuleName(uiRequest.getRuleName());
		
		if(StringUtils.isNotEmpty(uiRequest.getStatus())){
			ruleEntity.setStatus(uiRequest.getStatus());
		}
		
		for(RuleConfigUi ruleConfigUiObj : uiRequest.getRuleConfigUiList()){
			
			if(!ruleConfigUiObj.isNewConfig()){
				inner:
				for(RuleConfig ruleConfigEntityObj : ruleEntity.getRuleConfigurations()){
					
					if(ruleConfigEntityObj.getConfigId() == ruleConfigUiObj.getConfigId()){
						if(ruleConfigUiObj.isRemove()){
							ruleEntity.getRuleConfigurations().remove(ruleConfigEntityObj);
						}else{
							ruleConfigEntityObj.setConfigName(ruleConfigUiObj.getConfigName());
							ruleConfigEntityObj.setConfigValue(ruleConfigUiObj.getConfigValue());
						}
						break inner;
					}
				}
				}else{
					RuleConfig ruleConfigNewEntity = new RuleConfig();
					ruleConfigNewEntity.setConfigName(ruleConfigUiObj.getConfigName());
					ruleConfigNewEntity.setConfigValue(ruleConfigUiObj.getConfigValue());
					ruleConfigNewEntity.setRule(ruleEntity);
					ruleEntity.addRuleConfig(ruleConfigNewEntity);
				}
			
		}
	}

}
