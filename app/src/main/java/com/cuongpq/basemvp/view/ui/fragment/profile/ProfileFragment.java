package com.cuongpq.basemvp.view.ui.fragment.profile;


import static com.cuongpq.basemvp.view.ui.activity.main.MainActivity.MY_REQUEST_CODE;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentProfileBinding;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.dialog.RateUsDialog;
import com.cuongpq.basemvp.view.ui.activity.login.LogInActivity;
import com.cuongpq.basemvp.view.ui.activity.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends BaseFragmentMvp<FragmentProfileBinding,ProFilePresenter> implements IProfileView{

    private Member member;
    private FirebaseUser firebaseUser;
    private String userID;
    private FirebaseDatabase firebaseDatabase;
    private Uri mUri;
    private DatabaseReference databaseReference;

    @Override
    protected void initView() {
        super.initView();
        presenter = new ProFilePresenter(this);
        presenter.onInitPresenter();
        binding.btnSetAvt.setVisibility(View.INVISIBLE);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userID = firebaseUser.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        getInfoUser();
        setUserAvatar();
    }

    @Override
    public int getMainLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    public void onClickListener() {
        binding.btnSignOut.setOnClickListener(v -> {
            AlertDialog alertDialog=new AlertDialog.Builder(getContext())
                    .setTitle("Confirm Logout")
                    .setMessage("Are you sure you signed out ?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getContext(), LogInActivity.class);
                        startActivity(intent);
                    })
                    .setNegativeButton("No", (dialog, which) -> {

                    })
                    .create();
            alertDialog.show();
        });
        binding.btnRateApp.setOnClickListener(v -> {
            RateUsDialog rateUsDialog = new RateUsDialog(getContext());
            rateUsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
            rateUsDialog.setCancelable(false);
            rateUsDialog.show();
        });
        binding.imAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
                binding.btnSetAvt.setVisibility(View.VISIBLE);
            }
        });
        binding.btnSetAvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAvatar();
            }
        });
    }

    @Override
    public Activity getActivityProfile() {
        return getActivity();
    }

    @Override
    public void setInfoUser(String email, String name) {
        binding.tvUserName.setText(name);
        binding.tvUserEmail.setText(email);
    }

    public void getInfoUser() {
        databaseReference = firebaseDatabase.getReference(userID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                member = snapshot.getValue(Member.class);
                binding.tvUserName.setText(member.getMemberName());
                binding.tvUserEmail.setText(member.getMemberAccount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void setUserAvatar() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            return;
        }
        Glide.with(this).load(user.getPhotoUrl()).error(R.drawable.avatardefult1).into(binding.imAvatar);
    }
    private void onClickRequestPermission(){
        MainActivity mainActivity = (MainActivity) getActivity();
        if(mainActivity == null){
            return;
        }
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            mainActivity.openGallery();
            return;
        }
        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            mainActivity.openGallery();
        }else {
            String[] permisstions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            this.requestPermissions(permisstions,MY_REQUEST_CODE);
        }
    }
    public void setBitmapImageView(Bitmap bitmapImageView){
        Glide.with(binding.imAvatar).load(bitmapImageView).into(binding.imAvatar);
    //    binding.imAvatar.setImageBitmap(bitmapImageView);
    }

    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }
    private void updateAvatar(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(mUri)
                .build();
        user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        binding.btnSetAvt.setVisibility(View.INVISIBLE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(),"Sucess",Toast.LENGTH_SHORT).show();
                            setUserAvatar();
                        }
                    }
                });
    }
}