/**
 * 
 */
package com.co.preauthorizepoc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.co.preauthorizepoc.security.CustomMethodSecurityExpressionHandler;

/**
 * @author alobaton
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		CustomMethodSecurityExpressionHandler expressionHandler = new CustomMethodSecurityExpressionHandler();

		// Also, we can define a custom permission evaluator.
		// expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());

		return expressionHandler;
	}
}