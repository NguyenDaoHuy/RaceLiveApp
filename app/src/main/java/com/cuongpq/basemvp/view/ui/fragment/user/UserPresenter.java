package com.cuongpq.basemvp.view.ui.fragment.user;

import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.cuongpq.basemvp.model.User;
import com.cuongpq.basemvp.service.Interactor;

import java.util.ArrayList;
import java.util.List;

public class UserPresenter extends BasePresenter implements IUserPresenter {
    private List<User> users;
    private UserView userView;

    public UserPresenter(UserView userView) {
        this.userView = userView;
    }

    @Override
    public List<User> getListUsers() {
        return users;
    }

    @Override
    public void setListUsers(List<User> listUsers) {
        this.users = listUsers;
    }

    @Override
    public void onInitPresenter() {
        userView.onClickListener();
        userView.initRecycleView();
        callUsersApi();
    }

    @Override
    public void callUsersApi() {
        users = new ArrayList<>();
        Interactor.getInstance().getUsers()
                .subscribe((response)->{
                    users = response.getData();
                    userView.getListUserSuccess();
                }, error->{
                    userView.onCallApiError();
                });
    }
}
