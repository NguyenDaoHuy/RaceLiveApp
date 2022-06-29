package com.cuongpq.basemvp.view.ui.timekeeper.TKlistrace;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentListRaceTimekeeperBinding;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment.listRace.list.ListRaceAdapter;
import com.cuongpq.basemvp.view.ui.fragment.race.raceinfor.RaceInformationFragment;

public class ListRaceTimekeeperFragment extends BaseFragmentMvp<FragmentListRaceTimekeeperBinding,ListRaceTimekeeperPresenter> implements IListRaceTimekeeperView, ListRaceAdapter.IRace {

    private Member member;

    public ListRaceTimekeeperFragment(Member member) {
        this.member = member;
    }

    @Override
    protected void initView() {
        super.initView();
        presenter = new ListRaceTimekeeperPresenter(this);
        presenter.initPresenter(member);
        if(member.getQuyen() == 1){
            binding.memberName.setText("Check List : " + member.getMemberName());
        }else if(member.getQuyen() == 2){
            binding.memberName.setText("User : " + member.getMemberName());
        }
        checkList();
        initRecyclerView();
    }

    private void checkList(){
        if(presenter.getRaceList().size() > 0){
            binding.tvThongBaoSLRace.setVisibility(View.INVISIBLE);
        }else {
            binding.tvThongBaoSLRace.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getMainLayout() {
        return R.layout.fragment_list_race_timekeeper;
    }

    @Override
    public void onClickListener() {

    }

    @Override
    public Activity getActivityListRaceTK() {
        return getActivity();
    }

    @Override
    public void initRecyclerView() {
        ListRaceAdapter adapter = new ListRaceAdapter(this,member);
        binding.rvRaceTK.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvRaceTK.setAdapter(adapter);
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
        bundle.putSerializable("member",member);
        raceInformationFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentTimekeeper, raceInformationFragment);
        fragmentTransaction.addToBackStack(RaceInformationFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void onCLickDelete(int position) {

    }
}