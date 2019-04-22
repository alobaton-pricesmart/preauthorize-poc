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

	/**
	 * Creates a user.
	 * 
	 * @param domain User ioformation.
	 * @return The created user.
	 */
	public User create(User domain);

	/**
	 * Get a user
	 * 
	 * @param id The user id
	 * @return The user
	 */
	public User get(String id);

}
