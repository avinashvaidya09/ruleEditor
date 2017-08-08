/**
 * 
 */
package com.automation.dao;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.automation.config.AutomationConfig;
import com.automation.config.RuleEditorDBConfig;
import com.automation.dto.Rule;
import com.automation.dto.RuleConfig;

/**
 * @author AvinashVaidya
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RuleDaoImpl.class, RuleEditorDBConfig.class}, loader = AnnotationConfigContextLoader.class)
public class RuleDaoImplTests {
	
	@Autowired
	RuleDao ruleDao;
	
	@Test
	@Transactional
	public void testFetchRules(){
		
		try {
			List<Rule> ruleList = ruleDao.fetchRules();
			assertNotNull(ruleList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Transactional
	public void testFindRuleById(){
		
		try {
			Rule ruleObj = ruleDao.findRuleById(1);
			assertNotNull(ruleObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCreateRule(){
		
		try {
			
			Rule ruleObj = new Rule();
			RuleConfig rfc = new RuleConfig();
			
			rfc.setConfigName("CONFIG2");
			rfc.setConfigValue("VALUE2");
			rfc.setRule(ruleObj);
			
			Set<RuleConfig> ruleConfigurations = new HashSet<RuleConfig>(0);
			ruleConfigurations.add(rfc);
			
			ruleObj.setRuleConfigurations(ruleConfigurations);
			ruleObj.setRuleName("RULE2");
			ruleObj.setStatus("ACTIVE");
			
			ruleObj = ruleDao.createRule(ruleObj);
			
			assertNotNull(ruleObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testUpdateRule(){
		
		try {
			
			Rule ruleObj = new Rule();
			RuleConfig rfc = new RuleConfig();
			
			rfc.setConfigId(5);
			rfc.setConfigName("CONFIG2");
			rfc.setConfigValue("VALUE3");
			rfc.setRule(ruleObj);
			
			RuleConfig rfc1 = new RuleConfig();
			rfc1.setConfigName("CONFIG3");
			rfc1.setConfigValue("VALUE4");
			rfc1.setRule(ruleObj);
			
			
			Set<RuleConfig> ruleConfigurations = new HashSet<RuleConfig>(0);
			ruleConfigurations.add(rfc);
			ruleConfigurations.add(rfc1);
			
			ruleObj.setRuleConfigurations(ruleConfigurations);
			ruleObj.setRuleId(5);
			ruleObj.setRuleName("RULE2");
			ruleObj.setStatus("INACTIVE");
			
			ruleObj = ruleDao.updateRule(ruleObj);
			
			assertNotNull(ruleObj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testDeleteRule(){
		
		try {
		
			ruleDao.deleteRule(37);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
