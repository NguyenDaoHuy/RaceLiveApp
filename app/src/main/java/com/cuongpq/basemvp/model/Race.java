package com.cuongpq.basemvp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Race implements Serializable{
    private String nameRace;
    private String date;
    private ArrayList<Car> cars;

    public Race(){}

    public Race(String nameRace, String date, ArrayList<Car> cars) {
        this.nameRace = nameRace;
        this.date = date;
        this.cars = cars;
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

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }
}
