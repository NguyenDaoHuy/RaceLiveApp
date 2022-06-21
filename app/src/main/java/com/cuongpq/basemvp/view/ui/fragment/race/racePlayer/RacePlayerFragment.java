package com.cuongpq.basemvp.view.ui.fragment.race.racePlayer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.cuongpq.basemvp.R;
import com.cuongpq.basemvp.databinding.FragmentRacePlayerBinding;
import com.cuongpq.basemvp.model.Car;
import com.cuongpq.basemvp.view.base.fragment.BaseFragmentMvp;

import java.util.Locale;


public class RacePlayerFragment extends BaseFragmentMvp<FragmentRacePlayerBinding,RacePlayerPresenter>
      implements IRacePlayerView {

    private Car car;
    public static final String TAG = RacePlayerFragment.class.getName();
    private int seconds;
    private boolean running;
    private boolean wasRunning;
    private Bundle saveInstanceState;
    @Override
    protected void initView() {
        super.initView();
        presenter = new RacePlayerPresenter(this);
        presenter.initPresenter();
        car = (Car)getArguments().getSerializable("car");
        binding.tvNameCar.setText(car.getId() + " . " + car.getNameCar());
        if(saveInstanceState != null){
            saveInstanceState.getInt("seconds");
            saveInstanceState.getBoolean("running");
            saveInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    public int getMainLayout() {
        return R.layout.fragment_race_player;
    }

    @Override
    public void onClickListener() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFragmentManager() != null){
                    getFragmentManager().popBackStack();
                }
            }
        });
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
                binding.btnResume.setImageResource(R.drawable.ic_pause);
                Toast.makeText(getContext(),"START",Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(running){
                    binding.btnResume.setImageResource(R.drawable.ic_play);
                    Toast.makeText(getContext(),"PAUSE",Toast.LENGTH_SHORT).show();
                    running = false;
                }else if(!running){
                    binding.btnResume.setImageResource(R.drawable.ic_pause);
                    Toast.makeText(getContext(),"PLAY",Toast.LENGTH_SHORT).show();
                    running = true;
                }
            }
        });
        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                binding.btnResume.setImageResource(R.drawable.ic_pause);
                Toast.makeText(getContext(),"STOP",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
        outState.putBoolean("wasRunning",wasRunning);
    }

    private void runTimer() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                 int hours = seconds/ 3600;
                 int minutes = (seconds % 3600) / 60;
                 int secs = seconds % 60;

                 String time = String.format(Locale.getDefault(),
                         "%d:%02d:%02d",
                         hours,minutes,secs);
                 binding.tvTime.setText(time);
                 if(running){
                     seconds++;
                 }
                 handler.postDelayed(this,1000);

            }
        });
    }
}