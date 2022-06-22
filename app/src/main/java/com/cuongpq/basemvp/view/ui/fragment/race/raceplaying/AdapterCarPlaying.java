package com.cuongpq.basemvp.view.ui.fragment.race.raceplaying;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuongpq.basemvp.databinding.ItemCarPlayingBinding;
import com.cuongpq.basemvp.model.Car;

public class AdapterCarPlaying extends RecyclerView.Adapter<AdapterCarPlaying.ViewHolder> {

    private final ICarPlaying inter;

    public AdapterCarPlaying(ICarPlaying inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public AdapterCarPlaying.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCarPlayingBinding binding = ItemCarPlayingBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterCarPlaying.ViewHolder holder, int position) {
        Car car = inter.getCar(position);
        holder.binding.nameCar.setText(car.getId() + " . " + car.getNameCar());
        holder.binding.nameRacer.setText("Racer : " + car.getRacer());
        if(car.getLevel() == 1){
            holder.binding.tvLevel.setText("SS1");
            holder.binding.tvStartTime.setText("Start : "+ car.getStart());
        }else if(car.getLevel() == 2){
            holder.binding.tvLevel.setText("SS2");
            holder.binding.tvStartTime.setText("SS1 : "+ car.getSs1());
        }else if(car.getLevel() == 3){
            holder.binding.tvLevel.setText("SS3");
            holder.binding.tvStartTime.setText("SS2 : "+ car.getSs2());
        }else if(car.getLevel() == 4){
            holder.binding.tvLevel.setText("SS4");
            holder.binding.tvStartTime.setText("SS3 : "+ car.getSs3());
        }else if(car.getLevel() == 5){
            holder.binding.tvLevel.setText("SS5");
            holder.binding.tvStartTime.setText("SS4 : "+ car.getSs4());
        }else if(car.getLevel() == 6){
            holder.binding.tvLevel.setText("SS6");
            holder.binding.tvStartTime.setText("SS5 : "+ car.getSs5());
        }else if(car.getLevel() == 7){
            holder.binding.tvLevel.setText("Finish");
            holder.binding.tvStartTime.setText("SS6 : "+ car.getSs6());
        }else if(car.getLevel() == 8){
            holder.binding.tvLevel.setText("Finish");
            holder.binding.tvStartTime.setText("Finish");
        }
        holder.itemView.setOnClickListener(v -> inter.onClickItem(position));
        holder.binding.btnGo.setOnClickListener(v -> inter.onClickGo(position));
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }
    public interface ICarPlaying {
        int getCount();
        Car getCar(int position);
        void onClickItem(int position);
        void onClickGo(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemCarPlayingBinding binding;
        public ViewHolder(@NonNull ItemCarPlayingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
