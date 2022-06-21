package com.cuongpq.basemvp.view.ui.activity.login.signin;


import androidx.annotation.NonNull;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInPresenter extends BasePresenter implements ILogInPresenter {

    private ILogInView view;
    private FirebaseAuth mAuth;

    public LogInPresenter(ILogInView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {
        view.onClickListener();
    }

    @Override
    public void checkLogIn(String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        if(email.isEmpty() || password.isEmpty()){
            view.logInError("Empty information");
        }else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(view.getActivityLogIn(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                view.logInSuccess();
                                view.loadingStart();
                            } else {
                                view.logInError("Enter the correct email and password");
                            }
                        }
                    });
        }
    }
}
