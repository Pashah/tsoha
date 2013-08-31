/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import liikuntaleaderboard.content.User;
import liikuntaleaderboard.repository.UserRepository;
import liikuntaleaderboard.repository.UserSQLRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miika
 */
@Service
public class UserService implements UserServiceInterface {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    @Qualifier("UserSQLRepo")
    private UserSQLRepo userSQLRepo;

    @Override
    public void register(User user) {
        user.setPoints(0);
        user.setRole("user");
        try {
            userSQLRepo.save(user);
        } catch (Throwable ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void changeRole(String role, Long id) {
        User user = userRepository.findOne(id);
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public boolean loginCheck(String username, String password, HttpSession session) {
        ResultSet resultSet;
        resultSet = userSQLRepo.checkLogin(username, password);
        if(resultSet == null)
            return false;
        try {
            if(resultSet.next()) {
                Long userId = resultSet.getLong("USER_ID");
                session.setAttribute("userId", userId);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void createUserTable() {
        try {
            userSQLRepo.createUserTable();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createAdminUser(String username, String password, String email) {
        User admin = new User(username, password, email, true);
        userSQLRepo.save(admin);
    }

    @Override
    public List<User> getUsers() {
        try {
            return constructUsers(userSQLRepo.findAll());
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User getUser(Long id) {
        ResultSet resultSet = null;
        try {
            resultSet = userSQLRepo.findOne(id);
            if(resultSet == null || !resultSet.next()) {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user = new User(resultSet);
        return user;
    }

    @Override
    public void updatePoints(Long id, int points) {
        System.out.println("updatin points userid: " + id + " points: " + points);
        User user = getUser(id);
        userSQLRepo.updatePoints(id, user.getPoints() + points);
    }
    
    private List<User> constructUsers(ResultSet resultSet) {
        List<User> users = new ArrayList();
        try {
            while(resultSet.next()) {
                users.add(new User(resultSet));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
}
