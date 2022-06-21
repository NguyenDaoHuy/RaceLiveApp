package com.cuongpq.basemvp.view.base.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.cuongpq.basemvp.view.base.ViewUI;
import com.cuongpq.basemvp.view.base.fragment.BaseFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Locale;
//******************************
//******************************
//***** Create by cuongpq  *****
//******************************
//******************************


public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity implements ViewUI {
    protected T binding;

    protected abstract void initView();

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        binding = DataBindingUtil.setContentView(this, getMainLayout());
        initView();
    }


    public void loadLocale() {
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = preferences.getString("LANGUAGE_SETTING","");
        setLocale(language);
    }

    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("LANGUAGE_SETTING",lang);
        editor.apply();
    }
    public final void onBackPressRoot() {
        super.onBackPressed();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void onBackPressed() {
        List<Fragment> fragments =
                getSupportFragmentManager().getFragments();
        for (Fragment frg : fragments) {
            if (frg != null && frg.isVisible()) {
                if (frg instanceof BaseFragment) {
                    ((BaseFragment) frg).onBackPressed();
                    return;
                }
            }
        }
        super.onBackPressed();
    }

    @Override
    public void viewLoading(){
        if (binding != null ){
            View view = binding.getRoot().findViewById(getIdLoading());
            if (view != null){
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getIdLoading() {
        return 0;
    }

    @Override
    public void disableLoading(){
        if (binding != null ){
            View view = binding.getRoot().findViewById(getIdLoading());
            if (view != null){
                view.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void showMessage(String message) {
        if (binding != null ){
            Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
        }
    }

}