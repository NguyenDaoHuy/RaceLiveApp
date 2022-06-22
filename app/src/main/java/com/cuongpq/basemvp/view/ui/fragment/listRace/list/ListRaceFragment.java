package com.cuongpq.basemvp.view.ui.fragment.listRace.list;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentListRaceBinding;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment.race.raceinfor.RaceInformationFragment;


public class ListRaceFragment extends BaseFragmentMvp<FragmentListRaceBinding,ListCarPresenter>
      implements IListRaceView, ListRaceAdapter.IRace {

    @Override
    protected void initView() {
        super.initView();
        presenter = new ListCarPresenter(this);
        presenter.initPresenter();
        initRecyclerView();
    }

    @Override
    public int getMainLayout() {
        return R.layout.fragment_list_race;
    }

    @Override
    public void onClickListener() {

    }

    @Override
    public void initRecyclerView() {
        ListRaceAdapter adapter = new ListRaceAdapter(this);
        binding.rvRace.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvRace.setAdapter(adapter);
    }

    @Override
    public Activity getActivityListRace() {
        return getActivity();
    }

    @Override
    public int getCount() {
        return presenter.getRaceList().size();
    }

    @Override
    public Race getRace(int position) {
        return presenter.getRaceList().get(position);
    }

    @Override
    public void onClickItem(int position) {
        RaceInformationFragment raceInformationFragment = new RaceInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("race",presenter.getRaceList().get(position));
        raceInformationFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, raceInformationFragment);
        fragmentTransaction.addToBackStack(RaceInformationFragment.TAG);
        fragmentTransaction.commit();
    }
}