package com.cuongpq.basemvp.view.ui.activity.main;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ActivityMainBinding;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.view.base.activity.BaseActivity;
import com.cuongpq.basemvp.view.ui.fragment.listRace.list.ListRaceFragment;
import com.cuongpq.basemvp.view.ui.fragment.profile.ProfileFragment;
import com.cuongpq.basemvp.view.ui.fragment.race.racedetail.RaceFragment;

import java.io.IOException;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    final private ProfileFragment profileFragment = new ProfileFragment();
    public static final int MY_REQUEST_CODE =10;
    private Member member;

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

    final private ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Intent intent= result.getData();
                        if(intent == null){
                            return;
                        }
                        Uri uri = intent.getData();
                        profileFragment.setmUri(uri);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            profileFragment.setBitmapImageView(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }else {
                Toast.makeText(this,"Please give access",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intentActivityResultLauncher.launch(Intent.createChooser(intent,"Pick image"));
    }
}