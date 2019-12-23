package com.batval.myrent.controller;

import com.batval.myrent.model.UserProfile;
import com.batval.myrent.model.UserProfileType;
import com.batval.myrent.service.CarService;
import com.batval.myrent.service.UserProfileService;
import com.batval.myrent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Main application controller
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

    /**
     * String for logged user url
     */
    private static final String LOGGED_USER = "loggedinuser";
    /**
     * String for cars url
     */
    private static final String CARS = "cars";
    /**
     * String for access denied url
     */
    private static final String ACCESS_DENIED = "accessDenied";
    /**
     * String for home page url
     */
    private static final String HOMEPAGE = "homepage";
    /**
     * String for about url
     */
    private static final String ABOUT = "about";
    /**
     * String for your cars url
     */
    private static final String YOUR_CARS = "yourcars";

    /**
     * User service
     */
    //SERVICES
    @Autowired
    UserService userService;

    /**
     * User profile service
     */
    @Autowired
    UserProfileService userProfileService;
    /**
     * Car service
     */
    @Autowired
    CarService carService;

    /**
     * Method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }


    /**
     * This method get UserProfile
     */
    @ModelAttribute("role_user")
    public UserProfile initializeUserProfile() {
        return userProfileService.findByType(UserProfileType.USER.getUserProfileType());
    }

    /**
     * Method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute(LOGGED_USER, userService.getPrincipal());
        return ACCESS_DENIED;
    }

    /**
     * Method handles homepage (main page) redirect.
     */
    @RequestMapping(value = {"/", "/homepage"}, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute(LOGGED_USER, userService.getPrincipal());
        return HOMEPAGE;
    }

    /**
     * Method handles about redirect.
     */
    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public String aboutPage(ModelMap model) {
        model.addAttribute(LOGGED_USER, userService.getPrincipal());
        return ABOUT;
    }
    /**
     * Method handles yout cars redirect.
     */
    @RequestMapping(value = "/yourcars", method = RequestMethod.GET)
    public String yourCars(ModelMap model) {
        model.addAttribute(LOGGED_USER, userService.getPrincipal());
        model.addAttribute(CARS, carService.findUserCars(userService.getPrincipal()));
        return YOUR_CARS;
    }
}