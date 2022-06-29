package com.cuongpq.basemvp.view.ui.activity.login.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentLogInBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.activity.login.signup.SignupFragment;
import com.cuongpq.basemvp.view.ui.activity.main.MainActivity;
import com.cuongpq.basemvp.view.ui.timekeeper.MainTimekeeperActivity;


public class LogInFragment extends BaseFragmentMvp<FragmentLogInBinding,LogInPresenter> implements ILogInView{
    private final String saveInformation = "tk_mk";

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
      /*  binding.btnRegister.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentLogin,new SignupFragment(),SignupFragment.class.getName())
                .addToBackStack(SignupFragment.class.getName())
                .commit()); */
        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.editTextAccount.getText().toString().trim();
            String password = binding.editTextPassword.getText().toString().trim();
            presenter.checkLogIn(email,password);
        });

    }

    @Override
    public Activity getActivityLogIn() {
        return getActivity();
    }

    @Override
    public void logInSuccess(int permission) {
        disableLoading();
        if(permission == 0){
            Intent intent = new Intent(getContext(), MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("member",presenter.getMember());
            intent.putExtras(bundle);
            startActivity(intent);
            setLocate();
        }else if(permission == 1 || permission == 2){
            Intent intent = new Intent(getContext(), MainTimekeeperActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("member",presenter.getMember());
            intent.putExtras(bundle);
            startActivity(intent);
        }
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
        editor.apply();
    }

    @Override
    public void eventToast(String str) {
        Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getActivityLogIn().getSharedPreferences(saveInformation, Context.MODE_PRIVATE);
        String emailResume = sharedPreferences.getString("Email","");
        String passwordResume = sharedPreferences.getString("Password","");
        boolean save = sharedPreferences.getBoolean("Save",false);
        if(save){
            binding.editTextAccount.setText(emailResume);
            binding.editTextPassword.setText(passwordResume);
            binding.cbSave.setChecked(true);
        }
    }
}