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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
