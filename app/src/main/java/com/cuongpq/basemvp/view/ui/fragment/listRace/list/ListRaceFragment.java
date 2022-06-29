package com.cuongpq.basemvp.view.ui.fragment.listRace.list;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentListRaceBinding;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.model.Race;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment.listRace.addmember.AddMemberFragment;
import com.cuongpq.basemvp.view.ui.fragment.race.raceinfor.RaceInformationFragment;

public class ListRaceFragment extends BaseFragmentMvp<FragmentListRaceBinding, ListRacePresenter> implements IListRaceView, ListRaceAdapter.IRace {

    private Member member;

    public ListRaceFragment(Member member) {
        this.member = member;
    }

    @Override
    protected void initView() {
        super.initView();
        presenter = new ListRacePresenter(this);
        presenter.initPresenter();
        if(presenter.getRaceList().size() > 0){
            binding.tvThongBaoSLRace.setVisibility(View.INVISIBLE);
        }else {
            binding.tvThongBaoSLRace.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getMainLayout() {
        return R.layout.fragment_list_race;
    }

    @Override
    public void onClickListener() {
        binding.btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMemberFragment addMemberFragment = new AddMemberFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content, addMemberFragment);
                fragmentTransaction.addToBackStack(AddMemberFragment.TAG);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void initRecyclerView() {
        ListRaceAdapter adapter = new ListRaceAdapter(this,member);
        binding.rvRace.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvRace.setAdapter(adapter);
    }

    @Override
    public Activity getActivityListRace() {
        return getActivity();
    }

    @Override
    public void eventToast(String str) {
        Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearRv() {
        presenter.getRaceList().clear();
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
        fragmentTransaction.replace(R.id.content, raceInformationFragment);
        fragmentTransaction.addToBackStack(RaceInformationFragment.TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void onCLickDelete(int position) {
        AlertDialog alertDialog=new AlertDialog.Builder(getContext())
                .setTitle("Confirm Delete")
                .setMessage("Are you sure delete race ?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    presenter.deleteRace(presenter.getRaceList().get(position));
                    initRecyclerView();
                })
                .setNegativeButton("No", (dialog, which) -> {

                })
                .create();
        alertDialog.show();
    }
}