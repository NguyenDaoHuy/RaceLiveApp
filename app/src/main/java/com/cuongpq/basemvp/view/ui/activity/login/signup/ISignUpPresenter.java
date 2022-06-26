package com.cuongpq.basemvp.view.ui.activity.login.signup;

public interface ISignUpPresenter {
    void onInitPresenter();
    void onSignUp(String email,String password,String username);
}
