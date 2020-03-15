package com.montiHola.montiHola.services;


import com.montiHola.montiHola.domains.Statistic;
import com.montiHola.montiHola.domains.dto.ServerGameResponse;
import org.springframework.stereotype.Service;


public interface GameService {
     String playGame(int currentDoor, int correctDoor);
     ServerGameResponse completeFirstResponse (Integer userCurrentChoiceDoor, Integer correctDoor);
     ServerGameResponse completeSecondResponse (int userCurrentChoiceDoor, int correctDoor);
     Statistic autoPlay();

}
