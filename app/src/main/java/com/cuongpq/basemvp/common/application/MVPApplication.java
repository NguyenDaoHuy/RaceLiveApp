package com.cuongpq.basemvp.common.application;

import android.app.Application;

import com.cuongpq.basemvp.service.sqlite.DBManager;
//******************************
//******************************
//***** Create by cuongpq  *****
//******************************
//******************************


public class MVPApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBManager db = DBManager.getInstance(this);
    }
}
