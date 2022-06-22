package com.cuongpq.basemvp.view.ui.fragment.race.raceinfor;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cuongpq.basemvp.databinding.ItemCarBinding;
import com.cuongpq.basemvp.model.Car;
public class AdapterListCar extends RecyclerView.Adapter<AdapterListCar.ViewHolder> {
    private ICar inter;

    public AdapterListCar(ICar inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCarBinding binding = ItemCarBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Car car = inter.getCar(position);
         holder.binding.nameCar.setText(car.getId() + " . " + car.getNameCar());
         holder.binding.nameRacer.setText("Racer : " + car.getRacer());
         holder.itemView.setOnClickListener(v -> inter.onClickItem(position));
         holder.binding.btnStartCar.setOnClickListener(v -> inter.onClickStart(position));
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
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ItemCarBinding binding;
        public ViewHolder(@NonNull ItemCarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
