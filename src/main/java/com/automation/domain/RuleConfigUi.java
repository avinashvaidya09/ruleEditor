/**
 * 
 */
package com.automation.domain;

/**
 * @author AvinashVaidya
 *
 */
public class RuleConfigUi {
	
	private Integer configId;
	
	private String configName;
	
	private String configValue;
	
	private boolean remove;
	
	private boolean newConfig;

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}

	public boolean isNewConfig() {
		return newConfig;
	}

	public void setNewConfig(boolean newConfig) {
		this.newConfig = newConfig;
	}

	
	
	
	

}
