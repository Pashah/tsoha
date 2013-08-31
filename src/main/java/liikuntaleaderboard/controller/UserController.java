package liikuntaleaderboard.controller;

import javax.servlet.http.HttpSession;
import liikuntaleaderboard.service.AccomplishmentServiceInterface;
import liikuntaleaderboard.service.LeaderboardServiceInterface;
import liikuntaleaderboard.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController implements UserControllerInterface{
    
    @Autowired
    private LeaderboardServiceInterface leaderboardService;
    @Autowired
    private AccomplishmentServiceInterface accomplishmentService;
    @Autowired
    private UserServiceInterface userService;

    @Override
    @RequestMapping(value="viewUserPage", method = RequestMethod.GET)
    public String viewUserPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "userpage";
    }

    @Override
    @RequestMapping(value="viewUserInfo", method = RequestMethod.GET)
    public String viewUserInfoPage(@RequestParam(value = "userId", required = true) Long userId, Model model, HttpSession session) {
        model.addAttribute("leaderboards", leaderboardService.getUsersAllLeaderboards(userId));
        model.addAttribute("accomplishments", accomplishmentService.getUsersAccomplishments(userId));
        model.addAttribute("user", userService.getUser(userId));
        if(session != null) {
            model.addAttribute("userId", session.getAttribute("userId"));
        }
        return "userInfopage";
    }
    
}
