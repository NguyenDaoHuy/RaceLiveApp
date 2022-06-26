package com.cuongpq.basemvp.view.ui.fragment.profile;

import android.database.Cursor;

import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProFilePresenter extends BasePresenter implements IProfilePresenter {

    private final IProfileView view;
    private FirebaseUser firebaseUser;
    private SQLiteHelper sqLiteHelper;
    private String idAcount;
    String name ;
    String email;

    public ProFilePresenter(IProfileView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {
        view.onClickListener();
        sqLiteHelper = new SQLiteHelper(view.getActivityProfile(),"Data.sqlite",null,5);
        getInfoUser(email,name);
    }

    @Override
    public void getInfoUser(String email,String name) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAcount = firebaseUser.getUid();
        Cursor data = sqLiteHelper.GetData("SELECT * FROM User WHERE IdAcount = '"+idAcount+"'");
        while (data.moveToNext()){
            name = data.getString(3);
            email = data.getString(2);
        }
        view.setInfoUser(email,name);
    }
}
