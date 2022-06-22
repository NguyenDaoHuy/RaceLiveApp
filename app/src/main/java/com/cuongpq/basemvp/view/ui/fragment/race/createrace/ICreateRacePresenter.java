package com.cuongpq.basemvp.view.ui.fragment.race.createrace;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;

import java.util.ArrayList;

public interface ICreateRacePresenter {
    void initPresenter();
    void createRace(int idRace,String nameRace);
    void getDay();
    Race getRace();
}
