package liikuntaleaderboard.repository;

import liikuntaleaderboard.content.Accomplishment;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Miika
 */
public interface AccomplishmentRepository extends CrudRepository<Accomplishment, Long> {
    
}
