package com.cuongpq.basemvp.view.ui.fragment.listRace.list;

import android.database.Cursor;

import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ListCarPresenter extends BasePresenter implements IListCarPresenter{
    private final IListRaceView view;
    private ArrayList<Race> raceArrayList;

    public ListCarPresenter(IListRaceView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        raceArrayList = new ArrayList<>();
        getDataRace();
    }

    @Override
    public void getDataRace() {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(view.getActivityListRace(), "Data.sqlite", null, 5);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String idAcount = firebaseUser.getUid();
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Race1 WHERE IdAcount = '"+ idAcount +"'");
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
