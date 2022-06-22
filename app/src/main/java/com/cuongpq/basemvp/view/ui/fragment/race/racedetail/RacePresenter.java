package com.cuongpq.basemvp.view.ui.fragment.race.racedetail;

import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

public class RacePresenter extends BasePresenter implements IRacePresenter {
    private final IRaceView view;

    public RacePresenter(IRaceView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
       view.onClickListener();
    }
}
