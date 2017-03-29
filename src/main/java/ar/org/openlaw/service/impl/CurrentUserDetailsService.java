package ar.org.openlaw.service.impl;

import ar.org.openlaw.domain.user.Role;
import ar.org.openlaw.domain.user.User;
import ar.org.openlaw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by scamisay on 24/02/16.
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        }
        return new CurrentUser(user);
    }


    public static CurrentUserDetailsService.CurrentUser getCurrentUser(HttpSession session){
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        return (CurrentUserDetailsService.CurrentUser)securityContext.getAuthentication().getPrincipal();
    }

    public class CurrentUser extends org.springframework.security.core.userdetails.User {

        private User user;

        public CurrentUser(User user) {
            super(user.getUsername(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
            this.user = user;
        }

        public User getUser() {
            return user;
        }

        public String getId() {
            return user.getId();
        }

        public Role getRole() {
            return user.getRole();
        }

    }


}
