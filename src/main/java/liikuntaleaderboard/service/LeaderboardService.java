/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import liikuntaleaderboard.content.Leaderboard;
import liikuntaleaderboard.repository.LeaderboardSQLRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miika
 */
@Service
public class LeaderboardService implements LeaderboardServiceInterface{
    
    @Autowired
    @Qualifier("LeaderboardSQLRepo")
    private LeaderboardSQLRepo leaderboardSQLRepo;

    @Override
    public void createLeaderboard(String name) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setName(name);
        try {
            leaderboardSQLRepo.create(leaderboard);
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Leaderboard getLeaderboard(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Leaderboard> getLeaderboards() {
        ResultSet resultSet = null;
        try {
            resultSet = leaderboardSQLRepo.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(resultSet == null) {
            return null;
        }
        List<Leaderboard> leaderboards = new ArrayList<>();
        try {
            while (resultSet.next()) {
                    leaderboards.add(new Leaderboard(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return leaderboards;
    }

    @Override
    public void deleteLeaderboard(Long id) {
        try {
            leaderboardSQLRepo.delete(id);
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createLeaderboardTable() {
        try {
            leaderboardSQLRepo.createLeaderboardTable();
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
