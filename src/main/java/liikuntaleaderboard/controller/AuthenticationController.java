/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.controller;

import javax.servlet.http.HttpSession;
import liikuntaleaderboard.service.AccomplishmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Miika
 */
@Controller
public class AuthenticationController implements AuthenticationControllerInterface {
    
    @Autowired
    AccomplishmentServiceInterface accomplishmentServiceInterface;

    @Override
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String viewLoginPage() {
        return "login";
    }

    @Override
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "username", required = true)String username, 
                        @RequestParam(value = "password", required = true)String password, HttpSession session) {
        if(!password.equals("password")) {
            return "redirect:login";
        }
        return "redirect:mainpage";
    }

    @Override
    @RequestMapping(value = "mainpage", method = RequestMethod.GET)
    public String viewMainPage(Model model, HttpSession session) {
        model.addAttribute("accomplishments", accomplishmentServiceInterface.getAccomplishments());
        return "mainpage";
    }

    @Override
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }
    
}
