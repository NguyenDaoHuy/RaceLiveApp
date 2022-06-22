package com.cuongpq.basemvp.view.ui.fragment.race.raceplaying;

import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

public class RacePlayingPresenter extends BasePresenter implements IRacePlayingPresenter{
    private final IRacePlayingView view;

    public RacePlayingPresenter(IRacePlayingView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        view.onClickListener();
    }
}
