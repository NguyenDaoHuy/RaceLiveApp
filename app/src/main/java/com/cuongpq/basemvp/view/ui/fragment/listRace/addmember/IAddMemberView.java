package com.cuongpq.basemvp.view.ui.fragment.listRace.addmember;

import android.app.Activity;

public interface IAddMemberView {
    void onClickListener();
    void eventToast(String str);
    Activity getActivityAddMember();
    void addMemberSucess();
}
