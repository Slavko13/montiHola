package com.montiHola.montiHola.controllers;


import com.montiHola.montiHola.domains.Statistic;
import com.montiHola.montiHola.repositories.statRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;


@Controller
public class MainController {


    @Autowired
    private statRepo statRepo;


    //Данный метод собирает всю инфу для самого первого захода на страницу и прячет первый приз
    @GetMapping("/game")
    public String showGame(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        Statistic stat = statRepo.findById(1).get();
        int door = 1 + (int) (Math.random() * 3);
        data.put("statistic", stat);
        data.put("door", door);
        model.addAttribute("frontendData", data);

        return "game.html";
    }




}
