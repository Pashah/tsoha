/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import liikuntaleaderboard.service.LeaderboardServiceInterface;
import liikuntaleaderboard.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Miika
 */
@Controller
public class LeaderboardController implements LeaderboardControllerInterface {
    
    @Autowired
    private LeaderboardServiceInterface leaderboardService;
    @Autowired
    private UserServiceInterface userService;

    @Override
    @RequestMapping(value = "leaderboard", method = RequestMethod.POST)
    public String saveLeaderboard(@RequestParam(value = "leaderboardName", required = true)String name) {
        leaderboardService.createLeaderboard(name);
        return "redirect:/app/editLeaderboards";
    }

    @Override
    @RequestMapping(value = "deleteLeaderboard", method = RequestMethod.POST)
    public String deleteLeaderboard(@RequestParam(value = "leaderboardId", required = true)Long id) {
        leaderboardService.deleteLeaderboard(id);
        return "redirect:/app/editLeaderboards";
    }

    @Override
    @RequestMapping(value = "userToLeaderboard", method = RequestMethod.POST)
    public String addUsersToLeaderboard(@RequestParam(value = "leaderboardId", required = true)Long leaderboardId, 
    @RequestParam(value = "userIdParam", required = false)List<Long> userIds, HttpSession session) {
        if(userIds == null || userIds.size() < 1) {
            session.setAttribute("userToLbError", "Error!");
            return "redirect:/app/editLeaderboards";
        }
        leaderboardService.addUsersToLeaderboard(leaderboardId, userIds);
        return "redirect:/app/editLeaderboards";
    }

    @Override
    @RequestMapping(value = "viewLeaderboard", method = RequestMethod.GET)
    public String viewLeaderboard(@RequestParam(value = "leaderboardId", required = true)Long id, Model model) {
        model.addAttribute("leaderboard", leaderboardService.getLeaderboard(id));
        model.addAttribute("users", leaderboardService.getLeaderboardsAllUsers(id));
        return "leaderboard";
    }

    @Override
    @RequestMapping(value = "editLeaderboards", method = RequestMethod.GET)
    public String controlLeaderboards(Model model, HttpSession session) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("leaderboards", leaderboardService.getLeaderboards());
        if(session != null) {
            model.addAttribute("userId", session.getAttribute("userId"));
            if(session.getAttribute("userToLbError") != null) {
                model.addAttribute("userToLbError", "error!");
                session.setAttribute("userToLbError", null);
            }
        } 
        return "editLeaderboard";
    }
    
}
