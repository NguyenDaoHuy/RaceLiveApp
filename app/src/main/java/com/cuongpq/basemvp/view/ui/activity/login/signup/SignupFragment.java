package com.cuongpq.basemvp.view.ui.activity.login.signup;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentSignupBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.activity.login.signin.LogInFragment;

public class SignupFragment extends BaseFragmentMvp<FragmentSignupBinding,SignUpPresenter>
          implements ISignUpView {
    private String email,password,passwordConfirm,nameUser;

    @Override
    protected void initView() {
        super.initView();
        disableLoading();
        presenter = new SignUpPresenter(this);
        presenter.onInitPresenter();
    }

    @Override
    public int getMainLayout() {
        return R.layout.fragment_signup;
    }

    @Override
    public void onClickListener() {
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = binding.edAccount.getText().toString().trim();
                password = binding.edPassword.getText().toString().trim();
                passwordConfirm = binding.cfPassword.getText().toString().trim();
                nameUser = binding.edUserName.getText().toString().trim();
                if(email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() || nameUser.isEmpty()){
                    binding.tvThongBaoDangKi.setText("Empty information");
                }else if(password.length() < 6){
                    binding.tvThongBaoDangKi.setText("Password is short");
                }else if(!password.equals(passwordConfirm)){
                    binding.tvThongBaoDangKi.setText("Password Incorrect");
                }else {
                    presenter.onSignUp(email,password);
                }
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFragmentManager() != null){
                    getFragmentManager().popBackStack();
                }
            }
        });
    }

    @Override
    public void signUpSuccess() {
        disableLoading();
        Toast.makeText(getContext(),"Sign Up Success", Toast.LENGTH_SHORT).show();
        LogInFragment logInFragment = new LogInFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLogin,logInFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void signUpError(String str) {
        Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
        binding.tvThongBaoDangKi.setText(str);
    }

    @Override
    public Activity getActivitySignUp() {
        return getActivity();
    }

    @Override
    public void onLoadStart() {
        viewLoading();
    }

    @Override
    public void onLoadStop() {
        disableLoading();
    }
}