package com.cuongpq.basemvp.view.ui.activity.login;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ActivityLogInBinding;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.view.ui.activity.login.signin.LogInFragment;

public class LogInActivity extends BaseActivity<ActivityLogInBinding> {

    SQLiteHelper sqLiteHelper;
    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentLogin,new LogInFragment(),LogInFragment.class.getName())
                .addToBackStack(null)
                .commit();
        sqLiteHelper = new SQLiteHelper(this,"Data.sqlite",null,5);
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS Race1(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IdAcount VARCHAR(100)," +
                "IdRace INTEGER," +
                "NameRace NVARCHAR(100)," +
                "Date VARCHAR(50))");
        sqLiteHelper.QueryData("CREATE TABLE IF NOT EXISTS Car1(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IdAcount VARCHAR(100)," +
                "IdRace INTEGER," +
                "IdCar INTEGER," +
                "NameCar NVARCHAR(100)," +
                "Racer NVARCHAR(100)," +
                "Level INTEGER," +
                "Start VARCHAR(50)," +
                "SS1 VARCHAR(50)," +
                "SS2 VARCHAR(50)," +
                "SS3 VARCHAR(50)," +
                "SS4 VARCHAR(50)," +
                "SS5 VARCHAR(50)," +
                "SS6 VARCHAR(50)," +
                "Stop VARCHAR(50))");
    }


    @Override
    public int getMainLayout() {
        return R.layout.activity_log_in;
    }

    @Override
    public int getIdLoading() {
        return R.id.loadingLogIn;
    }
}