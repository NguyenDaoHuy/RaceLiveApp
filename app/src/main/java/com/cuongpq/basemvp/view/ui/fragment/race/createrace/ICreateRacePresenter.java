package com.cuongpq.basemvp.view.ui.fragment.race.createrace;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;

import java.util.ArrayList;

public interface ICreateRacePresenter {
    void initPresenter();
    void addListCar(String id,String name,String racer);
    void createRace(String nameRace);
    void getDay();
    Race getRace();
    ArrayList<Car> getListCar();
}
