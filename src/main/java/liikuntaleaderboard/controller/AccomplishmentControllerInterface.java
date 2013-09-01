package liikuntaleaderboard.controller;

import javax.servlet.http.HttpSession;
import liikuntaleaderboard.content.Accomplishment;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Miika
 */
public interface AccomplishmentControllerInterface {
    String saveAccomplishment(Accomplishment accomplishment, BindingResult bindingResult, HttpSession session);
    String deleteAccomplishment(Long id);
    String setPoints(Long id, String points, Long userId);
    String controlAccomplishments(Model model, HttpSession session);
}
