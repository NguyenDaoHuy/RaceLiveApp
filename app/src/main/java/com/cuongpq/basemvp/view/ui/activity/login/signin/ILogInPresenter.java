package com.cuongpq.basemvp.view.ui.activity.login.signin;

public interface ILogInPresenter {
    void onInitPresenter();
    void checkLogIn(String email,String password);
}
