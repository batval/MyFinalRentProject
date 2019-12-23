package com.batval.myrent.dao.impl;

import com.batval.myrent.dao.AbstractDao;
import com.batval.myrent.model.PersistentLogin;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Class for storing a token in the database extend of {@link AbstractDao} class
 * Implementation of {@link PersistentTokenRepository} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin>
        implements PersistentTokenRepository {

    /**
     * Saving a token in the database
     *
     * @param token remember me
     */
    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        persist(persistentLogin);

    }

    /**
     * Get token from the database
     *
     * @param seriesId token
     */
    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            Criteria crit = createEntityCriteria();
            crit.add(Restrictions.eq("series", seriesId));
            PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();

            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLast_used());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Delete token from the database by user name
     *
     * @param username name of user
     */
    @Override
    public void removeUserTokens(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
        if (persistentLogin != null) {
            delete(persistentLogin);
        }

    }

    /**
     * Update token in the database
     *
     * @param seriesId series id token,
     * @param tokenValue value of token,
     * @param lastUsed date of last using
     */
    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        PersistentLogin persistentLogin = getByKey(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);
        update(persistentLogin);
    }

}
