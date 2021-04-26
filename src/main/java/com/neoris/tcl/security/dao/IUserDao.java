package com.neoris.tcl.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.neoris.tcl.security.models.User;

public interface IUserDao extends JpaRepository<User, Integer> {

    /**
     * Find the user by Username
     * @param userName
     * @return
     */
    Optional<User> findByUsername(String username);
    
    @Modifying
    @Query(value = "UPDATE hfm_users u SET u.username=?, u.name=?, u.enabled=?, u.admin=?, u.dsvscompany=?, u.hfmcodes=?, hfmcodesoa=?, hfmcodestypes=?, u.layouthist= ?, u.matchaccounts=?, u.partners=?, u.payablesaccounts=?, u.receivablesaccounts=?, u.rollup= ?, u.rolluphist=? WHERE userid=?",
    		nativeQuery = true)
    Optional<User> saveWithoutPassword(String name, String username, Integer enabled, Integer userid);
    
//    @Modifying
//    @Query(
//    		value = "UPDATE hfm_users u SET u.enabled = :enabled, u.name = :name, u.username= :username WHERE u.userid = :userid",
//    		nativeQuery = true)
//    Optional<User> saveWithoutPassword(@Param("enabled") Integer enabled, @Param("name") String name, 
//    		@Param("username") String username, @Param("userid") Integer userid);
}
		
		