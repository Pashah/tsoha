/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private boolean firstUser = true;

    @Override
    public void register(User user) {
        if(firstUser) {
            try {
                userSQLRepo.createUserTable();
                firstUser = false;
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        user.setPoints(0);
        user.setRole("user");
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
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
    public boolean loginCheck(String username, String password) {
        ResultSet resultSet = null;
        try {
            resultSet = userSQLRepo.checkLogin(username, password);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(resultSet == null)
            return false;
        try {
            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
