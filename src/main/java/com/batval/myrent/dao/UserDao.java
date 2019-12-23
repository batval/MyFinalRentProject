package com.batval.myrent.dao;

import com.batval.myrent.model.User;

import java.util.List;

/**
 * Interface describing the behavior of the User DAO
 *
 * @author Baturo Valery
 * @version 1.0
 */

public interface UserDao {

    /**
     * Search  user by ID
     *
     * @param id user id
     */
    User findById(int id);

    /**
     * Search  user by Single Sign-On
     *
     * @param sso user Single Sign-On
     */
    User findBySSO(String sso);
    /**
     * Save user
     *
     * @param user to save
     */
    void save(User user);

    /**
     * Delete  user by sso
     *
     * @param sso user uniq number
     */
    void deleteBySSO(String sso);

    /**
     * Find all  users
     *
     */
    List<User> findAllUsers();
    /**
     * Check  user by  number
     *
     * @param sso user  registration number
     */
    boolean checkIfNotExists(String sso);

}

