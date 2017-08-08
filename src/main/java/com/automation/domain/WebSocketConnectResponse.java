/**
 * 
 */
package com.automation.domain;

/**
 * @author AvinashVaidya
 *
 */
public class WebSocketConnectResponse {
	
	private String message;
    public WebSocketConnectResponse(String message) {
        this.message = message;
    }
    public String getMessage() {
	return message;
    }

}
