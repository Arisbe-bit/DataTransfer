package com.neoris.tcl.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoris.tcl.security.dao.IRoleDao;
import com.neoris.tcl.security.models.Rol;
import com.neoris.tcl.security.models.Role;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private IRoleDao dao;

	@Override
	public Role saveRole(Role rol) {
		return dao.save(rol);
	}

	@Override
	public List<Role> saveAll(List<Role> roles) {
		return dao.saveAll(roles);
	}

	@Override
	public void deleteRole(Role rol) {
		dao.delete(rol);
	}

	@Override
	public void deleteAll(List<Role> roles) {
		dao.deleteAll(roles);
	}

	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Role> findById(int id) {
		return dao.findById(id);
	}

	@Override
	public Optional<Role> findByRole(Rol rol) {
		return dao.findByRole(rol.name());
	}

}
