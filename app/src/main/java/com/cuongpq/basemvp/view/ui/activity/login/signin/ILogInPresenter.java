package com.cuongpq.basemvp.view.ui.activity.login.signin;

import com.cuongpq.basemvp.model.Member;

public interface ILogInPresenter {
    void onInitPresenter();
    void checkLogIn(String email,String password);
    Member getMember();
}
