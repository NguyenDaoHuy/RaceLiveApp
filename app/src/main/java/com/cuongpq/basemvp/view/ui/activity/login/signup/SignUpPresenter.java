package com.cuongpq.basemvp.view.ui.activity.login.signup;


import androidx.annotation.NonNull;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpPresenter extends BasePresenter implements ISignUpPresenter {

    private final ISignUpView view;
    private FirebaseAuth mAuth;


    public SignUpPresenter(ISignUpView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {
        view.onClickListener();
    }

    @Override
    public void onSignUp(String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(view.getActivitySignUp(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            view.onLoadStart();
                            view.signUpSuccess();
                        } else {
                            view.signUpError("Email used");
                        }
                    }
                });
    }
}
