package com.neoris.tcl.security.service;

import java.util.List;
import java.util.Optional;

import com.neoris.tcl.security.models.Rol;
import com.neoris.tcl.security.models.Role;

public interface IRoleService {

	Role saveRole(Role rol);

	List<Role> saveAll(List<Role> roles);

	void deleteRole(Role rol);

	void deleteAll(List<Role> roles);

	List<Role> findAll();

	Optional<Role> findById(int id);

	Optional<Role> findByRole(Rol rol);

}