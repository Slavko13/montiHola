package com.montiHola.montiHola.domains.dto;

public class GameResponse {
    private int correctDoor;
    private int userChoice;


    public int getCorrectDoor() {
        return correctDoor;
    }

    public void setCorrectDoor(int correctDoor) {
        this.correctDoor = correctDoor;
    }

    public int getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }
}
