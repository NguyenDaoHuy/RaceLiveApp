package com.cuongpq.basemvp.view.ui.fragment.profile;

import android.app.AlertDialog;
import android.content.Intent;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentProfileBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.activity.login.LogInActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends BaseFragmentMvp<FragmentProfileBinding,ProFilePresenter>
       implements IProfileView{

    @Override
    protected void initView() {
        super.initView();
        presenter = new ProFilePresenter(this);
        presenter.onInitPresenter();
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
    }
}