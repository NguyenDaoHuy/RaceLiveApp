package com.cuongpq.basemvp.view.ui.fragment.race.racedetail;

import androidx.fragment.app.Fragment;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentRaceBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.fragment.race.createrace.CreateRaceFragment;

public class RaceFragment extends BaseFragmentMvp<FragmentRaceBinding, RacePresenter>
    implements IRaceView {

    @Override
    protected void initView() {
        super.initView();
        presenter =  new RacePresenter(this);
        presenter.initPresenter();
    }

    @Override
    public int getMainLayout() {
        return R.layout.fragment_race;
    }

    @Override
    public void onClickListener() {
        binding.btnCreateRace.setOnClickListener(v -> {
            CreateRaceFragment createRaceFragment = new CreateRaceFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .add(R.id.content,createRaceFragment, Fragment.class.getName())
                    .addToBackStack(null)
                    .commit();
        });
    }
}