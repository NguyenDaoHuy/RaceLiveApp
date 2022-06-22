package com.cuongpq.basemvp.view.ui.fragment.race.createrace;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentCreateRaceBinding;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment.race.raceinfor.RaceInformationFragment;

public class CreateRaceFragment extends BaseFragmentMvp<FragmentCreateRaceBinding,CreateCarPresenter>
         implements ICreateRaceView {
    private int stt = 1;

    @Override
    public int getMainLayout() {
        return R.layout.fragment_create_race;
    }

    @Override
    protected void initView() {
        super.initView();
        presenter = new CreateCarPresenter(this);
        presenter.initPresenter();
    }

    @Override
    public void onClickListener() {
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String raceID = binding.edRaceID.getText().toString().trim();
                int idRace = Integer.parseInt(raceID);
                String raceName = binding.edRaceName.getText().toString().trim();
                if(raceName.isEmpty()){
                    createToast("Name is empty");
                } else {
                    presenter.createRace(idRace,raceName);
                }
            }
        });
    }

    @Override
    public void createToast(String str) {
        Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDay(String day) {
        binding.tvDate.setText(day);
    }

    @Override
    public void setSTTCar() {
        stt++;
        String sttCar = String.valueOf(stt);
//        binding.edCarID.setText("");
//        binding.edCarName.setText("");
//        binding.edRacer.setText("");
//        binding.sttCar.setText(sttCar);
    }

    @Override
    public Activity getActivityCreateRace() {
        return getActivity();
    }

    @Override
    public void createRaceSuccess() {
        RaceInformationFragment raceInformationFragment = new RaceInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("race",presenter.getRace());
        raceInformationFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, raceInformationFragment);
        fragmentTransaction.commit();
    }
}