package com.neoris.tcl.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neoris.tcl.security.models.User;

public interface IUserDao extends JpaRepository<User, Long> {
    /**
     * Find the user by email
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * Find the user by Username
     * @param userName
     * @return
     */
    User findByUserName(String userName);
}
