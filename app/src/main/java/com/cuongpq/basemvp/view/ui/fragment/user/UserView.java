package com.cuongpq.basemvp.view.ui.fragment.user;

import com.cuongpq.basemvp.view.base.ViewUI;

public interface UserView extends ViewUI {
    void onClickListener();
    void initRecycleView();
    void getListUserSuccess();
    void onCallApiError();
}
