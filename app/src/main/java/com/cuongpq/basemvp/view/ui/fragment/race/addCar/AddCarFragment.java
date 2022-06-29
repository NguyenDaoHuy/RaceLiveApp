package com.cuongpq.basemvp.view.ui.fragment.race.addCar;

import android.app.Activity;
import android.widget.Toast;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentAddCarBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;


public class AddCarFragment extends BaseFragmentMvp<FragmentAddCarBinding,AddCarPresenter> implements IAddCarView{
    public static final String TAG = AddCarFragment.class.getName();
    private int idRace;
    private String strIdCar;
    private String nameCar;
    private String racer;
    private int idCar;

    @Override
    protected void initView() {
        super.initView();
        presenter = new AddCarPresenter(this);
        presenter.initPresenter();
        idRace = getArguments().getInt("raceID");
    }

    @Override
    public int getMainLayout() {
        return R.layout.fragment_add_car;
    }

    @Override
    public void onClickListener() {
          binding.btnBack.setOnClickListener(v -> {
              if(getFragmentManager() != null){
                  getFragmentManager().popBackStack();
              }
          });
          binding.btnAddCar.setOnClickListener(v -> {
              strIdCar = binding.edCarID.getText().toString().trim();
              nameCar = binding.edCarName.getText().toString().trim();
              racer = binding.edRacer.getText().toString().trim();
              if(strIdCar.isEmpty() || nameCar.isEmpty() || racer.isEmpty()){
                  createToast("Empty Information");
              }else {
                  idCar = Integer.parseInt(strIdCar);
                  presenter.addCar(idCar,nameCar,racer,idRace);
              }
          });
    }

    @Override
    public void createToast(String str) {
        Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Activity getActivityAddCar() {
        return getActivity();
    }

    @Override
    public void addSuccess() {
        getFragmentManager().popBackStack();
    }
}