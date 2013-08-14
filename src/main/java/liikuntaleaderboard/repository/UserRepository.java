package liikuntaleaderboard.repository;

import liikuntaleaderboard.content.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Miika
 */
public interface UserRepository extends CrudRepository<User, Long> {
    
}
