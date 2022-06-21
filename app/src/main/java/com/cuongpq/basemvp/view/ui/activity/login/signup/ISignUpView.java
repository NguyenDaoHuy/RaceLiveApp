package com.cuongpq.basemvp.view.ui.activity.login.signup;

import android.app.Activity;

public interface ISignUpView {
    void onClickListener();
    void signUpSuccess();
    void signUpError(String str);
    Activity getActivitySignUp();
    void onLoadStart();
    void onLoadStop();
}
