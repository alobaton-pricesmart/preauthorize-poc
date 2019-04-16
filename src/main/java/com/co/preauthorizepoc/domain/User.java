/**
 * 
 */
package com.co.preauthorizepoc.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author alobaton
 *
 */
@Entity
@Table(name = "user")
// This project use an embedded database, so this configuration is only for testing.
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1925624004327410231L;
	@NotNull
	@Column(name = "authorities")
	private String authorities;
	@Id
	@NotNull
	@Column(name = "username")
	private String username;
	@NotNull
	@Column(name = "password")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@NotNull
	@Column(name = "account_non_expired")
	private boolean accountNonExpired;
	@NotNull
	@Column(name = "account_non_locked")
	private boolean accountNonLocked;
	@NotNull
	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired;
	@NotNull
	@Column(name = "enabled")
	private boolean enabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (authorities == null) {
			return Collections.emptyList();
		}

		@SuppressWarnings("unchecked")
		List<String> splitedAuthorities = CollectionUtils.arrayToList(authorities.split(","));

		return splitedAuthorities.stream().map(role -> (GrantedAuthority) () -> role).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [authorities=" + authorities + ", username=" + username + ", password=" + password
				+ ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked
				+ ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + "]";
	}

}
