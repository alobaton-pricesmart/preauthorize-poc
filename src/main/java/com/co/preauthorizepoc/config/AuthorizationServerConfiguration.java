/**
 * 
 */
package com.co.preauthorizepoc.config;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author alobaton
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private static final Log LOGGER = LogFactory.getLog(AuthorizationServerConfiguration.class);

	@Value("${app.name}")
	private String resourceId;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private PasswordEncoder encoder;

	@Bean
	public AuthenticationKeyGenerator authenticationKeyGenerator() {
		return new DefaultAuthenticationKeyGenerator();
	}

	@Bean
	public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
		return new DefaultWebResponseExceptionTranslator() {
			@Override
			public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);

				LOGGER.error(sw.toString());

				ResponseEntity<OAuth2Exception> response = super.translate(e);
				return response;
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * AuthorizationServerConfigurerAdapter#configure(org.springframework.security.
	 * oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer)
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(resourceId).secret(encoder.encode(resourceId))
				.authorizedGrantTypes("authorization_code", "password").scopes("write").autoApprove(true)
				.redirectUris("http://localhost:8080/login");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * AuthorizationServerConfigurerAdapter#configure(org.springframework.security.
	 * oauth2.config.annotation.web.configurers.
	 * AuthorizationServerEndpointsConfigurer)
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).accessTokenConverter(accessTokenConverter)
				.authenticationManager(authenticationManager).userDetailsService(userDetailsService)
				.tokenGranter(endpoints.getTokenGranter()).exceptionTranslator(webResponseExceptionTranslator());
	}

}