/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import liikuntaleaderboard.content.User;
import liikuntaleaderboard.service.AccomplishmentServiceInterface;
import liikuntaleaderboard.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private AccomplishmentServiceInterface accomplishmentService;
    @Autowired
    private UserServiceInterface userService;

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
        model.addAttribute("accomplishments", accomplishmentService.getAccomplishments());
        return "mainpage";
    }

    @Override
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginfailed(Model model) {
        model.addAttribute("error", "true");
        return "login";
    }

    @Override
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
            return "register";
        }
        userService.register(user);
        return "redirect:login";
    }

    @Override
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String viewRegisterPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
}
