/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;

/**
 *
 * @author Miika
 */
public interface LeaderboardControllerInterface {
    String saveLeaderboard(String name);
    String deleteLeaderboard(Long id);
    String addUsersToLeaderboard(Long leaderboardId, List<Long> userIds, HttpSession session);
    String viewLeaderboard(Long id, Model model);
    String controlLeaderboards(Model model, HttpSession session);
}
