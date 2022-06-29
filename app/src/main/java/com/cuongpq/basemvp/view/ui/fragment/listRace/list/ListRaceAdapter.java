package com.cuongpq.basemvp.view.ui.fragment.listRace.list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuongpq.basemvp.databinding.ItemRaceBinding;
import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.model.Race;

public class ListRaceAdapter extends RecyclerView.Adapter<ListRaceAdapter.ViewHolder> {

    private final IRace inter;
    private final Member member;

    public ListRaceAdapter(IRace inter, Member member) {
        this.inter = inter;
        this.member = member;
    }

    @NonNull
    @Override
    public ListRaceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRaceBinding binding = ItemRaceBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListRaceAdapter.ViewHolder holder, int position) {
        Race race = inter.getRace(position);
        holder.binding.nameRace.setText(race.getIdRace() + " . "+ race.getNameRace());
        holder.binding.dateRace.setText(race.getDate());
        if(member.getQuyen() == 1 || member.getQuyen() == 2){
            holder.binding.btnDeleteRace.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(v -> inter.onClickItem(position));
        holder.binding.btnDeleteRace.setOnClickListener(v -> inter.onCLickDelete(position));
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    public interface IRace{
        int getCount();
        Race getRace(int position);
        void onClickItem(int position);
        void onCLickDelete(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ItemRaceBinding binding;
        public ViewHolder(@NonNull ItemRaceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
