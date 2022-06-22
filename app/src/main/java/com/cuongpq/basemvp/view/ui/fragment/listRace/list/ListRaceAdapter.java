package com.cuongpq.basemvp.view.ui.fragment.listRace.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuongpq.basemvp.databinding.ItemRaceBinding;
import com.cuongpq.basemvp.model.Race;

public class ListRaceAdapter extends RecyclerView.Adapter<ListRaceAdapter.ViewHolder> {

    private IRace inter;

    public ListRaceAdapter(IRace inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public ListRaceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRaceBinding binding = ItemRaceBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListRaceAdapter.ViewHolder holder, int position) {
        Race race = inter.getRace(position);
        holder.binding.nameRace.setText(race.getIdRace() + " . "+ race.getNameRace());
        holder.binding.dateRace.setText(race.getDate());
        holder.itemView.setOnClickListener(v -> inter.onClickItem(position));
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    public interface IRace{
        int getCount();
        Race getRace(int position);
        void onClickItem(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemRaceBinding binding;
        public ViewHolder(@NonNull ItemRaceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
