package com.cuongpq.basemvp.view.ui.fragment.race.raceinfor;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;

import java.util.ArrayList;

public interface IRaceInfoPresenter {
     void initPresenter();
     void getCar(Race race);
     ArrayList<Car> getArrayListCar();
}
