/**
 * 
 */
package com.co.preauthorizepoc.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author alobaton
 *
 */
public interface SecurityService {

	public boolean hasAccess(UserDetails principal, Authentication authentication);

}
