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
import liikuntaleaderboard.content.Leaderboard;
import liikuntaleaderboard.helpers.ConnectionHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Miika
 */
@Component(value = "LeaderboardSQLRepo")
public class LeaderboardSQLRepo {
    
    private int id = 0;
    private int lu_id = 0;
    private ConnectionHelper connectionHelper = new ConnectionHelper();
    
    public void createLeaderboardTable() throws SQLException {
        System.out.println("Creating leaderboard table!");
        String createLeaderboardTableSql = "CREATE TABLE LEADERBOARD ("
                + "LEADERBOARD_ID LONG NOT NULL GENERATED ALWAYS AS IDENTITY, "
                + "NAME VARCHAR(255) NOT NULL)";
        Connection connection = connectionHelper.createConnection();
        Statement statement = connection.createStatement();
        statement.execute(createLeaderboardTableSql);
    }
    
    public void createLeaderboardUsersTable() throws SQLException {
        System.out.println("Creating leaderboard users table!");
        String createLeaderboardTableSql = "CREATE TABLE LEADERBOARD_USERS ("
                + "LEADERBOARD_USERS_ID LONG NOT NULL GENERATED ALWAYS AS IDENTITY, "
                + "LEADERBOARD_ID LONG NOT NULL REFERENCES LEADERBOARD(LEADERBOARD_ID), "
                + "USER_ID LONG NOT NULL REFERENCES USER(USER_ID))";
        Connection connection = connectionHelper.createConnection();
        Statement statement = connection.createStatement();
        statement.execute(createLeaderboardTableSql);
    }
    
    public void addUserToLeaderboard(Long leaderboardId, Long userId) throws SQLException {
        Connection connection = connectionHelper.createConnection();
        PreparedStatement statement = 
            connection.prepareStatement("INSERT INTO LEADERBOARD_USERS VALUES (?, ?, ?)");
        statement.setLong(1, lu_id++ + 1);
        statement.setLong(2, leaderboardId);
        statement.setLong(3, userId);
        statement.execute();
    }
    
    public ResultSet getAllLeaderboardsUsers(Long leaderboardId) throws SQLException {
        Connection connection = connectionHelper.createConnection();
        PreparedStatement statement = 
            connection.prepareStatement("SELECT USER_ID FROM LEADERBOARD_USERS WHERE LEADERBOARD_ID = ?");
        statement.setLong(1, leaderboardId);
        return statement.executeQuery();
    }
    
    public ResultSet getUsersAllLeaderboards(Long userId) throws SQLException {
        Connection connection = connectionHelper.createConnection();
        PreparedStatement statement = 
            connection.prepareStatement("SELECT * FROM LEADERBOARD_USERS WHERE USER_ID = ?");
        statement.setLong(1, userId);
        return statement.executeQuery();
    }
    
    public void create(Leaderboard leaderboard) throws SQLException {
        Connection connection = connectionHelper.createConnection();
        PreparedStatement statement = 
            connection.prepareStatement("INSERT INTO LEADERBOARD VALUES (?, ?)");
        statement.setLong(1, id++ + 1);
        statement.setString(2, leaderboard.getName());
        statement.execute();
    }
        
    public ResultSet findOne(Long id) throws SQLException {
        Connection connection = connectionHelper.createConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM LEADERBOARD WHERE LEADERBOARD_ID = ?");
        statement.setLong(1, id);
        return statement.executeQuery();
    }
    
    public ResultSet findAll() throws SQLException {
        Connection connection = connectionHelper.createConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM LEADERBOARD");
        return statement.executeQuery();
    }
    
    public void delete(Long id) throws SQLException {
        Connection connection = connectionHelper.createConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM LEADERBOARD WHERE LEADERBOARD_ID = ?");
        statement.setLong(1, id);
        statement.execute();
    }
    
}
