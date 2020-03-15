package com.montiHola.montiHola.domains;


import javax.persistence.*;


@Entity
@Table
public class Statistic {

    @Id
    private int id;
    private int gameCountAfterChange;
    private int gameCountWithOutChange;
    private int losesAfterChange;
    private int winsAfterChange;
    private int losesWithOutChange;
    private int winsWithOutChange;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameCountAfterChange() {
        return gameCountAfterChange;
    }

    public void setGameCountAfterChange(int gameCountAfterChange) {
        this.gameCountAfterChange = gameCountAfterChange;
    }

    public int getGameCountWithOutChange() {
        return gameCountWithOutChange;
    }

    public void setGameCountWithOutChange(int gameCountWithOutChange) {
        this.gameCountWithOutChange = gameCountWithOutChange;
    }

    public int getLosesAfterChange() {
        return losesAfterChange;
    }

    public void setLosesAfterChange(int losesAfterChange) {
        this.losesAfterChange = losesAfterChange;
    }

    public int getWinsAfterChange() {
        return winsAfterChange;
    }

    public void setWinsAfterChange(int winsAfterChange) {
        this.winsAfterChange = winsAfterChange;
    }

    public int getLosesWithOutChange() {
        return losesWithOutChange;
    }

    public void setLosesWithOutChange(int losesWithOutChange) {
        this.losesWithOutChange = losesWithOutChange;
    }

    public int getWinsWithOutChange() {
        return winsWithOutChange;
    }

    public void setWinsWithOutChange(int winsWithOutChange) {
        this.winsWithOutChange = winsWithOutChange;
    }
}
