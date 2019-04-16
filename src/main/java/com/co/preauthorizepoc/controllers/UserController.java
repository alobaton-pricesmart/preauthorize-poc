/**
 * 
 */
package com.co.preauthorizepoc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.preauthorizepoc.annotations.CustomPreAuthorize;
import com.co.preauthorizepoc.domain.User;
import com.co.preauthorizepoc.service.UserService;

/**
 * @author alobaton
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public User create(@Valid @RequestBody User domain) {
		return service.create(domain);
	}

	@GetMapping("/{id}")
	@CustomPreAuthorize
	public User get(@PathVariable String id) {
		return service.get(id);
	}

}
