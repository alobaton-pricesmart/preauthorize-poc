/**
 * 
 */
package com.co.preauthorizepoc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.co.preauthorizepoc.domain.User;
import com.co.preauthorizepoc.repository.UserRepository;
import com.co.preauthorizepoc.service.UserService;

/**
 * @author alobaton
 *
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Log LOGGER = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User create(User domain) {
		domain.setPassword(encoder.encode(domain.getPassword()));

		return repository.save(domain);
	}

	@Override
	public User get(String id) {
		return repository.getOne(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = get(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		LOGGER.debug(user.toString());

		// Add authentication to security context.
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return user;
	}

}
