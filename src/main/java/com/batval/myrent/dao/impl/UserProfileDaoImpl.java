package com.batval.myrent.dao.impl;

import com.batval.myrent.dao.AbstractDao;
import com.batval.myrent.dao.UserProfileDao;
import com.batval.myrent.model.UserProfile;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class describing the behavior of the User DAO extend of {@link AbstractDao} class
 * Implementation of {@link UserProfileDao} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao {
    /**
     * Type of user profile
     */
    private static final String TYPE_PROPERTY = "type";
    /**
     * Search  user profile by ID
     *
     * @param id user id
     */
    public UserProfile findById(int id) {
        return getByKey(id);
    }
    /**
     * Search  user by  profile types
     *
     * @param type user profile type
     */
    public UserProfile findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(TYPE_PROPERTY, type));
        return (UserProfile) crit.uniqueResult();
    }
    /**
     * Find all  profiles of users
     *
     */
    @SuppressWarnings("unchecked")
    public List<UserProfile> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc(TYPE_PROPERTY));
        return (List<UserProfile>) crit.list();
    }

}
