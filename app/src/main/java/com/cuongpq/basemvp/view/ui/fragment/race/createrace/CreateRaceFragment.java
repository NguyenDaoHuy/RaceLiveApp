package com.cuongpq.basemvp.view.ui.fragment.race.createrace;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentCreateRaceBinding;
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
        binding.btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String carName = binding.edCarName.getText().toString().trim();
                 String carID = binding.edCarID.getText().toString().trim();
                 String racerName = binding.edRacer.getText().toString().trim();
                 presenter.addListCar(carID,carName,racerName);
            }
        });
        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String raceName = binding.edRaceName.getText().toString().trim();
                if(raceName.isEmpty()){
                    createToast("Name is empty");
                }else if (presenter.getListCar().size() == 0){
                    createToast("Not car");
                } else {
                    presenter.createRace(raceName);
                    RaceInformationFragment raceInformationFragment = new RaceInformationFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("listCar", presenter.getListCar());
                    bundle.putSerializable("race",presenter.getRace());
                    raceInformationFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, raceInformationFragment);
                    fragmentTransaction.commit();
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
        binding.edCarID.setText("");
        binding.edCarName.setText("");
        binding.edRacer.setText("");
        binding.sttCar.setText(sttCar);
    }
}