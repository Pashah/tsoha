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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Miika
 */
public class Accomplishment implements Serializable {
    
    @Column
    private Long id;
    
    @Column
    private String sport;
    
    @Column
    private Long user_id;
    
    @Column
    private int points;
    
    @Column
    private int lengthInMinutes;

    public Accomplishment() {
    }
    
    public Accomplishment(ResultSet resultSet) {
        try {
            this.id = resultSet.getLong("ACCOMPLISHMENT_ID");
            this.sport = resultSet.getString("SPORT");
            this.user_id = resultSet.getLong("USER_ID");
            this.points = resultSet.getInt("POINTS");
            this.lengthInMinutes = resultSet.getInt("LENGTHINMINUTES");
        } catch (SQLException ex) {
            Logger.getLogger(Accomplishment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }
    
}
