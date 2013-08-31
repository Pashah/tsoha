package liikuntaleaderboard.controller;

import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;


public interface UserControllerInterface {
    String viewUserPage(Model model);
    String viewUserInfoPage(Long userId, Model model, HttpSession httpSession);
}
