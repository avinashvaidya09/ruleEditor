/**
 * 
 */
package com.automation.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.automation.dto.Rule;
import com.automation.util.RuleStatus;

/**
 * @author AvinashVaidya
 *
 */
@Repository
public class RuleDaoImpl implements RuleDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	private static String SELECT_BASIC = "from Rule r";
	
	private static String SELECT_BY_RULEID = "from Rule r where r.ruleId = :ruleId";
	
	private static String SELECT_BY_RULE_NAME = "from Rule r where ruleName = :ruleName";
	
	private static String DELETE_RULE = "delete from Rule r where r.ruleId = :ruleId";
	
	

	/* (non-Javadoc)
	 * @see com.automation.dao.RuleDao#fetchRules()
	 */
	@Override
	public List<Rule> fetchRules() throws Exception {
	
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(SELECT_BASIC);
		
		@SuppressWarnings("unchecked")
		List<Rule> ruleList =  query.list();
		
		return ruleList;
	}

	/* (non-Javadoc)
	 * @see com.automation.dao.RuleDao#findRuleById(java.lang.Integer)
	 */
	@Override
	public Rule findRuleById(Integer ruleId) throws Exception {
		
		Rule ruleObj = null;
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(SELECT_BY_RULEID);
		
		query.setParameter("ruleId", ruleId);
		
		@SuppressWarnings("unchecked")
		List<Rule> ruleList =  query.list();
		
		if(!CollectionUtils.isEmpty(ruleList)){
			ruleObj = ruleList.get(0);
		}
		
		return ruleObj;
	}
	
	
	@Override
	public Rule findRuleByName(String ruleName) throws Exception {
		Rule ruleObj = null;
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(SELECT_BY_RULE_NAME);
		
		query.setParameter("ruleName", ruleName);
		
		@SuppressWarnings("unchecked")
		List<Rule> ruleList =  query.list();
		
		if(!CollectionUtils.isEmpty(ruleList)){
			ruleObj = ruleList.get(0);
		}
		
		return ruleObj;
	}

	/* (non-Javadoc)
	 * @see com.automation.dao.RuleDao#createRule(com.automation.dto.Rule)
	 */
	@Override
	public Rule createRule(Rule ruleEntity) throws Exception {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.save(ruleEntity);
		
		return ruleEntity;
	}

	/* (non-Javadoc)
	 * @see com.automation.dao.RuleDao#updateRule(com.automation.dto.Rule)
	 */
	@Override
	public Rule updateRule(Rule ruleEntity) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		
		session.update(ruleEntity);
		
		return ruleEntity;
	}

	/* (non-Javadoc)
	 * @see com.automation.dao.RuleDao#deleteRule(java.lang.Integer)
	 */
	@Override
	public boolean deleteRule(Integer ruleId) throws Exception {
		
		boolean status = false;
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(DELETE_RULE);
		
		
		query.setParameter("ruleId",ruleId);
		
		query.executeUpdate();
		status = true;
		
		return status;
	}

	

}
