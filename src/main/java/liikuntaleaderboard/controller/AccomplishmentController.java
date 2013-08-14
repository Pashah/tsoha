/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Miika
 */
@Controller
public class AccomplishmentController implements AccomplishmentControllerInterface{

    @Override
    @RequestMapping(value = "accomplishment", method = RequestMethod.POST)
    public String saveAccomplishment(@RequestParam(value = "sport", required = true)String sport, 
                        @RequestParam(value = "lengthInMinutes", required = true)int lengthInMinutes) {
        return "redirect:mainpage";
    }
    
}
