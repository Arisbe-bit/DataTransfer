package com.neoris.tcl.security.service;

import com.neoris.tcl.security.models.User;

public interface IUserService {

    User findUserByEmail(String email);

    User findUserByUserName(String userName);

    User saveUser(User user);

}