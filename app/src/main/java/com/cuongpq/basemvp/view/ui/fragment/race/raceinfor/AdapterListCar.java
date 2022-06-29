package com.cuongpq.basemvp.view.ui.fragment.race.raceinfor;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cuongpq.basemvp.databinding.ItemCarBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.model.Member;

public class AdapterListCar extends RecyclerView.Adapter<AdapterListCar.ViewHolder> {
    private final ICar inter;
    private Member member;

    public AdapterListCar(ICar inter, Member member) {
        this.inter = inter;
        this.member = member;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCarBinding binding = ItemCarBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Car car = inter.getCar(position);
         holder.binding.nameCar.setText(car.getId() + " . " + car.getNameCar());
         holder.binding.nameRacer.setText("Racer : " + car.getRacer());
         if(member.getQuyen() == 1){
             holder.binding.btnDeleteCar.setVisibility(View.GONE);
         }
         if(member.getQuyen() == 2){
             holder.binding.btnStartCar.setVisibility(View.GONE);
             holder.binding.btnDeleteCar.setVisibility(View.GONE);
         }
         holder.itemView.setOnClickListener(v -> inter.onClickItem(position));
         holder.binding.btnStartCar.setOnClickListener(v -> inter.onClickStart(position));
         holder.binding.btnDeleteCar.setOnClickListener(v -> inter.onClickDeleteCar(position));
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    public interface ICar{
        int getCount();
        Car getCar(int position);
        void onClickItem(int position);
        void onClickStart(int position);
        void onClickDeleteCar(int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ItemCarBinding binding;
        public ViewHolder(@NonNull ItemCarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
