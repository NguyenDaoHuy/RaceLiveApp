package com.cuongpq.basemvp.view.ui.fragment.race.raceplaying;

import android.app.Activity;

public interface IRacePlayingView {
    void onClickListener();
    void initRecyclerView();
    Activity getActivityPlaying();
}
