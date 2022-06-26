package com.cuongpq.basemvp.view.ui.fragment.profile;

import android.app.Activity;

public interface IProfileView {
    void onClickListener();
    Activity getActivityProfile();
    void setInfoUser(String email,String name);
}
