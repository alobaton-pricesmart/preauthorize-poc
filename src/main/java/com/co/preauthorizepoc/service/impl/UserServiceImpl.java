/**
 * 
 */
package com.co.preauthorizepoc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	@Autowired
	private UserRepository repository;

	@Override
	public User create(User domain) {
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

		return user;
	}

}
