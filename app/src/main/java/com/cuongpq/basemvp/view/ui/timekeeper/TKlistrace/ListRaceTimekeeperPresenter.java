package com.cuongpq.basemvp.view.ui.timekeeper.TKlistrace;

import android.database.Cursor;

import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

import java.util.ArrayList;

public class ListRaceTimekeeperPresenter extends BasePresenter implements IListRaceTimekeeperPresenter {

    private IListRaceTimekeeperView view;
    private ArrayList<Race> raceArrayList;

    public ListRaceTimekeeperPresenter(IListRaceTimekeeperView view) {
        this.view = view;
    }

    @Override
    public void initPresenter(Member member) {
        raceArrayList = new ArrayList<>();
        view.onClickListener();
        getRaceData(member);
    }

    @Override
    public void getRaceData(Member member) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(view.getActivityListRaceTK(), "Data.sqlite", null, 5);
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Race1 ");
        while(data.moveToNext()){
            int id = data.getInt(2);
            String name = data.getString(3);
            String date = data.getString(4);
            raceArrayList.add(new Race(id,name,date));
        }
    }

    @Override
    public ArrayList<Race> getRaceList() {
        return raceArrayList;
    }
}
