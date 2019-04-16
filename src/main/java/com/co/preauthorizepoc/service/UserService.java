/**
 * 
 */
package com.co.preauthorizepoc.service;

import com.co.preauthorizepoc.domain.User;

/**
 * @author alobaton
 *
 */
public interface UserService {

	public User create(User domain);

	public User get(String id);

}
