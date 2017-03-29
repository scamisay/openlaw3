package ar.org.openlaw.repository;


import ar.org.openlaw.domain.law.Law;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by scamisay on 08/02/16.
 */
public interface LawRepository extends MongoRepository<Law, String> {
}
