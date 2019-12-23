package com.batval.myrent.service.impl;

import com.batval.myrent.dao.UserProfileDao;
import com.batval.myrent.model.UserProfile;
import com.batval.myrent.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class describing the behavior of the UserProfile
 * Implementation of {@link UserProfileService} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	/**
	 * Event Logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);
	/**
	 *Work with UserDao
	 *
	 */
	@Autowired
    UserProfileDao dao;
	/**
	 * Search  user profile by ID
	 *
	 * @param id user id
	 */
	@Override
	public UserProfile findById(int id) {
		logger.info("Search user profile by id:"+id);
		return dao.findById(id);
	}
	/**
	 * Search  user by  profile types
	 *
	 * @param type user profile type
	 */
	@Override
	public UserProfile findByType(String type){

		logger.info("Search user profile by type:"+ type);
		return dao.findByType(type);
	}
	/**
	 * Find all  profiles of users
	 *
	 */
	@Override
	public List<UserProfile> findAll() {

		logger.info("Find all user profiles.");
		return dao.findAll();
	}
}
