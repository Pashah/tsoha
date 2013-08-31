/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import liikuntaleaderboard.content.Accomplishment;
import liikuntaleaderboard.service.AccomplishmentServiceInterface;
import liikuntaleaderboard.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class AccomplishmentController implements AccomplishmentControllerInterface{
    
    @Autowired
    private AccomplishmentServiceInterface accomplishmentService;
    @Autowired
    private UserServiceInterface userService;

    @Override
    @RequestMapping(value = "accomplishment", method = RequestMethod.POST)
    public String saveAccomplishment(@Valid @ModelAttribute("accomplishment") Accomplishment accomplishment,BindingResult bindingResult, 
            HttpSession session) {
        if(bindingResult.hasErrors()) {
            session.setAttribute("addAccomplishmentError", "error!");
            return "redirect:/app/mainpage";
        }
        accomplishmentService.createAccomplishment(accomplishment.getSport(), accomplishment.getLengthInMinutes(), accomplishment.getUser_id());
        userService.updatePoints(accomplishment.getUser_id(), accomplishment.getLengthInMinutes());
        return "redirect:/app/mainpage";
    }

    @Override
    @RequestMapping(value = "deleteAccomplishment", method = RequestMethod.POST)
    public String deleteAccomplishment(@RequestParam(value = "id", required = true) Long id) {
        accomplishmentService.deleteAccomplishment(id);
        return "redirect:/app/mainpage";
    }

    @Override
    @RequestMapping(value = "setPoints", method = RequestMethod.POST)
    public String setPoints(@RequestParam(value = "id", required = true) Long id, 
            @RequestParam(value = "points", required = true)String points,
            @RequestParam(value = "userId", required = true) Long userId) {
        System.out.println(Integer.parseInt(points));
        accomplishmentService.setPoints(id, Integer.parseInt(points));
        userService.updatePoints(userId, Integer.parseInt(points));
        return "redirect:/app/mainpage";
    }
    
}
