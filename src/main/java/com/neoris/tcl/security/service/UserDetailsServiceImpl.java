package com.neoris.tcl.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neoris.tcl.security.models.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userService.findById(username);
        if (optUser.isPresent()) {
            return optUser.get();
        } else {
            throw new UsernameNotFoundException(String.format("The user '{}' was not found. Please try again.", username));
        }
    }

}
