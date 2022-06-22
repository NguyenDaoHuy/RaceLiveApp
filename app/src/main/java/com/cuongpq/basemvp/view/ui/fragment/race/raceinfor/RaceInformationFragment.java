package com.cuongpq.basemvp.view.ui.fragment.race.raceinfor;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentRaceInformationBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment.race.addCar.AddCarFragment;
import com.cuongpq.basemvp.view.ui.fragment.race.raceplaying.RacePlayingFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RaceInformationFragment extends BaseFragmentMvp<FragmentRaceInformationBinding,RaceInforPresenter>
        implements IRaceInfoView, AdapterListCar.ICar {

    private Race race;
    private ArrayList<Car> carArrayList;
    private SQLiteHelper sqLiteHelper;
    private String idAcount;
    public static final String TAG = RaceInformationFragment.class.getName();

    @Override
    public int getMainLayout() {
        return R.layout.fragment_race_information;
    }

    @Override
    protected void initView() {
        super.initView();
        initRecyclerView();
        sqLiteHelper = new SQLiteHelper(getActivity(),"Data.sqlite",null,5);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAcount = firebaseUser.getUid();
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
        binding.btnAdd.setOnClickListener(v -> {
            AddCarFragment addCarFragment = new AddCarFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("raceID",race.getIdRace());
            addCarFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, addCarFragment);
            fragmentTransaction.addToBackStack(AddCarFragment.TAG);
            fragmentTransaction.commit();
        });
        binding.btnRunning.setOnClickListener(v -> {
            RacePlayingFragment racePlayingFragment = new RacePlayingFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("race",race);
            racePlayingFragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, racePlayingFragment);
            fragmentTransaction.addToBackStack(RacePlayingFragment.TAG);
            fragmentTransaction.commit();
        });
    }

    @Override
    public void initRecyclerView() {
        AdapterListCar adapterListCar = new AdapterListCar(this);
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

    }

    @Override
    public void onClickStart(int position) {
        int idCar = carArrayList.get(position).getId();
        sqLiteHelper.QueryData("UPDATE Car1 SET Level = '1' , Start = '"+getTime()+"' WHERE IdAcount = '"+idAcount+"' AND IdCar = '"+idCar+"' AND IdRace = '"+race.getIdRace()+"' ");
        carArrayList.remove(position);
        Toast.makeText(getContext(),"Start",Toast.LENGTH_SHORT).show();
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
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Car1 WHERE IdAcount = '"+idAcount+"' " +
                "AND IdRace = '"+race.getIdRace()+"' " +
                "AND Level = '0' ");
        while(data.moveToNext()){
              int id = data.getInt(3);
              String name = data.getString(4);
              String racer = data.getString(5);
              int lv = data.getInt(6);
              carArrayList.add(new Car(id,name,racer,lv,null,null,null,null,null,null,null,null));
        }
        initRecyclerView();
    }
    public static String getTime(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        return sdf.format(date);
    }
}