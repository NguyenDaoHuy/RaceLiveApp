package com.cuongpq.basemvp.view.ui.fragment.race.createrace;

import android.app.Activity;

public interface ICreateRaceView {
    void onClickListener();
    void createToast(String str);
    void setDay(String day);
    void setSTTCar();
    Activity getActivityCreateRace();
    void createRaceSuccess();
}
