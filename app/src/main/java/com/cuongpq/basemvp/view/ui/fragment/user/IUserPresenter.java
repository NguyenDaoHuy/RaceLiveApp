package com.cuongpq.basemvp.view.ui.fragment.user;

import com.cuongpq.basemvp.view.base.presenter.IBasePresenter;
import com.cuongpq.basemvp.model.User;

import java.util.List;

public interface IUserPresenter extends IBasePresenter {
    void onInitPresenter();
    List<User> getListUsers();
    void setListUsers(List<User> listUsers);
    void callUsersApi();
}
