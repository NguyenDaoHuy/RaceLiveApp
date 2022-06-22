package com.cuongpq.basemvp.view.ui.fragment.profile;

import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

public class ProFilePresenter extends BasePresenter implements IProfilePresenter {

    private final IProfileView view;

    public ProFilePresenter(IProfileView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {
         view.onClickListener();
    }
}
