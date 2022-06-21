package com.cuongpq.basemvp.view.ui.fragment.race.raceinfor;

import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentRaceInformationBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment.race.racePlayer.RacePlayerFragment;

import java.util.ArrayList;

public class RaceInformationFragment extends BaseFragmentMvp<FragmentRaceInformationBinding,RaceInforPresenter>
        implements IRaceInfoView, AdapterListCar.ICar {

    private Race race;
    private ArrayList<Car> carArrayList;
    private AdapterListCar adapterListCar;

    @Override
    public int getMainLayout() {
        return R.layout.fragment_race_information;
    }

    @Override
    protected void initView() {
        super.initView();
        initRecyclerView();
        race = (Race) getArguments().getSerializable("race");
        carArrayList = getArguments().getParcelableArrayList("listCar");
        binding.tvRaceName.setText(race.getNameRace());
        presenter = new RaceInforPresenter(this);
        presenter.initPresenter();
    }

    @Override
    public void onClickListener() {

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
        RacePlayerFragment racePlayerFragment = new RacePlayerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("car",carArrayList.get(position));
        racePlayerFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, racePlayerFragment);
        fragmentTransaction.addToBackStack(RacePlayerFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void onClickStart(int position) {
        carArrayList.remove(position);
        initRecyclerView();
    }
}