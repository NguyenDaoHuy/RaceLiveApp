package com.cuongpq.basemvp.view.ui.fragment.race.addCar;

import android.app.Activity;

public interface IAddCarView {
    void onClickListener();
    void createToast(String str);
    Activity getActivityAddCar();
    void addSuccess();
}
