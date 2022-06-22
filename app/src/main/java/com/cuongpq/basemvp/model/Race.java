package com.cuongpq.basemvp.model;

import java.io.Serializable;

public class Race implements Serializable{
    private int idRace;
    private String nameRace;
    private String date;


    public Race(){}

    public Race(int idRace, String nameRace, String date) {
        this.idRace = idRace;
        this.nameRace = nameRace;
        this.date = date;
    }

    public int getIdRace() {
        return idRace;
    }
    public String getNameRace() {
        return nameRace;
    }

    public String getDate() {
        return date;
    }

}
