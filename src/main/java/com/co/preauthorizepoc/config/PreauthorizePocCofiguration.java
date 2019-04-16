/**
 * 
 */
package com.co.preauthorizepoc.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author alobaton
 *
 */
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = { "com.co.preauthorizepoc.repository" })
@EntityScan(basePackages = { "com.co.preauthorizepoc.domain" })
@EnableTransactionManagement
public class PreauthorizePocCofiguration {

}
