/**
 * 
 */
package com.automation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author AvinashVaidya
 *
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.automation.config", "com.automation.controller","com.automation.dto","com.automation.dao",
		"com.automation.service","com.automation.eventHandling" })
public class AutomationConfig{
	
	

}
