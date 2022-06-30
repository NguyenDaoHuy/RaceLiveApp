package com.cuongpq.basemvp.view.ui.fragment.race.raceplaying;


import android.app.Activity;
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
        binding.tvRaceName.setText(race.getNameRace());
        initRecyclerView();
        presenter.getData(race);
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
    public Activity getActivityPlaying() {
        return getActivity();
    }

    @Override
    public int getCount() {
        return presenter.getListCar().size();
    }

    @Override
    public Car getCar(int position) {
        return presenter.getListCar().get(position);
    }

    @Override
    public void onClickItem(int position) {
        Car car = presenter.getListCar().get(position);
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
        int idCar = presenter.getListCar().get(position).getId();
        int lv = presenter.getListCar().get(position).getLevel();
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
        presenter.getData(race);
        binding.rvCarPlaying.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onCLickDeleteCar(int position) {
        AlertDialog alertDialog=new AlertDialog.Builder(getContext())
                .setTitle("Confirm Delete")
                .setMessage("Are you sure delete car ?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    sqLiteHelper.QueryData("DELETE FROM Car1 WHERE IdCar = '"+ presenter.getListCar().get(position).getId() +"' AND IdRace = '"+race.getIdRace()+"'");
                    presenter.getData(race);
                    Toast.makeText(getContext(),"Deleted",Toast.LENGTH_SHORT).show();
                    binding.rvCarPlaying.getAdapter().notifyDataSetChanged();
                })
                .setNegativeButton("No", (dialog, which) -> {

                })
                .create();
        alertDialog.show();
    }

    private void checkList(){
        if(presenter.getListCar().size() == 0){
            binding.tvThongBaoSLXe.setVisibility(View.VISIBLE);
        }else {
            binding.tvThongBaoSLXe.setVisibility(View.INVISIBLE);
        }
    }
}