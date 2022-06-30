package com.cuongpq.basemvp.view.ui.fragment.race.raceinfor;

import android.database.Cursor;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

import java.util.ArrayList;

public class RaceInforPresenter extends BasePresenter implements IRaceInfoPresenter {
    private final IRaceInfoView view;
    private ArrayList<Car> carArrayList;
    private SQLiteHelper sqLiteHelper;

    public RaceInforPresenter(IRaceInfoView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        view.onClickListener();
        sqLiteHelper = new SQLiteHelper(view.getActivityRaceInfo(),"Data.sqlite",null,5);
    }

    @Override
    public void getCar(Race race) {
        carArrayList = new ArrayList<>();
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Car1 WHERE IdRace = '"+race.getIdRace()+"' " +
                "AND Level = '0' ");
        while(data.moveToNext()){
            int id = data.getInt(3);
            String name = data.getString(4);
            String racer = data.getString(5);
            int lv = data.getInt(6);
            carArrayList.add(new Car(id,name,racer,lv,null,null,null,null,null,null,null,null));
        }
    }

    @Override
    public ArrayList<Car> getArrayListCar() {
        return carArrayList;
    }
}
