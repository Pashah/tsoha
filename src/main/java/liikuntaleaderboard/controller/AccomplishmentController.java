/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liikuntaleaderboard.controller;

import liikuntaleaderboard.service.AccomplishmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private AccomplishmentServiceInterface accomplishmentServiceInterface;

    @Override
    @RequestMapping(value = "accomplishment", method = RequestMethod.POST)
    public String saveAccomplishment(@RequestParam(value = "sport", required = true)String sport, 
                        @RequestParam(value = "lengthInMinutes", required = true)String lengthInMinutes) {
        accomplishmentServiceInterface.createAccomplishment(sport, Integer.parseInt(lengthInMinutes));
        return "redirect:/app/mainpage";
    }

    @Override
    @RequestMapping(value = "deleteAccomplishment", method = RequestMethod.POST)
    public String deleteAccomplishment(@RequestParam(value = "id", required = true) Long id) {
        accomplishmentServiceInterface.deleteAccomplishment(id);
        return "redirect:/app/mainpage";
    }

    @Override
    @RequestMapping(value = "setPoints", method = RequestMethod.POST)
    public String setPoints(@RequestParam(value = "id", required = true) Long id, 
            @RequestParam(value = "points", required = true)String points) {
        System.out.println(Integer.parseInt(points));
        accomplishmentServiceInterface.setPoints(id, Integer.parseInt(points));
        return "redirect:/app/mainpage";
    }
    
}
