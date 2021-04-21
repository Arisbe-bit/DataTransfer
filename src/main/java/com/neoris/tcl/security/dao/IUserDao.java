package com.neoris.tcl.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neoris.tcl.security.models.User;

public interface IUserDao extends JpaRepository<User, Integer> {

    /**
     * Find the user by Username
     * @param userName
     * @return
     */
    Optional<User> findByUsername(String username);
}
