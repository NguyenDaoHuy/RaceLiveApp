package com.cuongpq.basemvp.view.ui.fragment.race.createrace;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentCreateRaceBinding;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment.race.raceinfor.RaceInformationFragment;

public class CreateRaceFragment extends BaseFragmentMvp<FragmentCreateRaceBinding,CreateCarPresenter>
         implements ICreateRaceView {

    private final Member member;

    public CreateRaceFragment(Member member) {
        this.member = member;
    }

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
        binding.btnCreate.setOnClickListener(v -> {
            String raceID = binding.edRaceID.getText().toString().trim();
            String raceName = binding.edRaceName.getText().toString().trim();
            if(raceName.isEmpty() || raceID.isEmpty()){
                createToast("Information is empty");
            } else {
                int idRace = Integer.parseInt(raceID);
                presenter.createRace(idRace,raceName);
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
    public Activity getActivityCreateRace() {
        return getActivity();
    }

    @Override
    public void createRaceSuccess() {
        RaceInformationFragment raceInformationFragment = new RaceInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("race",presenter.getRace());
        bundle.putSerializable("member",member);
        raceInformationFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, raceInformationFragment);
        fragmentTransaction.commit();
    }
}