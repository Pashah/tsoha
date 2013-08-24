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
import liikuntaleaderboard.content.User;
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
    
    
    private Connection createConnection() throws SQLException {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("/META-INF/beans.xml");
        DataSource dataSource = (DataSource) appContext.getBean("dataSource");
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
    
    public void save(User user) throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = 
            connection.prepareStatement("INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?)");
        statement.setLong(1, id++ + 1);
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getEmail());
        statement.setInt(5, user.getPoints());
        statement.setString(6, user.getRole());
        statement.execute();
    }
  /*  
    public void jdbcSave(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        JdbcTemplate template = new JdbcTemplate(getDataSource());
        template.update(new PreparedStatementCreator() {
        public PreparedStatement createPreparedStatement(Connection connection)
         throws SQLException {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO AUTHOR"
                       + " (NAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, author.name);
                return ps;
            }
        }, keyHolder);
        Long generatedId = new Long(keyHolder.getKey().longValue());
        book.setId(generatedId);
    }
    */
    public void createUserTable() throws SQLException {
        System.out.println("Creating user table!");
        String createUserTableSql = "CREATE TABLE USER ("
                + "USER_ID LONG NOT NULL GENERATED ALWAYS AS IDENTITY, "
                + "USERNAME VARCHAR(255) NOT NULL, "
                + "PASSWORD VARCHAR(255) NOT NULL, "
                + "EMAIL VARCHAR(255) NOT NULL, "
                + "POINTS INT(20), "
                + "ROLE VARCHAR(255)"
                + ")";
        Connection connection = createConnection();
        Statement statement = connection.createStatement();
        statement.execute(createUserTableSql);
    }
    
    public ResultSet checkLogin(String username, String password) throws SQLException {
        Connection connection = createConnection();
        PreparedStatement statement = 
            connection.prepareStatement("SELECT PASSWORD FROM USER WHERE USERNAME = ? AND PASSWORD = ?");
        statement.setString(1, username);
        statement.setString(2, password);
        return statement.executeQuery();
    }
    
}