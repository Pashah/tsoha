/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.service;

import java.util.List;
import liikuntaleaderboard.content.Leaderboard;
import liikuntaleaderboard.content.User;

/**
 *
 * @author Miika
 */
public interface LeaderboardServiceInterface {
    void createLeaderboard(String name);
    Leaderboard getLeaderboard(Long id);
    List<Leaderboard> getLeaderboards();
    void deleteLeaderboard(Long id);
    void createLeaderboardTable();
    void createLeaderboardUsersTable();
    void addUsersToLeaderboard(Long leaderboardId, List<Long> userIds);
    List<User> getLeaderboardsAllUsers(Long leaderboardId);
    List<Leaderboard> getUsersAllLeaderboards(Long userId);
}
