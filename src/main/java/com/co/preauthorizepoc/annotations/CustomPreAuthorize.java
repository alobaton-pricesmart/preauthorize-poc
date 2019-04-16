/**
 * 
 */
package com.co.preauthorizepoc.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author alobaton
 *
 */
@PreAuthorize(value = "@securityService.hasAccess(principal, authentication)")
public @interface CustomPreAuthorize {

}
