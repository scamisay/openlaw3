package ar.org.openlaw.service;


import ar.org.openlaw.domain.user.User;

import java.util.Collection;

/**
 * Created by scamisay on 23/02/16.
 */
public interface UserService {
    User getUserById(String id);

    User getUserByUsername(String email);

    Collection<User> getAllUsers();

    User create(User user);
}
