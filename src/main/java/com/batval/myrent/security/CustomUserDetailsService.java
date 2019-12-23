package com.batval.myrent.security;

import com.batval.myrent.model.User;
import com.batval.myrent.model.UserProfile;
import com.batval.myrent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * User rights class
 * Implementation of {@link UserDetailsService} interface
 *
 * @author Baturo Valery
 * @version 1.0
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    /**
     *User service for working with user
     *
     */
    @Autowired
    private UserService userService;

    /**
     *Load user Rights by ssoId
     *
     * @param ssoId user login
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        User user = userService.findBySSO(ssoId);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(),
                true, true, true, true, getGrantedAuthorities(user));
    }

    /**
     *Load user authority
     *
     * @param user   for whom you need to get rights
     */
    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserProfile userProfile : user.getUserProfiles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
        }
        return authorities;
    }

}
