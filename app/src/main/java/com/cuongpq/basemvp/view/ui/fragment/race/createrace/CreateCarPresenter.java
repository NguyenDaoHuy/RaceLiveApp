package com.cuongpq.basemvp.view.ui.fragment.race.createrace;

import android.annotation.SuppressLint;
import android.database.Cursor;

import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateCarPresenter extends BasePresenter implements ICreateRacePresenter {
     private final ICreateRaceView view;
     private ArrayList<Race> raceArrayList;
     private String dat;
     private Race race;
    private String idAcount;

    public CreateCarPresenter(ICreateRaceView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        raceArrayList = new ArrayList<>();
        view.onClickListener();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAcount = firebaseUser.getUid();
        getDay();
    }

    @Override
    public void createRace(int idRace,String nameRace) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(view.getActivityCreateRace(), "Data.sqlite", null, 5);
        race = new Race(idRace, nameRace, dat);
        raceArrayList.add(race);
        Cursor cursor = sqLiteHelper.GetData("SELECT * FROM Race1 WHERE IdAcount = '" + idAcount + "' AND IdRace = '" + idRace + "'");
        if (cursor.getCount() <= 0) {
            sqLiteHelper.QueryData("INSERT INTO Race1 VALUES(null,'" + idAcount + "','" + idRace + "','" + nameRace + "','" + dat + "')");
            view.createToast("Create Race Success");
            view.createRaceSuccess();
        } else {
            view.createToast("Already Exist");
        }
    }
    @Override
    public void getDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dat = sdf.format(date);
        view.setDay(dat);
    }

    @Override
    public Race getRace() {
        return race;
    }

}
