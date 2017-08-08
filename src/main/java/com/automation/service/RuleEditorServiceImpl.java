/**
 * 
 */
package com.automation.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.automation.dao.RuleDao;
import com.automation.domain.RuleUi;
import com.automation.domain.RuleUiRequest;
import com.automation.domain.RulesUiResponse;
import com.automation.dto.Rule;
import com.automation.util.RuleMapper;

/**
 * @author AvinashVaidya
 *
 */
@Service
public class RuleEditorServiceImpl implements RuleEditorService {
	
	@Autowired
	RuleDao ruleDao;
	
	

	@Override
	@Transactional
	public void getRules(RulesUiResponse uiResponse) throws Exception {
		
		List<Rule> ruleList = ruleDao.fetchRules();
		List<RuleUi> ruleUiList = RuleMapper.mapRulesToUiResponse(ruleList);
		uiResponse.setRuleUiList(ruleUiList);
		
	}

	@Override
	@Transactional
	public void createRule(RuleUiRequest uiRequest, RulesUiResponse uiResponse)
			throws Exception {
		Rule ruleEntity = null;
		
		ruleEntity = ruleDao.findRuleByName(uiRequest.getRuleName());
		
		if(ruleEntity != null){
			throw new Exception("Rule Name already exists.");
		}
		
		ruleEntity = RuleMapper.createRuleEntityObj(uiRequest);
		
		ruleDao.createRule(ruleEntity);
		List<Rule> ruleList = new ArrayList<Rule>();
		ruleList.add(ruleEntity);
		List<RuleUi> ruleUiList =  RuleMapper.mapRulesToUiResponse(ruleList);
		uiResponse.setRuleUiList(ruleUiList);
		
	}

	@Override
	@Transactional
	public List<RuleUi> updateRule(RuleUiRequest uiRequest)
			throws Exception {
		Rule ruleEntity = null;
		
		ruleEntity = ruleDao.findRuleById(uiRequest.getRuleId());
		
		if(ruleEntity == null){
			throw new Exception("Enter a valid rule name.");
		}
		
		RuleMapper.updateRuleEnitity(uiRequest, ruleEntity);
		
		ruleDao.updateRule(ruleEntity);
		List<Rule> ruleList = new ArrayList<Rule>();
		ruleList.add(ruleEntity);
		List<RuleUi> ruleUiList = RuleMapper.mapRulesToUiResponse(ruleList);
		
		
		return ruleUiList;
	}

	@Override
	@Transactional
	public List<RuleUi> deleteRule(RuleUiRequest uiRequest, RulesUiResponse uiResponse)
			throws Exception {
		Rule ruleEntity = null;
		
		ruleEntity = ruleDao.findRuleById(uiRequest.getRuleId());
		
		boolean flag = ruleDao.deleteRule(uiRequest.getRuleId());
		uiResponse.setDeleteStatus(flag);
		List<Rule> ruleList = new ArrayList<Rule>();
		ruleList.add(ruleEntity);
		List<RuleUi> ruleUiList = RuleMapper.mapRulesToUiResponse(ruleList);
		if(flag){
			ruleUiList.get(0).setRuleDeleted(true);
		}
		return ruleUiList;
		
	}

	@Override
	@Transactional
	public void getRuleByName(String ruleName, RulesUiResponse uiResponse)
			throws Exception {
		Rule ruleEntity = ruleDao.findRuleByName(ruleName);
		if(ruleEntity == null){
			throw new Exception("Rule Name not found. Enter valid Rule Name");
		}
		List<Rule> ruleList = new ArrayList<Rule>();
		ruleList.add(ruleEntity);
		List<RuleUi> ruleUiList = RuleMapper.mapRulesToUiResponse(ruleList);
		uiResponse.setRuleUiList(ruleUiList);
		
	}

}
