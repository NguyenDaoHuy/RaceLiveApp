package com.cuongpq.basemvp.view.ui.activity.login.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentLogInBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.activity.login.signup.SignupFragment;
import com.cuongpq.basemvp.view.ui.activity.main.MainActivity;


public class LogInFragment extends BaseFragmentMvp<FragmentLogInBinding,LogInPresenter>
        implements ILogInView{
    private String saveInformation = "tk_mk";

    @Override
    public int getMainLayout() {
        return R.layout.fragment_log_in;
    }


    @Override
    protected void initView() {
        super.initView();
        presenter = new LogInPresenter(this);
        presenter.onInitPresenter();
        disableLoading();
    }

    @Override
    public void onClickListener() {
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentLogin,new SignupFragment(),SignupFragment.class.getName())
                        .addToBackStack(SignupFragment.class.getName())
                        .commit();
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editTextAccount.getText().toString().trim();
                String password = binding.editTextPassword.getText().toString().trim();
                presenter.checkLogIn(email,password);
            }
        });

    }

    @Override
    public Activity getActivityLogIn() {
        return getActivity();
    }

    @Override
    public void logInSuccess() {
        disableLoading();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        setLocate();
    }

    @Override
    public void logInError(String str) {
        binding.tvThongBaoDangNhap.setText(str);
    }

    @Override
    public void loadingStart() {
         viewLoading();
    }

    @Override
    public void loadingStop() {
         disableLoading();
    }

    @Override
    public void setLocate() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(saveInformation, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email",binding.editTextAccount.getText().toString().trim());
        editor.putString("Password",binding.editTextPassword.getText().toString().trim());
        editor.putBoolean("Save",binding.cbSave.isChecked());
        editor.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getActivityLogIn().getSharedPreferences(saveInformation, Context.MODE_PRIVATE);
        String emailResume = sharedPreferences.getString("Email","");
        String passwordResume = sharedPreferences.getString("Password","");
        boolean save = sharedPreferences.getBoolean("Save",false);
        if(save == true){
            binding.editTextAccount.setText(emailResume);
            binding.editTextPassword.setText(passwordResume);
            binding.cbSave.setChecked(true);
        }
    }
}