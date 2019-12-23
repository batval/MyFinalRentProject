package com.batval.myrent.model;

import java.io.Serializable;

/**
 * Enum describing  of the User profile
 * Implementation of {@link Serializable} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
public enum UserProfileType implements Serializable {
    USER("USER"),
    ADMIN("ADMIN"),
    DEALER("DEALER");
    /**
     *User profile type user
     *
     */
    String userProfileType;
    /**
     * Constructor user profile type
     *
     * @param userProfileType car number
     */
    private UserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    /**
     * Get user profile type
     *
     * @return  user profile type
     */
    public String getUserProfileType() {
        return userProfileType;
    }

}
