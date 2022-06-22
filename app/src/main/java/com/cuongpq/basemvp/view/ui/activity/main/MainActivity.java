package com.cuongpq.basemvp.view.ui.activity.main;


import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ActivityMainBinding;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.view.ui.fragment.listRace.list.ListRaceFragment;
import com.cuongpq.basemvp.view.ui.fragment.profile.ProfileFragment;
import com.cuongpq.basemvp.view.ui.fragment.race.racedetail.RaceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected void initView() {
        RaceFragment raceFragment = new RaceFragment();
        getFragment(raceFragment);
        onCLickMenuItem();
    }

    private void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content,fragment,Fragment.class.getName())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public int getMainLayout() {
        return R.layout.activity_main;
    }


    public void onCLickMenuItem() {
        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                  case  R.id.createRace :
                      RaceFragment raceFragment = new RaceFragment();
                      getFragment(raceFragment);
                      binding.bottomNav.getMenu().findItem(R.id.createRace).setChecked(true);
                      break;
                  case  R.id.listRace :
                      ListRaceFragment listRaceFragment = new ListRaceFragment();
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
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}