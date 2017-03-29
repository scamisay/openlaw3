package ar.org.openlaw.repository;


import ar.org.openlaw.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by scamisay on 23/02/16.
 */
public interface UserRepository extends MongoRepository<User,String> {

    User findOneByUsername(String email);
}
