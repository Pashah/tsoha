/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.helpers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Miika
 */
public class ConnectionHelper {
    
    public Connection createConnection() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("/META-INF/beans.xml");
        DataSource dataSource = (DataSource) appContext.getBean("dataSource");
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            try {
                throw new SQLException(ex);
            } catch (SQLException ex1) {
                Logger.getLogger(ConnectionHelper.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return null;
    }
    
}
