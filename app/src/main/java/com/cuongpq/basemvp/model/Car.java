package com.cuongpq.basemvp.model;

import java.io.Serializable;

public class Car implements Serializable{
    private int id;
    private String nameCar;
    private String racer;
    private int level;
    private String start;
    private String ss1;
    private String ss2;
    private String ss3;
    private String ss4;
    private String ss5;
    private String ss6;
    private String stop;

    private Car(){}

    public Car(int id, String nameCar, String racer, int level, String start, String ss1, String ss2, String ss3, String ss4, String ss5, String ss6, String stop) {
        this.id = id;
        this.nameCar = nameCar;
        this.racer = racer;
        this.level = level;
        this.start = start;
        this.ss1 = ss1;
        this.ss2 = ss2;
        this.ss3 = ss3;
        this.ss4 = ss4;
        this.ss5 = ss5;
        this.ss6 = ss6;
        this.stop = stop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getRacer() {
        return racer;
    }

    public void setRacer(String racer) {
        this.racer = racer;
    }

    public int getLevel() {
        return level;
    }


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getSs1() {
        return ss1;
    }

    public String getSs2() {
        return ss2;
    }

    public String getSs3() {
        return ss3;
    }

    public String getSs4() {
        return ss4;
    }

    public String getSs5() {
        return ss5;
    }

    public String getSs6() {
        return ss6;
    }

    public String getStop() {
        return stop;
    }

}
