/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.service;

import liikuntaleaderboard.content.User;

/**
 *
 * @author Miika
 */
public interface UserServiceInterface {
    void register(User user);
    void changeRole(String role, Long id);
    boolean loginCheck(String username, String password);
}
