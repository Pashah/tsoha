package liikuntaleaderboard.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import liikuntaleaderboard.content.Accomplishment;
import liikuntaleaderboard.repository.AccomplishmentRepository;
import liikuntaleaderboard.repository.AccomplishmentSQLRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miika
 */
@Service
public class AccomplishmentService implements AccomplishmentServiceInterface{
    
    @Autowired
    @Qualifier("AccomplishmentSQLRepo")
    private AccomplishmentSQLRepo accomplishmentSQLRepo;
    
    @Autowired
    private AccomplishmentRepository accomplishmentRepository;

    @Override
    public void createAccomplishment(String sport, int lengthInMinutes, Long userId) {
        Accomplishment accomplishment = new Accomplishment();
        accomplishment.setSport(sport);
        accomplishment.setLengthInMinutes(lengthInMinutes);
        accomplishment.setUser_id(userId);
        accomplishment.setPoints(countPoints(sport, lengthInMinutes));
        System.out.println(accomplishment.getUser_id());
        try {
            accomplishmentSQLRepo.create(accomplishment);
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void savePoints(Accomplishment accomplishment) {
        try {
            accomplishmentSQLRepo.savePoints(accomplishment);
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Accomplishment getAccomplishment(Long id) {
        ResultSet resultSet = null;
        try {
            resultSet = accomplishmentSQLRepo.findOne(id);
            if(resultSet == null || !resultSet.next()) {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        Accomplishment accomplishment = new Accomplishment(resultSet);
        return accomplishment;
        //return accomplishmentRepository.findOne(id);
    }

    @Override
    public List<Accomplishment> getAccomplishments() {
        ResultSet resultSet = null;
        try {
            resultSet = accomplishmentSQLRepo.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(resultSet == null) {
            return null;
        }
        List<Accomplishment> accomplishments = new ArrayList<>();
        try {
            while (resultSet.next()) {            
                accomplishments.add(new Accomplishment(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accomplishments;
    }

    @Override
    public void deleteAccomplishment(Long id) {
        try {
            accomplishmentSQLRepo.delete(id);
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setPoints(Long id, int points) {
        Accomplishment accomplishment = getAccomplishment(id);
        accomplishment.setPoints(points);
        savePoints(accomplishment);
    }

    private int countPoints(String sport, int lengthInMinutes) {
        if(sport.contentEquals("sali") || sport.contentEquals("juoksu")) {
            return (lengthInMinutes + 5);
        } else {
            return lengthInMinutes;
        }
    }

    @Override
    public void createAccomplishmentTable() {
        try {
            accomplishmentSQLRepo.createAccomplishmentTable();
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
