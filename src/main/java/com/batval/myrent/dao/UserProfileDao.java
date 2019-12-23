package com.batval.myrent.dao;

import com.batval.myrent.model.UserProfile;

import java.util.List;

/**
 * Interface describing the behavior of the UserProfile DAO
 *
 * @author Baturo Valery
 * @version 1.0
 */

public interface UserProfileDao {

    /**
     * Find all  profiles of users
     *
     */
    List<UserProfile> findAll();
    /**
     * Search  user by  profile types
     *
     * @param type user profile type
     */
    UserProfile findByType(String type);
    /**
     * Search  user profile by ID
     *
     * @param id user id
     */
    UserProfile findById(int id);
}
