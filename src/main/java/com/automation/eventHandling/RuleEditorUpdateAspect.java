/**
 * 
 */
package com.automation.eventHandling;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.automation.domain.RuleUi;

/**
 * @author AvinashVaidya
 *
 */
@Component
@Aspect
public class RuleEditorUpdateAspect {
	
	private static Logger AUDITLOGGER = LoggerFactory.getLogger(RuleEditorUpdateAspect.class);
	
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
	
	
	@AfterReturning(pointcut = "execution(* com.automation.service.RuleEditorService.updateRule(..))"
			+ "|| execution(* com.automation.service.RuleEditorService.deleteRule(..))", returning = "ruleUiList")
	public void sendWebsocketEventToUsers(JoinPoint joinPoint, List<RuleUi> ruleUiList){
		RuleEditorEvent ruleEditorEvent = null;
		//event handling
		if("updateRule".equalsIgnoreCase(joinPoint.getSignature().getName())){
			 ruleEditorEvent = new RuleEditorEvent(this, "update", ruleUiList.get(0));
		}else if("deleteRule".equalsIgnoreCase(joinPoint.getSignature().getName())){
			 ruleEditorEvent = new RuleEditorEvent(this, "delete", ruleUiList.get(0));
		}
		
		
		applicationEventPublisher.publishEvent(ruleEditorEvent);
		
		
		
				
		
	}
	
	

}
