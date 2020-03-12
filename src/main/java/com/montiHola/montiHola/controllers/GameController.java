package com.montiHola.montiHola.controllers;


import com.montiHola.montiHola.domains.Statistic;
import com.montiHola.montiHola.domains.dto.GameResponse;
import com.montiHola.montiHola.repositories.statRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    private statRepo statRepo;

    //Часть игры если равно 0, произошел первый выбор. Если 1, произошел второй выбор
    private int gamePart = 0;

    public void setGamePart(int gamePart) {
        this.gamePart = gamePart;
    }

    @PostMapping("/game/process")
    public String gameProcess(@RequestBody GameResponse gameResponse) {
        Statistic statistic = statRepo.findById(1).get();

        //обработка первого выбора двери
        if (gamePart == 0) {
            setGamePart(1);
            return "Хотите ли вы изменить ваш выбор? Есди нет, то нажмите на ваш выбор " + gameResponse.getFinalDoor() + " еще раз";
        }
        //обработка второго выбора двери
        if (gameResponse.getFinalDoor() == gameResponse.getCorrectDoor()) {
            statistic.setGameCount(statistic.getGameCount() + 1);
            statistic.setWins(statistic.getWins() + 1);
            //подсчет винрейт для сохранения в бд( не знаю почему так, ведь излешние данные в бд)
            statistic.setWinRate((double) statistic.getWins() / statistic.getGameCount()*100);
            setGamePart(0);
            statRepo.save(statistic);
            return "Вы выиграли, сэр. Хотите играть еще? Приз уже спрятан, выбирайте";
        }
        statistic.setGameCount(statistic.getGameCount() + 1);
        statistic.setLoses(statistic.getLoses() + 1);
        //подсчет винрейт для сохранения в бд( не знаю почему так, ведь излешние данные в бд)
        statistic.setWinRate((double) statistic.getWins() / statistic.getGameCount()*100);
        setGamePart(0);
        statRepo.save(statistic);
        return "Вы проиграли, сэр. Хотите играть еще? Приз уже спрятан, выбирайте";
    }

    //Этот метод нужен для того чтобы переписать статистику на странице
    @PostMapping("/getStat")
    public Statistic getStatistic() {
        return statRepo.findById(1).get();
    }

}
