package com.cuongpq.basemvp.view.ui.fragment.race.racePlayer;

import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

public class RacePlayerPresenter extends BasePresenter implements IRacePlayerPresenter {
    private IRacePlayerView view;

    public RacePlayerPresenter(IRacePlayerView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        view.onClickListener();
    }

}
