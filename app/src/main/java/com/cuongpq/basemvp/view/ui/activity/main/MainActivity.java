package com.cuongpq.basemvp.view.ui.activity.main;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ActivityMainBinding;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.view.ui.activity.login.LogInActivity;
import com.cuongpq.basemvp.view.ui.fragment.listRace.list.ListRaceFragment;
import com.cuongpq.basemvp.view.ui.fragment.profile.ProfileFragment;
import com.cuongpq.basemvp.view.ui.fragment.race.racedetail.RaceFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private Member member;
    private long backPressTime;

    @Override
    protected void initView() {
        member = (Member) getIntent().getSerializableExtra("member");
        RaceFragment raceFragment = new RaceFragment(member);
        getFragment(raceFragment);
        onCLickMenuItem();
    }

    private void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content,fragment,Fragment.class.getName())
                .commit();
    }


    @Override
    public int getMainLayout() {
        return R.layout.activity_main;
    }


    @SuppressLint("NonConstantResourceId")
    public void onCLickMenuItem() {
        binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
              case  R.id.createRace :
                  RaceFragment raceFragment = new RaceFragment(member);
                  getFragment(raceFragment);
                  binding.bottomNav.getMenu().findItem(R.id.createRace).setChecked(true);
                  break;
              case  R.id.listRace :
                  ListRaceFragment listRaceFragment = new ListRaceFragment(member);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onBackPressed() {
//        AlertDialog alertDialog=new AlertDialog.Builder(this)
//                .setTitle("Confirm Exit")
//                .setMessage("Are you sure exit app ?")
//                .setPositiveButton("Yes", (dialog, which) -> {
//                    finishAffinity();
//                    System.exit(0);
//                    return;
//                })
//                .setNegativeButton("No", (dialog, which) -> {
//
//                })
//                .create();
//        alertDialog.show();
        if(backPressTime +2000 > System.currentTimeMillis()){
            finishAffinity();
            System.exit(0);
            return;
        }else {
            Toast.makeText(this,"Pres back again to exit the application",Toast.LENGTH_SHORT).show();
        }
        backPressTime = System.currentTimeMillis();
    }
}