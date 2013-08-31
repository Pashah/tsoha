/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import liikuntaleaderboard.content.Leaderboard;
import liikuntaleaderboard.content.User;
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
    
    @Autowired
    private UserServiceInterface userService;

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
        ResultSet resultSet = null;
        try {
            resultSet = leaderboardSQLRepo.findOne(id);
            if(!resultSet.next()) {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(resultSet == null)
            return null;
        return new Leaderboard(resultSet);
    }

    @Override
    public List<Leaderboard> getLeaderboards() {
        try {
            return constructLeaderboards(leaderboardSQLRepo.findAll());
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    @Override
    public void createLeaderboardUsersTable() {
        try {
            leaderboardSQLRepo.createLeaderboardUsersTable();
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addUsersToLeaderboard(Long leaderboardId, List<Long> userIds) {
        List<Long> usersNotInLeaderboard = checkUsersBeforeAdd(leaderboardId, userIds);
        try {
            for (Long userId : usersNotInLeaderboard) {
                leaderboardSQLRepo.addUserToLeaderboard(leaderboardId, userId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> getLeaderboardsAllUsers(Long leaderboardId) {
        ResultSet resultSet = null;
        try {
            resultSet = leaderboardSQLRepo.getAllLeaderboardsUsers(leaderboardId);
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(resultSet == null) {
            return null;
        }
        List<User> users = new ArrayList();
        Long userId;
        try {
            while(resultSet.next()) {
                userId = resultSet.getLong("USER_ID");
                users.add(userService.getUser(userId));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public List<Leaderboard> getUsersAllLeaderboards(Long userId) {
        
        List<Leaderboard> leaderboards = new ArrayList();
        ResultSet resultSet;
        try {
            resultSet = leaderboardSQLRepo.getUsersAllLeaderboards(userId);
            while(resultSet.next()) {
                leaderboards.add(getLeaderboard(resultSet.getLong("LEADERBOARD_ID")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return leaderboards;
    }
    
    private List<Leaderboard> constructLeaderboards(ResultSet resultSet) {
        List<Leaderboard> leaderboards = new ArrayList();
        try {
            while (resultSet.next()) {
                leaderboards.add(new Leaderboard(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeaderboardService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return leaderboards;
    }

    private List<Long> checkUsersBeforeAdd(Long leaderboardId, List<Long> userIds) {
        List<User> leaderboardsUsers = getLeaderboardsAllUsers(leaderboardId);
        List<Long> idsNotInLeaderboard = new ArrayList(userIds);
        System.out.println("ids not in lb length before: " + idsNotInLeaderboard.size());
        for (Long userId : userIds) {
            for (User user : leaderboardsUsers) {
                if(user.getId() == userId)
                    idsNotInLeaderboard.remove(Long.valueOf(userId));
            }
        }
        System.out.println("ids not in lb length after: " + idsNotInLeaderboard.size());
        return idsNotInLeaderboard;
    }
    
}
