package com.cuongpq.basemvp.view.ui.activity.login.signin;

import android.app.Activity;

public interface ILogInView {
    void onClickListener();
    Activity getActivityLogIn();
    void logInSuccess();
    void logInError(String str);
    void loadingStart();
    void loadingStop();
    void setLocate();
}
