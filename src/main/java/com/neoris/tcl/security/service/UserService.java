package com.neoris.tcl.security.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.neoris.tcl.security.dao.IUserDao;
import com.neoris.tcl.security.models.User;

@Service()
public class UserService implements IUserService {

	private final static Logger LOG = LoggerFactory.getLogger(UserService.class);
			
	@Autowired
	private IUserDao userRepository;

//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PersistenceContext
	private EntityManager entityManager;

	public UserService() {
	}

	@Override
	public User saveUser(User user) {
		//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public void deleteAll(List<User> users) {
		userRepository.deleteAll(users);
	}

	@Override
	public Optional<User> findById(Integer id) {		
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> findByUsername(String username) {	
		return userRepository.findByUsername(username);
	}


}
