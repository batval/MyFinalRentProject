package com.batval.myrent.service;

import com.batval.myrent.model.User;

import java.util.List;

/**
 * Interface describing the behavior of the User
 *
 * @author Baturo Valery
 * @version 1.0
 */
public interface UserService {
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
    void saveUser(User user);

    /**
     * Update user
     *
     * @param user to update
     */
    void updateUser(User user);

    /**
     * Delete  user by sso
     *
     * @param sso user uniq number
     */
    void deleteUserBySSO(String sso);

    /**
     * Find all  users
     */
    List<User> findAllUsers();

    /**
     * Check if user is uniq
     *
     * @param sso user uniq number
     * @param id  user id
     */
    boolean isUserSSOUnique(Integer id, String sso);
    /**
     * Get user name
     *
     */
    String getPrincipal();
    /**
     * Check if user is
     *
     * @param sso user uniq number
     */
    boolean checkIfExists(String sso);

}