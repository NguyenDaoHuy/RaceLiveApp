package com.cuongpq.basemvp.view.ui.fragment.user;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.service.sqlite.DBManager;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;
import com.cuongpq.basemvp.databinding.FragmentUserBinding;
import com.cuongpq.basemvp.model.User;

import java.util.List;
//******************************
//******************************
//***** Create by cuongpq  *****
//******************************
//******************************


public class UserFragment extends BaseFragmentMvp<FragmentUserBinding, UserPresenter>
                   implements UserView, UserAdapter.IUser, View.OnClickListener {
    private UserAdapter adapter;

    @Override
    public int getMainLayout() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {
        super.initView();
        viewLoading();
        presenter = new UserPresenter(this);
        presenter.onInitPresenter();
    }

    @Override
    public void initRecycleView() {
        adapter = new UserAdapter(this);
        binding.rcContent.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rcContent.setAdapter(adapter);
    }

    @Override
    public void onClickListener() {
        binding.btnBack.setOnClickListener(this);
    }

    @Override
    public void onCallApiError() {
        DBManager db = DBManager.getInstance(getContext());
        List<User> listUsersDB = db.getUsers();
        presenter.setListUsers(listUsersDB);
        binding.rcContent.getAdapter().notifyDataSetChanged();
        disableLoading();
    }

    @Override
    public void getListUserSuccess() {
        DBManager db = DBManager.getInstance(getContext());
        if (presenter.getListUsers().size() == 0){
            List<User> listUsersDB = db.getUsers();
            presenter.setListUsers(listUsersDB);
        }else{
            db.insertListUser(presenter.getListUsers());
        }
        binding.rcContent.getAdapter().notifyDataSetChanged();
        disableLoading();
    }

    @Override
    public int getCount() {
        if (presenter.getListUsers() == null || presenter.getListUsers().size() == 0) {
            return 0;
        }
        return presenter.getListUsers().size();
    }

    @Override
    public User getData(int position) {
        return presenter.getListUsers().get(position);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                break;
            default:
                break;
        }
    }
}
