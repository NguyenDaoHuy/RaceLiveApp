package com.cuongpq.basemvp.view.ui.fragment.race.addCar;

public interface IAddCarPresenter {
    void initPresenter();
    void addCar(int idCar,String nameCar,String racer,int idRace);
}
