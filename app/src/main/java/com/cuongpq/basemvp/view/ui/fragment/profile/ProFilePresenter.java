package com.cuongpq.basemvp.view.ui.fragment.profile;

import androidx.annotation.NonNull;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProFilePresenter extends BasePresenter implements IProfilePresenter {

    private final IProfileView view;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Member member;

    public ProFilePresenter(IProfileView view) {
        this.view = view;
    }

    @Override
    public void onInitPresenter() {
        view.onClickListener();
        SQLiteHelper sqLiteHelper = new SQLiteHelper(view.getActivityProfile(), "Data.sqlite", null, 5);
    //    getInfoUser();
    }

    @Override
    public void getInfoUser() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = firebaseUser.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(userID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                member = snapshot.getValue(Member.class);
                view.setInfoUser(member.getMemberAccount(),member.getMemberName());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
