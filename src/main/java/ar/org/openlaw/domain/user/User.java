package ar.org.openlaw.domain.user;

import ar.org.openlaw.domain.BaseDomain;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by scamisay on 28/10/16.
 */
@Document(collection = "users")
public class User extends BaseDomain{

    private String username;
    private String passwordHash;
    private Role role;
    private String name;
    private String surname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return surname != null ? surname.equals(user.surname) : user.surname == null;

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }


    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}

