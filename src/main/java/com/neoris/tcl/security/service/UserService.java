package com.neoris.tcl.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.neoris.tcl.security.dao.IRoleDao;
import com.neoris.tcl.security.dao.IUserDao;
import com.neoris.tcl.security.models.Role;
import com.neoris.tcl.security.models.User;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao userRepository;

    @Autowired
    private IRoleDao roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService() {
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

}
