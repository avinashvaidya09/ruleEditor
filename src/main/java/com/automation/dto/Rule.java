package com.automation.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Synchronize;

/**
 * AccountInfo generated by hbm2java
 */
@Entity
@Table(name = "RULE", catalog = "test", uniqueConstraints = @UniqueConstraint(columnNames = "RULE_ID"))
public class Rule implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer ruleId;
	private String ruleName;
	private String status;
	private Set<RuleConfig> ruleConfigurations = new HashSet<RuleConfig>(0);
	
	

	public Rule() {
	}

	public Rule(String ruleName, String status) {
		this.ruleName = ruleName;
		this.status = status;
	}

	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RULE_ID", unique = true, nullable = false)
	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	@Column(name = "RULE_NAME", unique = false, nullable = false)
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}


	@OneToMany(fetch = FetchType.EAGER, mappedBy = "rule",cascade= CascadeType.ALL,orphanRemoval=true)
	public Set<RuleConfig> getRuleConfigurations() {
		return this.ruleConfigurations;
	}
	
	

	public void setRuleConfigurations(Set<RuleConfig> ruleConfigurations) {
		this.ruleConfigurations = ruleConfigurations;
	}

	@Column(name = "STATUS", unique = false, nullable = false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public void addRuleConfig(RuleConfig ruleConfigEntity){
		this.ruleConfigurations.add(ruleConfigEntity);
	}
	

}
