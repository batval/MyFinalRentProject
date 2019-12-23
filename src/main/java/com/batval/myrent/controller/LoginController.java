package com.batval.myrent.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Login controller
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class LoginController {
    /**
     * Event Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    /**
     * String for login url
     */
    private static final String LOGIN = "login";
    /**
     * String for home page redirect url
     */
    private static final String REDIRECT_HOMEPAGE = "redirect:/homepage";
    /**
     * String for home page redirect url with logout
     */
    private static final String REDIRECT_HOMEPAGE_LOGOUT = "redirect:/homepage?logout";

    /**
     * Authentication resolver
     */
    //SERVICES
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    /**
     * Token for "remember me"
     */
    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    /**
     * Get form to login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return LOGIN;

        }
        return REDIRECT_HOMEPAGE;
    }
    /**
     * Method handles logout redirect to home page.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logger.info("User: "+auth.getName()+" logout");
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return REDIRECT_HOMEPAGE_LOGOUT;
    }
    /**
     * Uset  authentication anonymous
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
