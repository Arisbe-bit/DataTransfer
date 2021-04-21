package com.neoris.tcl.security.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
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

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PersistenceContext
	private EntityManager entityManager;

	public UserService() {
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
	@Transactional
	public Optional<User> findByUsername(String username) {
		Optional<User> userResponse = userRepository.findByUsername(username);
		if (userResponse.isPresent()) {
			User user = userResponse.get();
			Session session = (Session) entityManager.unwrap(Session.class);

			if (!user.getRoles().isEmpty()) {
				user.getRoles().forEach(r -> LOG.info("Rol ID = {}, Rol Name = {}", r.getId(), r.getRole().name()));
			}

			session.close();
		}
		return userResponse;
	}

}
