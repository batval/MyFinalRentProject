package com.batval.myrent.service;

import com.batval.myrent.model.UserProfile;

import java.util.List;

/**
 * Interface describing the behavior of the User profile
 *
 * @author Baturo Valery
 * @version 1.0
 */
public interface UserProfileService {
    /**
     * Search  user profile by ID
     *
     * @param id user id
     */
    UserProfile findById(int id);
    /**
     * Search  user by  profile types
     *
     * @param type user profile type
     */
    UserProfile findByType(String type);
    /**
     * Find all  profiles of users
     *
     */
    List<UserProfile> findAll();

}
