package ar.org.openlaw.service.impl;

import ar.org.openlaw.domain.user.User;
import ar.org.openlaw.repository.UserRepository;
import ar.org.openlaw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by scamisay on 23/02/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByUsername(String email) {
        return userRepository.findOneByUsername(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}
