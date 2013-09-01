/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import liikuntaleaderboard.content.Accomplishment;
import liikuntaleaderboard.helpers.ConnectionHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Miika
 */
@Component(value = "AccomplishmentSQLRepo")
public class AccomplishmentSQLRepo {
    
    private ConnectionHelper connectionHelper = new ConnectionHelper();
    
    public void createAccomplishmentTable() {
        System.out.println("Creating accomplishment table!");
        String createUserTableSql = "CREATE TABLE ACCOMPLISHMENT ("
                + "ACCOMPLISHMENT_ID LONG NOT NULL GENERATED ALWAYS AS IDENTITY, "
                + "SPORT VARCHAR(255) NOT NULL, "
                + "USER_ID LONG NOT NULL REFERENCES USER(USER_ID), "
                + "POINTS INT(20), "
                + "LENGTHINMINUTES INT(20)"
                + ")";
        Connection connection = connectionHelper.createConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(createUserTableSql);
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create(Accomplishment accomplishment) {
        Connection connection = connectionHelper.createConnection();
        try {
            PreparedStatement statement = 
                connection.prepareStatement("INSERT INTO ACCOMPLISHMENT (SPORT, USER_ID, POINTS, LENGTHINMINUTES) VALUES (?, ?, ?, ?)");
            statement.setString(1, accomplishment.getSport());
            statement.setLong(2, accomplishment.getUser_id());
            statement.setInt(3, accomplishment.getPoints());
            statement.setInt(4, accomplishment.getLengthInMinutes());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void savePoints(Accomplishment accomplishment) {
        Connection connection = connectionHelper.createConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("UPDATE ACCOMPLISHMENT " +
                                "SET POINTS = ?" +
                                "WHERE ACCOMPLISHMENT_ID = ?");
            statement.setInt(1, accomplishment.getPoints());
            statement.setLong(2, accomplishment.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public ResultSet findOne(Long id) {
        Connection connection = connectionHelper.createConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ACCOMPLISHMENT WHERE ACCOMPLISHMENT_ID = ?");
            statement.setLong(1, id);
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ResultSet findAll() {
        Connection connection = connectionHelper.createConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ACCOMPLISHMENT");
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void delete(Long id) {
        Connection connection = connectionHelper.createConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ACCOMPLISHMENT WHERE ACCOMPLISHMENT_ID = ?");
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet findUsersAll(Long userId) {
        Connection connection = connectionHelper.createConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ACCOMPLISHMENT WHERE USER_ID = ?");
            statement.setLong(1, userId);
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(AccomplishmentSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
