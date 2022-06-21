package com.cuongpq.basemvp.view.ui.fragment.user;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.cuongpq.basemvp.databinding.ItemUserBinding;
import com.cuongpq.basemvp.model.User;
import com.cuongpq.basemvp.view.base.customview.LoadView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private IUser inter;

    public UserAdapter(IUser inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = inter.getData(position);
        holder.binding.tvName.setText(user.getFirstName() + " " + user.getLastName());
        holder.binding.tvEmail.setText(user.getEmail());
        LoadView.loadImageGlide(user.getAvatar(),holder.binding.acivAvatar);
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    public interface IUser {
        int getCount();
        User getData(int position);
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private ItemUserBinding binding;
        public UserViewHolder(@NonNull ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
