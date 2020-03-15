package com.montiHola.montiHola.domains.dto;

public class ServerGameResponse {
    private String message;
    private int freeDoor;
    private int userChoice;


    public int getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFreeDoor() {
        return freeDoor;
    }

    public void setFreeDoor(int freeDoor) {
        this.freeDoor = freeDoor;
    }

}
