/**
 * 
 */
package com.co.preauthorizepoc.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author alobaton
 *
 */
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
		implements MethodSecurityExpressionOperations {

	private static final Log LOGGER = LogFactory.getLog(CustomMethodSecurityExpressionRoot.class);

	private Object filterObject;
	private Object returnObject;

	public CustomMethodSecurityExpressionRoot(Authentication authentication) {
		super(authentication);
	}

	public boolean customHasPermission() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		LOGGER.debug(request.getRequestURI());
		LOGGER.debug(this.getAuthentication().getPrincipal());

		return true;
	}

	@Override
	public void setFilterObject(Object filterObject) {
		this.filterObject = filterObject;
	}

	@Override
	public Object getFilterObject() {
		return this.filterObject;
	}

	@Override
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	@Override
	public Object getReturnObject() {
		return this.returnObject;
	}

	@Override
	public Object getThis() {
		return null;
	}
}