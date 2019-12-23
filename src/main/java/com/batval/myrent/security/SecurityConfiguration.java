package com.batval.myrent.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * Securiyt configuration
 * extends of {@link WebSecurityConfigurerAdapter} class
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * Customer user details service
	 */
    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

	/**
	 * Repository token
	 */
    @Autowired
    PersistentTokenRepository tokenRepository;

	/**
	 * Configure global security
	 */
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }
	/**
	 * Setting access rights to resources
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/yourcars")
                .access("hasRole('DEALER')")
                .antMatchers("/newcar")
                .access("hasAnyRole('DEALER','ADMIN')")
                .antMatchers("/rent-car-*")
                .access("hasAnyRole('ADMIN', 'DEALER','USER')")
                .antMatchers("/delete-car-*")
                .access("hasAnyRole('ADMIN', 'DEALER')")
                .antMatchers("/userpanel")
                .access("hasAnyRole('ADMIN', 'DEALER', 'USER')")
                .antMatchers("/list")
                .access("hasRole('ADMIN')")
                .antMatchers("/delete-user-*")
                .access("hasRole('ADMIN')")
                .antMatchers("/edit-user-*")
                .access("hasAnyRole('ADMIN', 'DEALER','USER')").and().formLogin().loginPage("/login")
                .loginProcessingUrl("/login").usernameParameter("ssoId").passwordParameter("password").and()
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                .tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");

    }

    /**
     * Password encoder BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication provider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    /**
     * Token for remember me
     */
    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
                "remember-me", userDetailsService, tokenRepository);
        return tokenBasedservice;
    }

    /**
     * Bean authentication
     */
    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

}
