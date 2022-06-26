package com.cuongpq.basemvp.view.ui.fragment.race.raceplaying;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.ItemCarPlayingBinding;
import com.cuongpq.basemvp.model.Car;

public class AdapterCarPlaying extends RecyclerView.Adapter<AdapterCarPlaying.ViewHolder> {

    private final ICarPlaying inter;
    private boolean show = true;

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
        holder.binding.carPlaying.setOnClickListener(v -> inter.onClickItem(position));
        holder.binding.btnGo.setOnClickListener(v -> inter.onClickGo(position));

        holder.binding.tvStart.setText(car.getStart());
        holder.binding.tvSS1.setText(car.getSs1());
        holder.binding.tvSS2.setText(car.getSs2());
        holder.binding.tvSS3.setText(car.getSs3());
        holder.binding.tvSS4.setText(car.getSs4());
        holder.binding.tvSS5.setText(car.getSs5());
        holder.binding.tvSS6.setText(car.getSs6());
        holder.binding.tvStop.setText(car.getStop());

        holder.binding.infoCar.setVisibility(View.GONE);
        holder.binding.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(show == true){
                    holder.binding.infoCar.setVisibility(View.VISIBLE);
                    holder.binding.btnDown.setImageResource(R.drawable.ic_up_24);
                    show = false;
                }else {
                    holder.binding.infoCar.setVisibility(View.GONE);
                    holder.binding.btnDown.setImageResource(R.drawable.ic_down_24);
                    show = true;
                }
            }
        });
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
