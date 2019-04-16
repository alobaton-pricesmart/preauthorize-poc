/**
 * 
 */
package com.co.preauthorizepoc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	/**
	 * Put here your public access endpoints...
	 */
	private static final String[] PUBLIC_ACCESS = new String[] { "/", "/login**", "/webjars/**", "/error**",
			"/oauth/authorize", "/oauth/confirm_access", "/actuator**", "/users", "/h2/**" };

	@Value("${app.name}")
	private String resourceId;

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(Boolean.TRUE);
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public CorsFilter corsFilter() {
		return new CorsFilter(corsConfigurationSource());
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		return converter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(Boolean.TRUE);
		return defaultTokenServices;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurerAdapter#configure(org.springframework.security.oauth2
	 * .config.annotation.web.configurers.ResourceServerSecurityConfigurer)
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceId).tokenServices(tokenServices());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurerAdapter#configure(org.springframework.security.config
	 * .annotation.web.builders.HttpSecurity)
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// Login and logout...
		http.formLogin().loginPage("/login").permitAll()//
				.defaultSuccessUrl("/")//
				.and().logout().logoutSuccessUrl("/login").permitAll()//
				// If a user try to access a resource without having enough permissions
				.and().exceptionHandling().accessDeniedPage("/login");

		http.authorizeRequests()//
				// OAuth OPTIONS requests...
				.antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()//
				// Public access...
				.antMatchers(PUBLIC_ACCESS).permitAll()//
				// Setup full authentication access...
				.anyRequest().authenticated()
				// .and().csrf().ignoringAntMatchers("/h2/**")
				// Allow use of frame to same origin urls
				.and().headers().frameOptions().sameOrigin();

	}

}