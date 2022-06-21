package com.cuongpq.basemvp.view.ui.activity.login;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ActivityLogInBinding;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.view.ui.activity.login.signin.LogInFragment;

public class LogInActivity extends BaseActivity<ActivityLogInBinding> {

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentLogin,new LogInFragment(),LogInFragment.class.getName())
                .addToBackStack(null)
                .commit();
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