package com.cuongpq.basemvp.view.ui.fragment.race.addCar;

import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddCarPresenter extends BasePresenter implements IAddCarPresenter{
    private IAddCarView view;
    private SQLiteHelper sqLiteHelper;
    private String idAcount;
    private FirebaseUser firebaseUser;
    public AddCarPresenter(IAddCarView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        view.onClickListener();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAcount = firebaseUser.getUid();
    }

    @Override
    public void addCar(int idCar, String nameCar, String racer ,int idRace) {
        if(nameCar == "" || racer == ""){
            view.createToast("Empty information");
        }else {
            sqLiteHelper = new SQLiteHelper(view.getActivityAddCar(),"Data.sqlite",null,5);
            sqLiteHelper.QueryData("INSERT INTO Car VALUES(null,'"+idAcount+"','"+idRace+"','"+idCar+"','"+nameCar+"','"+racer+"','0')");
            view.addSuccess();
            view.createToast("Add Car Success");
        }
    }
}
