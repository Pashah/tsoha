package liikuntaleaderboard.service;

import liikuntaleaderboard.content.Accomplishment;
import liikuntaleaderboard.repository.AccomplishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miika
 */
@Service
public class AccomplishmentService implements AccomplishmentServiceInterface{
    
    @Autowired
    private AccomplishmentRepository accomplishmentRepository;

    @Override
    public void createAccomplishment(String sport, int lengthInMinutes) {
        Accomplishment accomplishment = new Accomplishment();
        accomplishment.setSport(sport);
        accomplishment.setPoints(lengthInMinutes);
        saveAccomplishment(accomplishment);
    }

    @Override
    public void saveAccomplishment(Accomplishment accomplishment) {
        accomplishmentRepository.save(accomplishment);
    }

    @Override
    public Accomplishment getAccomplishment(Long id) {
        return accomplishmentRepository.findOne(id);
    }
    
}
