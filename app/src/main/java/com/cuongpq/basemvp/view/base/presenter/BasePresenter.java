package com.cuongpq.basemvp.view.base.presenter;


import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter implements IBasePresenter {
    private CompositeDisposable dis;

    @Override
    public void onCreatePresenter() {
        dis = new CompositeDisposable();
    }

    @Override
    public void onDestroyPresenter() {
        if (dis != null ){
            dis.dispose();
        }
        dis = null;
    }
}
