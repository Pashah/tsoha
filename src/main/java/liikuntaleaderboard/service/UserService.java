/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import liikuntaleaderboard.content.User;
import liikuntaleaderboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miika
 */
@Service
public class UserService implements UserServiceInterface {
    
    @Autowired
    private UserRepository userRepository;
    
    //@Autowired
    //private UserSQLRepo userSQLRepo;

    @Override
    public void register(User user) {
        user.setPoints(0);
        user.setRole("user");
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        try {
            //userSQLRepo.save(user);
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
    
}
