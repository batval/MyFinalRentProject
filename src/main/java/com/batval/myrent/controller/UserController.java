package com.batval.myrent.controller;

import com.batval.myrent.model.User;
import com.batval.myrent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class UserController {

    /**
     * Event Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static final String REGISTRATION_SUCCESS = "registrationsuccess";
    private static final String REGISTRATION = "registration";
    private static final String USER = "user";
    private static final String EDIT = "edit";
    private static final String LOGGED_USER = "loggedinuser";
    private static final String SUCCESS = "success";
    private static final String USER_LIST = "userslist";
    private static final String USER_PANEL = "userpanel";
    private static final String USERS = "users";
    private static final String FIND_USER = "finduser";
    private static final String USER_IMAGE = "userImage";

    /**
     * User service
     */
    @Autowired
    UserService userService;

    /**
     * Message source
     */
    @Autowired
    MessageSource messageSource;

    /**
     * This method show page to add user
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute(USER, user);
        model.addAttribute(EDIT, false);
        model.addAttribute(LOGGED_USER, userService.getPrincipal());

        return REGISTRATION;
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        User user = userService.findBySSO(ssoId);
        model.addAttribute(USER, user);
        model.addAttribute(EDIT, true);
        model.addAttribute(LOGGED_USER, userService.getPrincipal());
        return REGISTRATION;
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String ssoId) {

        if (result.hasErrors()) {
            return REGISTRATION;
        }
        userService.updateUser(user);
        model.addAttribute(SUCCESS, "User " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        model.addAttribute(LOGGED_USER, userService.getPrincipal());
        logger.info("User "+user+" edited");
        return REGISTRATION_SUCCESS;
    }

    /**
     * This method will be called on form submission, handling POST request for
     * deleting user in database.
     */
    @RequestMapping(value = {"/delete-user-{ssoId}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId, ModelMap model) {
        userService.deleteUserBySSO(ssoId);
        model.addAttribute(SUCCESS, "User deleted successfully");
        logger.info("User "+ssoId+" deleted");
        return REGISTRATION_SUCCESS;
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return REGISTRATION;
        }
        if (!userService.isUserSSOUnique(user.getId(), user.getSsoId())) {
            FieldError ssoError = new FieldError(USER, "ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            logger.info("Error register"+user+".");
            return REGISTRATION;
        }
        userService.saveUser(user);
        model.addAttribute(SUCCESS, "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
        model.addAttribute(LOGGED_USER, userService.getPrincipal());
        logger.info("User"+user+" registered successfully");
        return REGISTRATION_SUCCESS;
    }


    /**
     * This method get users from database
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listUsers(@RequestParam(value = "login", required = false) String login, ModelMap model) {
        if (login != null && userService.checkIfExists(login)) {
            model.addAttribute(FIND_USER, userService.findBySSO(login));
        }
        List<User> users = userService.findAllUsers();
        model.addAttribute(USERS, users);
        model.addAttribute(LOGGED_USER, userService.getPrincipal());
        return USER_LIST;
    }

    /**
     * This method   displaying user information
     */
    @RequestMapping(value = {"/userpanel"}, method = RequestMethod.GET)
    public String userPanelPage(ModelMap model) {
        User user = userService.findBySSO(userService.getPrincipal());
        if (user.getPicture() != null) {
            byte[] encodeBase64 = Base64.encode(user.getPicture());
            String base64Encoded = new String(encodeBase64, StandardCharsets.UTF_8);
            model.addAttribute(USER_IMAGE, base64Encoded);
        }
        model.addAttribute(LOGGED_USER, userService.getPrincipal());
        model.addAttribute(USER, user);
        return USER_PANEL;
    }
}
