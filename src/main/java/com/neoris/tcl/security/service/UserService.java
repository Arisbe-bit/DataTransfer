package com.neoris.tcl.security.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neoris.tcl.security.dao.IUserDao;
import com.neoris.tcl.security.models.User;

@Service()
public class UserService implements IUserService {

    private final static Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IUserDao userRepository;

    public UserService() {
    }

    @Override
    public User saveUser(User user) {
        User retval = null;
        try {
            retval = userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error saving user: {}", e.getMessage());
        }
        return retval;
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
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

}
