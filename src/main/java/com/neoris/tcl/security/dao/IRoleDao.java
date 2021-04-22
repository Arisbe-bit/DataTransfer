package com.neoris.tcl.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.tcl.security.models.Role;

@Repository
public interface IRoleDao extends JpaRepository<Role, Integer> {

    /**
     * Find role by role.
     * @param role
     * @return
     */
	Optional<Role> findByRole(String role);
}
