package liikuntaleaderboard.controller;

/**
 *
 * @author Miika
 */
public interface AccomplishmentControllerInterface {
    String saveAccomplishment(String sport, String lengthInMinutes);
    String deleteAccomplishment(Long id);
    String setPoints(Long id, String points);
}
