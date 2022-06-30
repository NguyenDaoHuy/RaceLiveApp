package com.cuongpq.basemvp.view.ui.fragment.race.raceplaying;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import java.util.ArrayList;

public interface IRacePlayingPresenter {
    void initPresenter();
    void getData(Race race);
    ArrayList<Car> getListCar();
}
