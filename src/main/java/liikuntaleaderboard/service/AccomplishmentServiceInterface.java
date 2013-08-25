/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.service;

import java.util.List;
import liikuntaleaderboard.content.Accomplishment;

/**
 *
 * @author Miika
 */
public interface AccomplishmentServiceInterface {
    void createAccomplishment(String sport, int lengthInMinutes, Long userId);
    void saveAccomplishment(Accomplishment accomplishment);
    Accomplishment getAccomplishment(Long id);
    List<Accomplishment> getAccomplishments();
    void deleteAccomplishment(Long id);
    void setPoints(Long id, int points);
}
