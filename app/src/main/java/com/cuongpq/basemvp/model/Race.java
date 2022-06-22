package com.cuongpq.basemvp.model;

import java.io.Serializable;
import java.util.ArrayList;

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

    public void setIdRace(int idRace) {
        this.idRace = idRace;
    }

    public String getNameRace() {
        return nameRace;
    }

    public void setNameRace(String nameRace) {
        this.nameRace = nameRace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
