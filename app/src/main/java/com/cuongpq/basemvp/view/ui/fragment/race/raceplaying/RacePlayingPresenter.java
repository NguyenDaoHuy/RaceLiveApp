package com.cuongpq.basemvp.view.ui.fragment.race.raceplaying;

import android.database.Cursor;

import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

import java.util.ArrayList;

public class RacePlayingPresenter extends BasePresenter implements IRacePlayingPresenter{
    private final IRacePlayingView view;
    private ArrayList<Car> carArrayList;
    private SQLiteHelper sqLiteHelper;

    public RacePlayingPresenter(IRacePlayingView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        view.onClickListener();
        sqLiteHelper = new SQLiteHelper(view.getActivityPlaying(),"Data.sqlite",null,5);
    }

    @Override
    public void getData(Race race) {
        carArrayList = new ArrayList<>();
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Car1 WHERE IdRace = '"+race.getIdRace()+"'");
        while(data.moveToNext()){
            int id = data.getInt(3);
            String name = data.getString(4);
            String racer = data.getString(5);
            int lv = data.getInt(6);
            String start = data.getString(7);
            String ss1 = data.getString(8);
            String ss2 = data.getString(9);
            String ss3 = data.getString(10);
            String ss4 = data.getString(11);
            String ss5 = data.getString(12);
            String ss6 = data.getString(13);
            String stop = data.getString(14);
            if(lv != 0 ){
                carArrayList.add(new Car(id,name,racer,lv,start,ss1,ss2,ss3,ss4,ss5,ss6,stop));
            }
        }
    }

    @Override
    public ArrayList<Car> getListCar() {
        return carArrayList;
    }
}
