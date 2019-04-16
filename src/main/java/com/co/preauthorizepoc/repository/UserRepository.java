/**
 * 
 */
package com.co.preauthorizepoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.co.preauthorizepoc.domain.User;

/**
 * @author alobaton
 *
 */
public interface UserRepository extends JpaRepository<User, String> {

}
