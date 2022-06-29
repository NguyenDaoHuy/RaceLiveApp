package com.cuongpq.basemvp.view.ui.activity.login.signup;


import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
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
                .addOnCompleteListener(view.getActivitySignUp(), task -> {
                    if (task.isSuccessful()) {
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference();
                        String IdAcount = task.getResult().getUser().getUid();
                        Member member = new Member(email,IdAcount,username,0,0);
                        databaseReference.child(IdAcount).setValue(member);
                        sqLiteHelper.QueryData("INSERT INTO User1 VALUES(null,'" + IdAcount + "','" + email + "','"+password+"','" + username + "','0','0')");
                        view.onLoadStart();
                        view.signUpSuccess();
                    } else {
                        view.signUpError("Email used");
                    }
                });
    }
}
