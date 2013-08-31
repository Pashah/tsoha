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
import javax.sql.DataSource;
import liikuntaleaderboard.content.User;
import liikuntaleaderboard.helpers.ConnectionHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Miika
 */
@Component(value = "UserSQLRepo")
public class UserSQLRepo {
    
    private Long id = 0L;
    private ConnectionHelper connectionHelper = new ConnectionHelper();
    
    public void save(User user) {
        try {
            Connection connection = connectionHelper.createConnection();
            PreparedStatement statement = 
                connection.prepareStatement("INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?)");
            statement.setLong(1, id++ + 1);
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getPoints());
            statement.setString(6, user.getRole());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createUserTable() {
        System.out.println("Creating user table!");
        String createUserTableSql = "CREATE TABLE USER ("
                + "USER_ID LONG NOT NULL GENERATED ALWAYS AS IDENTITY, "
                + "USERNAME VARCHAR(255) NOT NULL, "
                + "PASSWORD VARCHAR(255) NOT NULL, "
                + "EMAIL VARCHAR(255) NOT NULL, "
                + "POINTS INT(20), "
                + "ROLE VARCHAR(255)"
                + ")";
        Connection connection = connectionHelper.createConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(createUserTableSql);
        } catch (SQLException ex) {
            Logger.getLogger(UserSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet checkLogin(String username, String password) {
        try {
            Connection connection = connectionHelper.createConnection();
            PreparedStatement statement = 
                connection.prepareStatement("SELECT USER_ID FROM USER WHERE USERNAME = ? AND PASSWORD = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UserSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ResultSet findAll() {
        Connection connection = connectionHelper.createConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("SELECT * FROM USER");
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UserSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ResultSet findOne(Long id) {
        Connection connection = connectionHelper.createConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("SELECT * FROM USER WHERE USER_ID = ?");
            statement.setLong(1, id);
            return statement.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UserSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void updatePoints(Long id, int points) {
        try {
            Connection connection = connectionHelper.createConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE USER SET POINTS = ? WHERE USER_ID = ?");
            statement.setInt(1, points);
            statement.setLong(2, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserSQLRepo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
