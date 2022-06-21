package com.cuongpq.basemvp.view.base.fragment;

import androidx.databinding.ViewDataBinding;

import com.cuongpq.basemvp.view.base.presenter.IBasePresenter;
//******************************
//******************************
//***** Create by cuongpq  *****
//******************************
//******************************


public abstract class BaseFragmentMvp<T extends ViewDataBinding, Presenter extends IBasePresenter> extends BaseFragment<T> {
    protected Presenter presenter;

    @Override
    protected void initView() {
        super.initView();
        if (presenter != null){
            presenter.onCreatePresenter();
        }
    }

    @Override
    public void onDestroyView() {
        if (presenter != null ){
            presenter.onDestroyPresenter();
            presenter = null;
        }
        super.onDestroyView();
    }
}
