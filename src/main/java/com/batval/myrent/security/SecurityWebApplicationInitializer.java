package com.batval.myrent.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Automatically register the springSecurityFilterChain Filter for every URL in  application
 * Add a ContextLoaderListener that loads the SecurityConfig.
 *
 * @author Baturo Valery
 * @version 1.0
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
