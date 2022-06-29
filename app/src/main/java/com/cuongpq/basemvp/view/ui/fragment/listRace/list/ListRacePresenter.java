package com.cuongpq.basemvp.view.ui.fragment.listRace.list;

import android.database.Cursor;

import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ListRacePresenter extends BasePresenter implements IListCarPresenter{
    private final IListRaceView view;
    private ArrayList<Race> raceArrayList;
    private SQLiteHelper sqLiteHelper;
    private String idAcount;

    public ListRacePresenter(IListRaceView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        raceArrayList = new ArrayList<>();
        view.onClickListener();
        sqLiteHelper = new SQLiteHelper(view.getActivityListRace(), "Data.sqlite", null, 5);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAcount = firebaseUser.getUid();
        getDataRace();

    }

    @Override
    public void getDataRace() {
        view.clearRv();
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Race1");
        while(data.moveToNext()){
                  int id = data.getInt(2);
                  String name = data.getString(3);
                  String date = data.getString(4);
                  raceArrayList.add(new Race(id,name,date));
        }
        view.initRecyclerView();
    }

    @Override
    public ArrayList<Race> getRaceList() {
        return raceArrayList;
    }

    @Override
    public void deleteRace(Race race) {
        sqLiteHelper.QueryData("DELETE FROM Car1 WHERE IdRace = '"+ race.getIdRace() +"'");
        sqLiteHelper.QueryData("DELETE FROM Race1 WHERE IdRace = '"+ race.getIdRace() +"'");
        getDataRace();
        view.eventToast("Deleted");
    }
}
