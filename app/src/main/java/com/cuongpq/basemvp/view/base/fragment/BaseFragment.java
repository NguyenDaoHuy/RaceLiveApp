package com.cuongpq.basemvp.view.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.cuongpq.basemvp.view.base.ViewUI;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.google.android.material.snackbar.Snackbar;

//******************************
//******************************
//***** Create by cuongpq  *****
//******************************
//******************************


public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment implements ViewUI {
    protected T binding;

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getMainLayout(), container, false);
        return binding.getRoot();
    }

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected void initView() {

    }

    public BaseActivity<T> getActivityMain() {
        return (BaseActivity<T>) getActivity();
    }

    @Override
    public void onBackPressed() {
        if (getActivityMain() != null) {
            getActivityMain().onBackPressRoot();
        }
    }

    @Override
    public void viewLoading() {
        getActivityMain().viewLoading();
    }

    @Override
    public int getIdLoading() {
        return 0;
    }

    @Override
    public void disableLoading() {
        BaseActivity activity = getActivityMain();
        if (activity != null) {
            activity.disableLoading();
        }
    }

    @Override
    public void showMessage(String message) {
        if (binding != null) {
            Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();

    }
}
