package liikuntaleaderboard.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import liikuntaleaderboard.content.Accomplishment;
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
    private UserServiceInterface userService;

    @Override
    public void createAccomplishment(String sport, int lengthInMinutes, Long userId) {
        Accomplishment accomplishment = new Accomplishment();
        accomplishment.setSport(sport);
        accomplishment.setLengthInMinutes(lengthInMinutes);
        accomplishment.setUser_id(userId);
        accomplishment.setPoints(countPoints(sport, lengthInMinutes));
        accomplishmentSQLRepo.create(accomplishment);
    }

    @Override
    public void savePoints(Accomplishment accomplishment) {
        accomplishmentSQLRepo.savePoints(accomplishment);
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
    }

    @Override
    public List<Accomplishment> getAccomplishments() {
        return constructAccomplishments(accomplishmentSQLRepo.findAll());
    }

    @Override
    public void deleteAccomplishment(Long id) {
        accomplishmentSQLRepo.delete(id);
    }

    @Override
    public void setPoints(Long id, int points, Long userId) {
        Accomplishment accomplishment = getAccomplishment(id);
        int subtract = points - accomplishment.getPoints();
        userService.updatePoints(userId, subtract);
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
        accomplishmentSQLRepo.createAccomplishmentTable();
    }

    @Override
    public List<Accomplishment> getUsersAccomplishments(Long userId) {
        return constructAccomplishments(accomplishmentSQLRepo.findUsersAll(userId));
    }
    
    private List<Accomplishment> constructAccomplishments(ResultSet resultSet) {
        List<Accomplishment> accomplishments = new ArrayList();
        try {
            while (resultSet.next()) {
                    accomplishments.add(new Accomplishment(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(accomplishments.isEmpty())
            return null;
        return accomplishments;
    }
    
}
