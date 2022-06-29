package com.cuongpq.basemvp.view.ui.timekeeper.TKlistrace;

import android.widget.ArrayAdapter;

import com.cuongpq.basemvp.model.Member;
import com.cuongpq.basemvp.model.Race;

import java.util.ArrayList;

public interface IListRaceTimekeeperPresenter {
    void initPresenter(Member member);
    void getRaceData(Member member);
    ArrayList<Race> getRaceList();
}
