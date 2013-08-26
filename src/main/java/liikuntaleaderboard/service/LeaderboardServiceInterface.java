/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.service;

import java.util.List;
import liikuntaleaderboard.content.Leaderboard;

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
}
