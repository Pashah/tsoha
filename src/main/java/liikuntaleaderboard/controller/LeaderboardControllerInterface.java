/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.controller;

import java.util.List;

/**
 *
 * @author Miika
 */
public interface LeaderboardControllerInterface {
    String saveLeaderboard(String name);
    String deleteLeaderboard(Long id);
    String addUsersToLeaderboard(Long leaderboardId, List<Long> userIds);
}
