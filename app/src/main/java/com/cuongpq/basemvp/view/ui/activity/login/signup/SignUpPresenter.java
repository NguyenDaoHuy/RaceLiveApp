package com.cuongpq.basemvp.view.ui.activity.login.signup;


import androidx.annotation.NonNull;

import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpPresenter extends BasePresenter implements ISignUpPresenter {

    private final ISignUpView view;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    public SignUpPresenter(ISignUpView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {
        view.onClickListener();
    }

    @Override
    public void onSignUp(String email, String password,String username) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(view.getActivitySignUp(), "Data.sqlite", null, 5);
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(view.getActivitySignUp(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            firebaseDatabase = FirebaseDatabase.getInstance();
                            databaseReference = firebaseDatabase.getReference();
                            String IdAcount = task.getResult().getUser().getUid();
                            sqLiteHelper.QueryData("INSERT INTO User VALUES(null,'" + IdAcount + "','" + email + "','" + username + "','0')");
                            view.onLoadStart();
                            view.signUpSuccess();
                        } else {
                            view.signUpError("Email used");
                        }
                    }
                });
    }
}
