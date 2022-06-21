package com.cuongpq.basemvp.view.ui.fragment.race.raceinfor;

import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

public class RaceInforPresenter extends BasePresenter implements IRaceInfoPresenter {
    private IRaceInfoView view;

    public RaceInforPresenter(IRaceInfoView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        view.onClickListener();
    }
}
