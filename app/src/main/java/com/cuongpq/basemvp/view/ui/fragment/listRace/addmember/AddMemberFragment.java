package com.cuongpq.basemvp.view.ui.fragment.listRace.addmember;

import android.app.Activity;
import android.widget.Toast;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentAddMemberBinding;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;

public class AddMemberFragment extends BaseFragmentMvp<FragmentAddMemberBinding,AddMemberPresenter> implements IAddMemberView{

    public static final String TAG = AddMemberFragment.class.getName();
    private String memberAccount;
    private String memberPassword;
    private String memberName;
    private int quyen;

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
        getFragmentManager().popBackStack();
//        Intent intent = new Intent(getContext(), LogInActivity.class);
//        startActivity(intent);
    }
}