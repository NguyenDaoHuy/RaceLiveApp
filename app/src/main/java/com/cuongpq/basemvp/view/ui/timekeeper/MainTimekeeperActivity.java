package com.cuongpq.basemvp.view.ui.timekeeper;

import android.annotation.SuppressLint;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ActivityMainTimekeeperBinding;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.view.ui.fragment.listRace.list.ListRaceFragment;
import com.cuongpq.basemvp.view.ui.fragment.profile.ProfileFragment;
import com.cuongpq.basemvp.view.ui.fragment.race.racedetail.RaceFragment;
import com.cuongpq.basemvp.view.ui.timekeeper.TKlistrace.ListRaceTimekeeperFragment;

public class MainTimekeeperActivity extends BaseActivity<ActivityMainTimekeeperBinding> {

    private Member member;

    @Override
    protected void initView() {
        member = (Member) getIntent().getSerializableExtra("member");
        ListRaceTimekeeperFragment fragment = new ListRaceTimekeeperFragment(member);
        getFragment(fragment);
        onCLickMenuItem();
    }


    @Override
    public int getMainLayout() {
        return R.layout.activity_main_timekeeper;
    }

    private void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentTimekeeper,fragment,Fragment.class.getName())
                .commit();
    }

    @SuppressLint("NonConstantResourceId")
    public void onCLickMenuItem() {
        binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case  R.id.listRace :
                    ListRaceTimekeeperFragment listRaceFragment = new ListRaceTimekeeperFragment(member);
                    getFragment(listRaceFragment);
                    binding.bottomNav.getMenu().findItem(R.id.listRace).setChecked(true);
                    break;
                case  R.id.proFile :
                    ProfileFragment profileFragment = new ProfileFragment();
                    getFragment(profileFragment);
                    binding.bottomNav.getMenu().findItem(R.id.proFile).setChecked(true);
                    break;
            }
            return false;
        });
    }
}