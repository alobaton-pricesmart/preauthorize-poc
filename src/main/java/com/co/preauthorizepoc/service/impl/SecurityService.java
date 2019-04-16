/**
 * 
 */
package com.co.preauthorizepoc.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author alobaton
 *
 */
@Component
public class SecurityService {

	private static final Log LOGGER = LogFactory.getLog(SecurityService.class);

	public boolean hasAccess(@NotNull OAuth2Authentication authentication) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		LOGGER.debug(request.getRequestURI());
		LOGGER.debug(authentication.getPrincipal());

		// Do whatever you want...

		return true;
	}

}
