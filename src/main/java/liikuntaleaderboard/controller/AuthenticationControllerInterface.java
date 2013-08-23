package liikuntaleaderboard.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import liikuntaleaderboard.content.User;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Interface for authentication controller
 * @author Miika
 */
public interface AuthenticationControllerInterface {
    String viewLoginPage();
    String login(String username, String password, HttpSession session);
    String viewMainPage(Model model, HttpSession session);
    String logout(HttpSession session);
    String loginfailed(Model model);
    String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,Model model);
    String viewRegisterPage(ModelMap model);
}
