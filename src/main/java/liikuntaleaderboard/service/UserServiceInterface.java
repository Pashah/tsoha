/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import liikuntaleaderboard.content.User;

/**
 *
 * @author Miika
 */
public interface UserServiceInterface {
    void register(User user);
    void changeRole(String role, Long id);
    boolean loginCheck(String username, String password, HttpSession session);
    void createUserTable();
    void createAdminUser(String username, String password, String email);
    List<User> getUsers();
    User getUser(Long id);
}
