/**
 * 
 */
package com.automation.util;

/**
 * @author AvinashVaidya
 *
 */
public enum RuleStatus {

	ACTIVE("ACTIVE"),

	INACTIVE("INACTIVE");

    private String status;

    private RuleStatus(String status) {
       this.status = status;
    }

    public String getRuleStatus() {
        return status;
    }
}
