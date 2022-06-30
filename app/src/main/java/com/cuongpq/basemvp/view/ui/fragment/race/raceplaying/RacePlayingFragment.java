package com.cuongpq.basemvp.view.ui.fragment.race.raceplaying;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentRacePlayingBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment.race.carinfo.CarInformationFragment;
import com.cuongpq.basemvp.view.ui.fragment.race.raceinfor.RaceInformationFragment;

import java.util.ArrayList;

public class RacePlayingFragment extends BaseFragmentMvp<FragmentRacePlayingBinding,RacePlayingPresenter>
    implements IRacePlayingView, AdapterCarPlaying.ICarPlaying {

    private ArrayList<Car> carArrayList;
    private Race race;
    private SQLiteHelper sqLiteHelper;
    public static final String TAG = RacePlayingFragment.class.getName();
    public Member member;

    @Override
    protected void initView() {
        super.initView();
        presenter = new RacePlayingPresenter(this);
        presenter.initPresenter();
        sqLiteHelper = new SQLiteHelper(getActivity(),"Data.sqlite",null,5);
        race = (Race) getArguments().getSerializable("race");
        member = (Member) getArguments().getSerializable("member");
        String idAcount = member.getIdAccount();
        if(carArrayList != null){
        }else {
            carArrayList = new ArrayList<>();
        }
        binding.tvRaceName.setText(race.getNameRace());
        initRecyclerView();
        getDataCar();
        checkList();
    }

    @Override
    public int getMainLayout() {
        return R.layout.fragment_race_playing;
    }

    @Override
    public void onClickListener() {

    }

    @Override
    public void initRecyclerView() {
        AdapterCarPlaying adapterCarPlaying = new AdapterCarPlaying(this,member);
        binding.rvCarPlaying.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvCarPlaying.setAdapter(adapterCarPlaying);
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
        Car car = carArrayList.get(position);
        CarInformationFragment carInformationFragment = new CarInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("car",car);
        carInformationFragment.setArguments(bundle);
        if(member.getQuyen() == 0){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, carInformationFragment);
            fragmentTransaction.addToBackStack(CarInformationFragment.TAG);
            fragmentTransaction.commit();
        }else if(member.getQuyen() == 1 || member.getQuyen() == 2){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.contentTimekeeper, carInformationFragment);
            fragmentTransaction.addToBackStack(CarInformationFragment.TAG);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onClickGo(int position) {
        int idCar = carArrayList.get(position).getId();
        int lv = carArrayList.get(position).getLevel();
        if(lv == 1){
            sqLiteHelper.QueryData("UPDATE Car1 SET Level = '2' , SS1 = '"+RaceInformationFragment.getTime()+"' WHERE IdCar = '"+idCar+"' AND IdRace = '"+race.getIdRace()+"' ");
        }else if(lv == 2){
            sqLiteHelper.QueryData("UPDATE Car1 SET Level = '3' , SS2 = '"+RaceInformationFragment.getTime()+"' WHERE IdCar = '"+idCar+"' AND IdRace = '"+race.getIdRace()+"' ");
        }else if(lv == 3){
            sqLiteHelper.QueryData("UPDATE Car1 SET Level = '4' , SS3 = '"+RaceInformationFragment.getTime()+"' WHERE IdCar = '"+idCar+"' AND IdRace = '"+race.getIdRace()+"' ");
        }else if(lv == 4){
            sqLiteHelper.QueryData("UPDATE Car1 SET Level = '5' , SS4 = '"+RaceInformationFragment.getTime()+"' WHERE IdCar = '"+idCar+"' AND IdRace = '"+race.getIdRace()+"' ");
        }else if(lv == 5){
            sqLiteHelper.QueryData("UPDATE Car1 SET Level = '6' , SS5 = '"+RaceInformationFragment.getTime()+"' WHERE IdCar = '"+idCar+"' AND IdRace = '"+race.getIdRace()+"' ");
        }else if(lv == 6){
            sqLiteHelper.QueryData("UPDATE Car1 SET Level = '7' , SS6 = '"+RaceInformationFragment.getTime()+"' WHERE IdCar = '"+idCar+"' AND IdRace = '"+race.getIdRace()+"' ");
        }else if(lv == 7){
            sqLiteHelper.QueryData("UPDATE Car1 SET Level = '8' , Stop = '"+RaceInformationFragment.getTime()+"' WHERE IdCar = '"+idCar+"' AND IdRace = '"+race.getIdRace()+"' ");
        }else if(lv == 8){
            Toast.makeText(getContext(),"Finish",Toast.LENGTH_SHORT).show();
        }
        getDataCar();
    }

    @Override
    public void onCLickDeleteCar(int position) {
        AlertDialog alertDialog=new AlertDialog.Builder(getContext())
                .setTitle("Confirm Delete")
                .setMessage("Are you sure delete car ?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    sqLiteHelper.QueryData("DELETE FROM Car1 WHERE IdCar = '"+ carArrayList.get(position).getId() +"' AND IdRace = '"+race.getIdRace()+"'");
                    getDataCar();
                    Toast.makeText(getContext(),"Deleted",Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", (dialog, which) -> {

                })
                .create();
        alertDialog.show();
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
        Cursor data = sqLiteHelper.GetData("SELECT * FROM Car1 WHERE IdRace = '"+race.getIdRace()+"'");
        while(data.moveToNext()){
            int id = data.getInt(3);
            String name = data.getString(4);
            String racer = data.getString(5);
            int lv = data.getInt(6);
            String start = data.getString(7);
            String ss1 = data.getString(8);
            String ss2 = data.getString(9);
            String ss3 = data.getString(10);
            String ss4 = data.getString(11);
            String ss5 = data.getString(12);
            String ss6 = data.getString(13);
            String stop = data.getString(14);
            if(lv != 0 ){
                carArrayList.add(new Car(id,name,racer,lv,start,ss1,ss2,ss3,ss4,ss5,ss6,stop));
            }
        }
        binding.rvCarPlaying.getAdapter().notifyDataSetChanged();
    }
}