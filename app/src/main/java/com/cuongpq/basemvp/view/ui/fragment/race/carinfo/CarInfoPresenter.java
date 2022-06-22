package com.cuongpq.basemvp.view.ui.fragment.race.carinfo;

import com.cuongpq.basemvp.view.base.presenter.BasePresenter;

public class CarInfoPresenter extends BasePresenter implements ICarInfoPresenter {

    private final ICarInfoView view;

    public CarInfoPresenter(ICarInfoView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {

    }
}
