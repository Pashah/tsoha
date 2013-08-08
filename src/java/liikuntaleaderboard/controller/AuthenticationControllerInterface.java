package liikuntaleaderboard.controller;

import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;

/**
 * Interface for authentication controller
 * @author Miika
 */
public interface AuthenticationControllerInterface {
    String viewLoginPage();
    String login(String username, String password, HttpSession session);
    String viewMainPage(Model model, HttpSession session);
    String logout(HttpSession session);
}
