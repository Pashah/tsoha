package liikuntaleaderboard.service;

import java.util.List;
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
        accomplishment.setLengthInMinutes(lengthInMinutes);
        System.out.println(accomplishment.getLengthInMinutes());
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

    @Override
    public List<Accomplishment> getAccomplishments() {
        return (List<Accomplishment>)accomplishmentRepository.findAll();
    }

    @Override
    public void deleteAccomplishment(Long id) {
        accomplishmentRepository.delete(id);
    }

    @Override
    public void setPoints(Long id, int points) {
        Accomplishment accomplishment = getAccomplishment(id);
        accomplishment.setPoints(points);
        saveAccomplishment(accomplishment);
    }
    
}
