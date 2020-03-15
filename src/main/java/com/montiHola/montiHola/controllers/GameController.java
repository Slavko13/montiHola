package com.montiHola.montiHola.controllers;


import com.montiHola.montiHola.domains.Statistic;
import com.montiHola.montiHola.domains.dto.GameResponse;
import com.montiHola.montiHola.domains.dto.ServerGameResponse;
import com.montiHola.montiHola.repositories.StatRepo;
import com.montiHola.montiHola.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    private StatRepo statRepo;

    @Autowired
    private GameService gameService;

    private int currentUserChoice;
    private int doorCounter = 3;


    @PostMapping("/game/process")
    public ServerGameResponse gameProcess(@RequestBody GameResponse gameResponse) {

            if (doorCounter == 3 ) {
                currentUserChoice = gameResponse.getUserChoice();
                doorCounter = 2;
                return gameService.completeFirstResponse(gameResponse.getUserChoice(), gameResponse.getCorrectDoor());
            }

            doorCounter = 3;
            return gameService.completeSecondResponse(gameResponse.getUserChoice(), gameResponse.getCorrectDoor());
    }


    //Этот метод нужен для того чтобы переписать статистику на странице
    @PostMapping("/getStat")
    public Statistic getStatistic() {
        return statRepo.findById(1).get();
    }


    @PostMapping("/autoPlay")
    public Statistic autoPlayMontihola() {
        return gameService.autoPlay();
    }

    @PostMapping("/clearStats")
    public Statistic clearStats() {
        Statistic statistic = statRepo.findById(1).get();
        statistic.setWinsAfterChange(0);
        statistic.setLosesAfterChange(0);
        statistic.setLosesWithOutChange(0);
        statistic.setWinsWithOutChange(0);
        statistic.setGameCountWithOutChange(0);
        statistic.setGameCountAfterChange(0);
        return statRepo.save(statistic);
    }

}
