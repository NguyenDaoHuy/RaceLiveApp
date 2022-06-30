package com.cuongpq.basemvp.view.ui.fragment.listRace.addmember;

import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.service.sqlite.SQLiteHelper;
import com.cuongpq.basemvp.view.base.presenter.BasePresenter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMemberPresenter extends BasePresenter implements IAddMemberPresenter{
    private final IAddMemberView view;
    private String idAcount;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public AddMemberPresenter(IAddMemberView view) {
        this.view = view;

    }

    @Override
    public void initPresenter() {
        view.onClickListener();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        idAcount = firebaseUser.getUid();
    }

    @Override
    public void createMember(String memberAccount, String memberPassword, String memberName, int permission) {
        SQLiteHelper sqLiteHelper = new SQLiteHelper(view.getActivityAddMember(), "Data.sqlite", null, 5);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(memberAccount, memberPassword)
                .addOnCompleteListener(view.getActivityAddMember(), task -> {
                    if (task.isSuccessful()) {
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference();
                        String IdAcount = task.getResult().getUser().getUid();
                        Member member = new Member(memberAccount,idAcount,memberName,permission,0);
                        databaseReference.child(IdAcount).setValue(member);
                        sqLiteHelper.QueryData("INSERT INTO User1 VALUES(null,'" + IdAcount + "','" + memberAccount + "','"+memberPassword+"','" + memberName + "','0','"+permission+"')");
                        view.eventToast("Add Member Success");
                        FirebaseAuth.getInstance().signOut();
                        view.addMemberSucess();
                    } else {
                        view.eventToast("Email used");
                    }
                });
    }
}
