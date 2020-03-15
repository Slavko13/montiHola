package com.montiHola.montiHola.services;


import com.montiHola.montiHola.domains.Statistic;
import com.montiHola.montiHola.domains.dto.ServerGameResponse;
import com.montiHola.montiHola.repositories.StatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    private int userChoiceAfterFirstPhase;


    @Autowired
    private StatRepo statRepo;

    @Override
    public String playGame(int currentDoor, int correctDoor) {
        return null;
    }

    @Override
    public ServerGameResponse completeFirstResponse(Integer userCurrentChoice, Integer correctDoor) {
        userChoiceAfterFirstPhase = userCurrentChoice;
        ServerGameResponse serverGameResponse = new ServerGameResponse();
        List<Integer> doorList = new ArrayList<>();
        doorList.add(1);
        doorList.add(2);
        doorList.add(3);

            if (correctDoor.equals(userCurrentChoice)) {
                doorList.remove(correctDoor);
                serverGameResponse.setFreeDoor(doorList.get((int) (Math.random() * 2)));
            }



            if (!correctDoor.equals(userCurrentChoice)) {
                doorList.remove(userCurrentChoice);
                doorList.remove(correctDoor);
                serverGameResponse.setFreeDoor(doorList.get(0));
            }
            serverGameResponse.setUserChoice(userCurrentChoice);
            serverGameResponse.setMessage( "Вы выбрали " + userCurrentChoice + " а дверь номер " + serverGameResponse.getFreeDoor() + " пустая. Хотите изменить ваш выбор?");
            return serverGameResponse;

    }

    @Override
    public ServerGameResponse completeSecondResponse(int userCurrentChoiceDoor, int correctDoor) {
        ServerGameResponse serverGameResponse = new ServerGameResponse();
        Statistic statistic = statRepo.findById(1).get();


        if (userCurrentChoiceDoor == correctDoor)  {
                if (userCurrentChoiceDoor == userChoiceAfterFirstPhase) {
                    statistic.setWinsWithOutChange(statistic.getWinsWithOutChange() + 1);
                    statistic.setGameCountWithOutChange(statistic.getGameCountWithOutChange() + 1);
                }
                else {
                    statistic.setWinsAfterChange(statistic.getWinsAfterChange() + 1);
                    statistic.setGameCountAfterChange(statistic.getGameCountAfterChange() +1);
                }
            serverGameResponse.setMessage("Вы выиграли");
        }

        if (userCurrentChoiceDoor != correctDoor) {
            if (userCurrentChoiceDoor == userChoiceAfterFirstPhase) {
                statistic.setLosesWithOutChange(statistic.getLosesWithOutChange() + 1);
                statistic.setGameCountWithOutChange(statistic.getGameCountWithOutChange() + 1);
            }
            else {
                statistic.setLosesAfterChange(statistic.getLosesAfterChange() + 1);
                statistic.setGameCountAfterChange(statistic.getGameCountAfterChange() + 1);
            }
            serverGameResponse.setMessage("Вы проиграли");
            }
        statRepo.save(statistic);
        return serverGameResponse;
    }

    @Override
    public Statistic autoPlay() {
        Statistic stat = statRepo.findById(1).get();
        int autoPlayCount = 30000;

        for (int i = 0;i < (autoPlayCount/2);i++) {

            int correctDoor = 1 + (int) (Math.random() * 3);
            int userChoice = 1 + (int) (Math.random() * 3);
            stat.setGameCountWithOutChange(stat.getGameCountWithOutChange() + 1);

            if (correctDoor == userChoice) {
                stat.setWinsWithOutChange(stat.getWinsWithOutChange() + 1);
            }
            else {
                stat.setLosesWithOutChange(stat.getLosesWithOutChange() + 1);
            }
        }

        for (int i = 0;i < (autoPlayCount/2);i++) {
            List<Integer> doorList = new ArrayList<>();
            doorList.add(1);
            doorList.add(2);
            doorList.add(3);

            stat.setGameCountAfterChange(stat.getGameCountAfterChange() + 1);
            Integer correctDoor = 1 + (int) (Math.random() * 3);
            Integer userChoice = 1 + (int) (Math.random() * 3);
            if (userChoice.equals(correctDoor)) {
                doorList.remove(userChoice);
                userChoice = (int) (Math.random() * 2);
            }
            else {
                userChoice = correctDoor;
            }

            if (correctDoor.equals(userChoice)) {
                stat.setWinsAfterChange(stat.getWinsAfterChange() + 1);
            }
            else {
                stat.setLosesAfterChange(stat.getLosesAfterChange() + 1);
            }
        }

        statRepo.save(stat);
        return stat;
    }
}
