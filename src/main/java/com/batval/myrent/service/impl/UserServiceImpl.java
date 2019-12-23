package com.batval.myrent.service.impl;

import com.batval.myrent.dao.UserDao;
import com.batval.myrent.model.User;
import com.batval.myrent.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class describing the behavior of the User
 * Implementation of {@link UserService} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    /**
     * Event Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    /**
     *Work with UserDao
     *
     */
    @Autowired
    UserDao dao;

    /**
     *Password encoder
     *
     */
    @Autowired
    PasswordEncoder passwordEncoder;
    /**
     * Search  user by ID
     *
     * @param id user id
     */
    @Override
    public User findById(int id) {
        logger.info("Search user by id:"+id);
        return dao.findById(id);
    }
    /**
     * Search  user by Single Sign-On
     *
     * @param sso user Single Sign-On
     */
    @Override
    public User findBySSO(String sso){
    logger.info("Search user by login:"+sso);
        return dao.findBySSO(sso);
    }
    /**
     * Save user
     *
     * @param user to save
     */
    @Override
    public void saveUser(User user) {
        logger.info("Save user:"+user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }
    /**
     * Update user
     *
     * @param user to update
     */
    @Override
    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        entity.setSsoId(user.getSsoId());
        if (!user.getPassword().equals(entity.getPassword())) {
            entity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setUserProfiles(user.getUserProfiles());
        logger.info("Update user: "+user.toString());

    }

    /**
     * Delete  user by sso
     *
     * @param sso user uniq number
     */
    @Override
    public void deleteUserBySSO(String sso) {
        logger.info("Delete user by login: "+sso);
        dao.deleteBySSO(sso);
    }
    /**
     * Find all  users
     */
    @Override
    public List<User> findAllUsers() {
        logger.info("Search all users.");
        return dao.findAllUsers();
    }
    /**
     * Check if user is uniq
     *
     * @param sso user uniq number
     * @param id  user id
     */
    @Override
    public boolean isUserSSOUnique(Integer id, String sso) {
        logger.info("Check is user login unique");
        return dao.checkIfNotExists(sso);
    }
    /**
     * Get user name
     *
     */
    @Override
    public String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
    /**
     * Check if user is
     *
     * @param sso user uniq number
     */
    @Override
    public boolean checkIfExists(String sso) {
        logger.info("Check is exist user:" +sso);
        return !dao.checkIfNotExists(sso);
    }

}
