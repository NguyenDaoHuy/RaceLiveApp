package com.cuongpq.basemvp.view.ui.fragment.listRace.list;

import com.cuongpq.basemvp.model.Race;

import java.util.ArrayList;

public interface IListCarPresenter {
    void initPresenter();
    void getDataRace();
    ArrayList<Race> getRaceList();
}
