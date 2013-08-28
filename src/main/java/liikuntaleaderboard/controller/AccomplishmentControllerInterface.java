package liikuntaleaderboard.controller;

import liikuntaleaderboard.content.Accomplishment;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Miika
 */
public interface AccomplishmentControllerInterface {
    String saveAccomplishment(Accomplishment accomplishment, BindingResult bindingResult);
    String deleteAccomplishment(Long id);
    String setPoints(Long id, String points, Long userId);
}
