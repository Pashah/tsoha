/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.service;

import liikuntaleaderboard.content.Accomplishment;

/**
 *
 * @author Miika
 */
public interface AccomplishmentServiceInterface {
    void createAccomplishment(String sport, int lengthInMinutes);
    void saveAccomplishment(Accomplishment accomplishment);
    Accomplishment getAccomplishment(Long id);
}
