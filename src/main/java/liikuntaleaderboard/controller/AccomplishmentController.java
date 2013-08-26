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
    private AccomplishmentServiceInterface accomplishmentService;

    @Override
    @RequestMapping(value = "accomplishment", method = RequestMethod.POST)
    public String saveAccomplishment(@RequestParam(value = "sport", required = true)String sport, 
                        @RequestParam(value = "lengthInMinutes", required = true)String lengthInMinutes,
                        @RequestParam(value = "userId", required = true)Long userId) {
        accomplishmentService.createAccomplishment(sport, Integer.parseInt(lengthInMinutes), userId);
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
            @RequestParam(value = "points", required = true)String points) {
        System.out.println(Integer.parseInt(points));
        accomplishmentService.setPoints(id, Integer.parseInt(points));
        return "redirect:/app/mainpage";
    }
    
}
