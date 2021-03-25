package com.neoris.tcl.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neoris.tcl.security.models.Role;

@Repository
public interface IRoleDao extends JpaRepository<Role, Long> {

    /**
     * Find role by role.
     * @param role
     * @return
     */
    Role findByRole(String role);
}
