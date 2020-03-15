package com.montiHola.montiHola.controllers;


import com.montiHola.montiHola.domains.Statistic;
import com.montiHola.montiHola.repositories.StatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;


@Controller
public class MainController {

    private double winRateAfterChange;
    private double winRateWithOutChange;

    @Autowired
    private StatRepo statRepo;


    //Данный метод собирает всю инфу для самого первого захода на страницу и прячет первый приз
    @GetMapping("/game")
    public String showGame(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        HashMap<Object, Object> frontendStats = new HashMap<>();
        Statistic stat = statRepo.findById(1).get();
        int gameCount = stat.getGameCountAfterChange() + stat.getGameCountWithOutChange();

        if(stat.getWinsWithOutChange() == 0) {
            winRateWithOutChange = 0;
        }
        else{
             winRateWithOutChange = (double)   stat.getWinsWithOutChange() / stat.getGameCountWithOutChange() * 100;
        }

        if(stat.getWinsAfterChange() == 0) {
             winRateAfterChange = 0;
        }
        else {
             winRateAfterChange = (double) stat.getWinsAfterChange() / stat.getGameCountAfterChange()  * 100;
        }

        int door = 1 + (int) (Math.random() * 3);
        data.put("statistic", stat);
        data.put("door", door);
        frontendStats.put("wins", stat.getWinsAfterChange() + stat.getWinsWithOutChange());
        frontendStats.put("loses", stat.getLosesAfterChange() + stat.getLosesWithOutChange());
        frontendStats.put("winRateWithOutChange", winRateWithOutChange);
        frontendStats.put("winRateAfterChange",winRateAfterChange );
        frontendStats.put("gameCount", gameCount);
        model.addAttribute("frontendData", data);
        model.addAttribute("frontendStats", frontendStats);
        return "game.html";
    }
}
