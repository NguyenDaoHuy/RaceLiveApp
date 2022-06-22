package com.cuongpq.basemvp.view.ui.activity.login.signin;

import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;

public class LogInPresenter extends BasePresenter implements ILogInPresenter {

    private final ILogInView view;

    public LogInPresenter(ILogInView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {
        view.onClickListener();
    }

    @Override
    public void checkLogIn(String email, String password) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if(email.isEmpty() || password.isEmpty()){
            view.logInError("Empty information");
        }else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(view.getActivityLogIn(), task -> {
                        if (task.isSuccessful()) {
                            view.logInSuccess();
                            view.loadingStart();
                        } else {
                            view.logInError("Enter the correct email and password");
                        }
                    });
        }
    }
}
