package com.cuongpq.basemvp.view.ui.fragment.race.addCar;

import android.database.Cursor;

import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AddCarPresenter extends BasePresenter implements IAddCarPresenter{
    private final IAddCarView view;
    private String idAcount;

    public AddCarPresenter(IAddCarView view) {
        this.view = view;
    }

    @Override
    public void initPresenter() {
        view.onClickListener();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAcount = firebaseUser.getUid();
    }

    @Override
    public void addCar(int idCar, String nameCar, String racer ,int idRace) {
            SQLiteHelper sqLiteHelper = new SQLiteHelper(view.getActivityAddCar(), "Data.sqlite", null, 5);
            Cursor cursor = sqLiteHelper.GetData("SELECT * FROM Car1 WHERE IdAcount = '" + idAcount + "' AND IdCar = '" + idCar + "' AND IdRace = '" + idRace + "'");
            if(cursor.getCount() <= 0){
                sqLiteHelper.QueryData("INSERT INTO Car1 VALUES(null,'"+idAcount+"','"+idRace+"','"+idCar+"','"+nameCar+"','"+racer+"','0',null,null,null,null,null,null,null,null)");
                view.addSuccess();
                view.createToast("Add Car Success");
            }else {
                view.createToast("Car Already Exist");
            }
     }
}
