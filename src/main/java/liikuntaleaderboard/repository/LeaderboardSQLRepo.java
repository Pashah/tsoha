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
