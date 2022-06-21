package com.cuongpq.basemvp.view.ui.fragment.race.raceplaying;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuongpq.basemvp.R;

public class RacePlayingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_race_playing, container, false);
    }
}