package com.cuongpq.basemvp.view.ui.fragment.listRace.addmember;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentAddMemberBinding;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.view.ui.activity.main.MainActivity;
import com.cuongpq.basemvp.view.ui.timekeeper.MainTimekeeperActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMemberFragment extends BaseFragmentMvp<FragmentAddMemberBinding,AddMemberPresenter> implements IAddMemberView{

    public static final String TAG = AddMemberFragment.class.getName();
    private final String saveInformation = "tk_mk";
    private String memberAccount;
    private String memberPassword;
    private String memberName;
    private int quyen;
    private String email;
    private String password;
    private Member member;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private String userID;

    @Override
    protected void initView() {
        super.initView();
        presenter = new AddMemberPresenter(this);
        presenter.initPresenter();
    }

    @Override
    public int getMainLayout() {
        return R.layout.fragment_add_member;
    }

    @Override
    public void onClickListener() {
          binding.btnCreateMember.setOnClickListener(v -> {
              memberAccount = binding.edMemberAccount.getText().toString().trim();
              memberPassword = binding.editMemberPassword.getText().toString().trim();
              memberName = binding.edMemberName.getText().toString().trim();
              if(binding.rdMember.isChecked()){
                  quyen = 1;
              }else if(binding.rdStatis.isChecked()){
                  quyen = 2;
              }
              if(memberAccount.isEmpty() || memberPassword.isEmpty() || memberName.isEmpty()){
                  eventToast("Empty Information");
              }else {
                  presenter.createMember(memberAccount,memberPassword,memberName,quyen);
              }
          });
    }

    @Override
    public void eventToast(String str) {
        Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Activity getActivityAddMember() {
        return getActivity();
    }

    @Override
    public void addMemberSucess() {
 //       getFragmentManager().popBackStack();
        getAccount();
        logInAgain(email,password);
    }

    private void getAccount(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(saveInformation, Context.MODE_PRIVATE);
        String emailResume = sharedPreferences.getString("Email","");
        String passwordResume = sharedPreferences.getString("Password","");
        boolean save = sharedPreferences.getBoolean("Save",false);
        if(save){
            email = emailResume;
            password = passwordResume;
        }
    }
    private void logInAgain(String email,String password){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(),task -> {
                    if (task.isSuccessful()) {
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        userID = firebaseUser.getUid();
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        databaseReference = firebaseDatabase.getReference(userID);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                member = snapshot.getValue(Member.class);
                                if(member.getQuyen() == 0){
                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("member",member);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }else if(member.getQuyen() == 1 || member.getQuyen() == 2){
                                    Intent intent = new Intent(getContext(), MainTimekeeperActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("member",member);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    } else {
                        eventToast("Enter the correct email and password");
                    }
                });
    }
}