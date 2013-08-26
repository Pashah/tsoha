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
import liikuntaleaderboard.content.Accomplishment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Miika
 */
@Component(value = "AccomplishmentSQLRepo")
public class AccomplishmentSQLRepo {
    
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
    
    public void createAccomplishmentTable() throws SQLException {
        System.out.println("Creating accomplishment table!");
        String createUserTableSql = "CREATE TABLE ACCOMPLISHMENT ("
                + "ACCOMPLISHMENT_ID LONG NOT NULL GENERATED ALWAYS AS IDENTITY, "
                + "SPORT VARCHAR(255) NOT NULL, "
                + "USER_ID LONG NOT NULL REFERENCES USER(USER_ID), "
                + "POINTS INT(20), "
                + "LENGTHINMINUTES INT(20)"
                + ")";
        Connection connection = createConnection();
        Statement statement = connection.createStatement();
        statement.execute(createUserTableSql);
    }
    
    public void create(Accomplishment accomplishment) throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = 
            connection.prepareStatement("INSERT INTO ACCOMPLISHMENT VALUES (?, ?, ?, ?, ?)");
        statement.setLong(1, id++ + 1);
        statement.setString(2, accomplishment.getSport());
        statement.setLong(3, accomplishment.getUser_id());
        statement.setInt(4, accomplishment.getPoints());
        statement.setInt(5, accomplishment.getLengthInMinutes());
        statement.execute();
    }
    
    public void savePoints(Accomplishment accomplishment) throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = 
            connection.prepareStatement("UPDATE ACCOMPLISHMENT " +
                    "SET POINTS = ?" +
                    "WHERE ACCOMPLISHMENT_ID = ?");
        statement.setInt(1, accomplishment.getPoints());
        statement.setLong(2, accomplishment.getId());
        statement.execute();
    }
        
    public ResultSet findOne(Long id) throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM ACCOMPLISHMENT WHERE ACCOMPLISHMENT_ID = ?");
        statement.setLong(1, id);
        return statement.executeQuery();
    }
    
    public ResultSet findAll() throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM ACCOMPLISHMENT");
        return statement.executeQuery();
    }
    
    public void delete(Long id) throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM ACCOMPLISHMENT WHERE ACCOMPLISHMENT_ID = ?");
        statement.setLong(1, id);
        statement.execute();
    }
    
}
