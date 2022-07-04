package com.cuongpq.basemvp.view.ui.timekeeper;

import android.annotation.SuppressLint;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ActivityMainTimekeeperBinding;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.view.ui.fragment.profile.ProfileFragment;
import com.cuongpq.basemvp.view.ui.timekeeper.TKlistrace.ListRaceTimekeeperFragment;

public class MainTimekeeperActivity extends BaseActivity<ActivityMainTimekeeperBinding> {

    private Member member;
    private long backPressTime;

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