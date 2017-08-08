package com.automation.dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * AccountInfo generated by hbm2java
 */
@Entity
@Table(name = "RULE_CONFIG", catalog = "test", uniqueConstraints = @UniqueConstraint(columnNames = "CONFIG_ID"))
public class RuleConfig implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer configId;
	private String configName;
	private String configValue;
	private Rule rule;
	
	

	public RuleConfig() {
	}

	public RuleConfig( String configName, String configValue) {
		this.configName = configName;
		this.configValue = configValue;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CONFIG_ID", unique = true, nullable = false)
	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	@Column(name = "CONFIG_NAME", unique = false, nullable = false)
	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	@Column(name = "CONFIG_VALUE", unique = false, nullable = false)
	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
	@JoinColumn(name = "CONFIG_RULE_ID", nullable = false)
	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}
	
	

}
