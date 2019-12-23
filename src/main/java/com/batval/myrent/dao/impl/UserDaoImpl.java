package com.batval.myrent.dao.impl;

import com.batval.myrent.dao.AbstractDao;
import com.batval.myrent.dao.UserDao;
import com.batval.myrent.exception.UserNotFoundException;
import com.batval.myrent.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Class describing the behavior of the User DAO extend of {@link AbstractDao} class
 * Implementation of {@link UserDao} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
    /**
     * Single Sign-On id
     */
    private static final String SSO_ID_PROPERTY = "ssoId";
    /**
     * Name of user
     */
    private static final String FIRST_NAME_PROPERTY = "firstName";

    /**
     * Search  user by ID
     *
     * @param id user id
     */
    public User findById(int id) {
        Optional<User> optionalUser = Optional.ofNullable(getByKey(id));
        optionalUser.ifPresent(u -> Hibernate.initialize(u.getUserProfiles()));
        return optionalUser.orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));
    }
    /**
     * Search  user by Single Sign-On
     *
     * @param sso user Single Sign-On
     */
    public User findBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(SSO_ID_PROPERTY, sso));
        Optional<User> optionalUser = Optional.ofNullable((User) crit.uniqueResult());
        optionalUser.ifPresent(u -> Hibernate.initialize(u.getUserProfiles()));
        return optionalUser.orElseThrow(() -> new UserNotFoundException(String.valueOf(sso)));
    }
    /**
     * Find all  users
     *
     */
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc(FIRST_NAME_PROPERTY));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<User>) criteria.list();
    }
    /**
     * Check  user by  number
     *
     * @param sso user  registration number
     */
    @Override
    public boolean checkIfNotExists(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(SSO_ID_PROPERTY, sso));
        return crit.uniqueResult() == null;
    }
    /**
     * Save user
     *
     * @param user to save
     */
    @Override
    public void save(User user) {
        persist(user);
    }

    /**
     * Delete  user by sso
     *
     * @param sso user uniq number
     */
    @Override
    public void deleteBySSO(String sso) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(SSO_ID_PROPERTY, sso));
        delete((User) crit.uniqueResult());
    }


}
