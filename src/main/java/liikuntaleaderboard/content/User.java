package liikuntaleaderboard.content;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Miika
 */
@Entity(name="USER")
public class User implements Serializable{
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    @NotEmpty
    private String username;
    
    @Column
    @NotEmpty
    private String password;
    
    @Email
    @Column
    private String email;
    
    @Column
    private int points;
    
    @Column
    private String roleName;
    
    public User() {
        //Default constructor
    }
    
    public User(ResultSet resultSet) {
        try {
            this.id = resultSet.getLong("USER_ID");
            this.username = resultSet.getString("USERNAME");
            this.password = resultSet.getString("PASSWORD");
            this.email = resultSet.getString("EMAIL");
            this.points = resultSet.getInt("POINTS");
            this.roleName = resultSet.getString("ROLE");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User(String username, String password, String email, boolean admin) {
        this.username = username;
        this.password = password;
        this.email = email;
        if(admin)
            this.roleName = "admin";
        else
            this.roleName = "user";
        this.points = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getRole() {
        return roleName;
    }

    public void setRole(String role) {
        this.roleName = role;
    }
    
    
}
