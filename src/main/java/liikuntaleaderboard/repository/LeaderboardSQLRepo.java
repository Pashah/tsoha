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
import javax.sql.DataSource;
import liikuntaleaderboard.content.Leaderboard;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Miika
 */
@Component(value = "LeaderboardSQLRepo")
public class LeaderboardSQLRepo {
    
    private int id = 0;
    private int lu_id = 0;
    
    private Connection createConnection() throws SQLException {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("/META-INF/beans.xml");
        DataSource dataSource = (DataSource) appContext.getBean("dataSource");
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
    
    public void createLeaderboardTable() throws SQLException {
        System.out.println("Creating leaderboard table!");
        String createLeaderboardTableSql = "CREATE TABLE LEADERBOARD ("
                + "LEADERBOARD_ID LONG NOT NULL GENERATED ALWAYS AS IDENTITY, "
                + "NAME VARCHAR(255) NOT NULL)";
        Connection connection = createConnection();
        Statement statement = connection.createStatement();
        statement.execute(createLeaderboardTableSql);
    }
    
    public void createLeaderboardUsersTable() throws SQLException {
        System.out.println("Creating leaderboard users table!");
        String createLeaderboardTableSql = "CREATE TABLE LEADERBOARD_USERS ("
                + "LEADERBOARD_USERS_ID LONG NOT NULL GENERATED ALWAYS AS IDENTITY, "
                + "LEADERBOARD_ID LONG NOT NULL REFERENCES LEADERBOARD(LEADERBOARD_ID), "
                + "USER_ID LONG NOT NULL REFERENCES USER(USER_ID))";
        Connection connection = createConnection();
        Statement statement = connection.createStatement();
        statement.execute(createLeaderboardTableSql);
    }
    
    public void addUserToLeaderboard(Long leaderboardId, Long userId) throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = 
            connection.prepareStatement("INSERT INTO LEADERBOARD_USERS VALUES (?, ?, ?)");
        statement.setLong(1, lu_id++ + 1);
        statement.setLong(2, leaderboardId);
        statement.setLong(3, userId);
        statement.execute();
    }
    
    public ResultSet getAllLeaderboardsUsers(Long leaderboardId) throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = 
            connection.prepareStatement("SELECT USER_ID FROM LEADERBOARD_USERS WHERE LEADERBOARD_ID = ?");
        statement.setLong(1, leaderboardId);
        return statement.executeQuery();
    }
    
    public ResultSet getUsersAllLeaderboards(Long userId) throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = 
            connection.prepareStatement("SELECT LEADERBOARD_ID FROM LEADERBOARD_USERS WHERE USER_ID = ?");
        statement.setLong(1, userId);
        return statement.executeQuery();
    }
    
    public void create(Leaderboard leaderboard) throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = 
            connection.prepareStatement("INSERT INTO LEADERBOARD VALUES (?, ?)");
        statement.setLong(1, id++ + 1);
        statement.setString(2, leaderboard.getName());
        statement.execute();
    }
        
    public ResultSet findOne(Long id) throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM LEADERBOARD WHERE LEADERBOARD_ID = ?");
        statement.setLong(1, id);
        return statement.executeQuery();
    }
    
    public ResultSet findAll() throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM LEADERBOARD");
        return statement.executeQuery();
    }
    
    public void delete(Long id) throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM LEADERBOARD WHERE LEADERBOARD_ID = ?");
        statement.setLong(1, id);
        statement.execute();
    }
    
}
