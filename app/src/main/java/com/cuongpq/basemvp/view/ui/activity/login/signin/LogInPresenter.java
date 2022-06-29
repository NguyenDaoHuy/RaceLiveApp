package com.cuongpq.basemvp.view.ui.activity.login.signin;

import androidx.annotation.NonNull;

import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogInPresenter extends BasePresenter implements ILogInPresenter, ValueEventListener {

    private final ILogInView view;
    private Member member;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private String userID;

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
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(view.getActivityLogIn(), task -> {
                    if (task.isSuccessful()) {
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        userID = firebaseUser.getUid();
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference(userID);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                member = snapshot.getValue(Member.class);
                                view.logInSuccess(member.getQuyen());
                                view.loadingStart();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    } else {
                        view.logInError("Enter the correct email and password");
                    }
                });

    }

    @Override
    public Member getMember() {
        return member;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
