package com.cuongpq.basemvp.view.ui.fragment.race.createrace;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateCarPresenter extends BasePresenter implements ICreateRacePresenter {
     private ICreateRaceView view;
     private ArrayList<Car> carList;
     private ArrayList<Race> raceArrayList;
     private String dat;
     private Race race;

    public CreateCarPresenter(ICreateRaceView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        carList = new ArrayList<>();
        raceArrayList = new ArrayList<>();
        view.onClickListener();
        getDay();
    }

    @Override
    public void addListCar(String id,String name,String racer){
        if( id.isEmpty()|| name.isEmpty() || racer.isEmpty()){
            view.createToast("Empty information");
        }else {
            carList.add(new Car(id,name,racer,0));
            view.setSTTCar();
            view.createToast("Add Car Success");
        }
    }

    @Override
    public void createRace(String nameRace) {
             race = new Race(nameRace,dat,carList);
             raceArrayList.add(race);
             view.createToast("Create Race Success");
    }

    @Override
    public void getDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dat = sdf.format(date);
        view.setDay(dat);
    }

    @Override
    public Race getRace() {
        return race;
    }

    @Override
    public ArrayList<Car> getListCar() {
        return carList;
    }

}
