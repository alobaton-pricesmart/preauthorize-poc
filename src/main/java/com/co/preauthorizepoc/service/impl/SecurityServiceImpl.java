/**
 * 
 */
package com.co.preauthorizepoc.service.impl;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.co.preauthorizepoc.service.SecurityService;

/**
 * @author alobaton
 *
 */
@Service
public class SecurityServiceImpl implements SecurityService {

	@Override
	public boolean hasAccess(@NotNull final UserDetails principal, @NotNull final Authentication authentication) {
		System.out.println("It's working!");
		
		return false;
	}

}
