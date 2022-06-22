package com.cuongpq.basemvp.view.ui.fragment.race.raceinfor;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentRaceInformationBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment.race.addCar.AddCarFragment;
import com.cuongpq.basemvp.view.ui.fragment.race.racePlayer.RacePlayerFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RaceInformationFragment extends BaseFragmentMvp<FragmentRaceInformationBinding,RaceInforPresenter>
        implements IRaceInfoView, AdapterListCar.ICar {

    private Race race;
    private ArrayList<Car> carArrayList;
    private AdapterListCar adapterListCar;
    private FirebaseUser firebaseUser;
    private SQLiteHelper sqLiteHelper;
    private String idAcount;

    @Override
    public int getMainLayout() {
        return R.layout.fragment_race_information;
    }

    @Override
    protected void initView() {
        super.initView();
        initRecyclerView();
        if(carArrayList != null){

        }else {
            carArrayList = new ArrayList<>();
        }
        race = (Race) getArguments().getSerializable("race");
        getDataCar();
        binding.tvRaceName.setText(race.getNameRace());
        presenter = new RaceInforPresenter(this);
        presenter.initPresenter();
        checkList();
    }

    @Override
    public void onClickListener() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCarFragment addCarFragment = new AddCarFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("raceID",race.getIdRace());
                addCarFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content, addCarFragment);
                fragmentTransaction.addToBackStack(AddCarFragment.TAG);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void initRecyclerView() {
        adapterListCar = new AdapterListCar(this);
        binding.rvCar.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvCar.setAdapter(adapterListCar);
    }

    @Override
    public int getCount() {
        return carArrayList.size();
    }

    @Override
    public Car getCar(int position) {
        return carArrayList.get(position);
    }

    @Override
    public void onClickItem(int position) {
//        RacePlayerFragment racePlayerFragment = new RacePlayerFragment();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("car",carArrayList.get(position));
//        racePlayerFragment.setArguments(bundle);
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.content, racePlayerFragment);
//        fragmentTransaction.addToBackStack(RacePlayerFragment.TAG);
//        fragmentTransaction.commit();
    }

    @Override
    public void onClickStart(int position) {
        carArrayList.remove(position);
        initRecyclerView();
    }
    private void checkList(){
        if(carArrayList.size() == 0){
            binding.tvThongBaoSLXe.setVisibility(View.VISIBLE);
        }else {
            binding.tvThongBaoSLXe.setVisibility(View.INVISIBLE);
        }
    }
    private void getDataCar(){
        carArrayList.clear();
        sqLiteHelper = new SQLiteHelper(getActivity(),"Data.sqlite",null,5);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAcount = firebaseUser.getUid();
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Car WHERE IdAcount = '"+idAcount+"' AND IdRace = '"+race.getIdRace()+"'");
        while(data.moveToNext()){
              int id = data.getInt(3);
              String name = data.getString(4);
              String racer = data.getString(5);
              int lv = data.getInt(6);
              carArrayList.add(new Car(id,name,racer,lv));
        }
        initRecyclerView();
    }
}